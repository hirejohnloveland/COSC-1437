package Lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    private String filename;

    private enum ParseState {
        NETWORK,
        VEHICLES,
        UNKNOWN
    }

    public FileParser(String filename) {
        this.filename = filename;
    }

    public FileData readFile() throws FileNotFoundException {
        // Hashmap for easy membership check and computeIfAbsent method
        Map<String, ShippingNode> nodeMap = new HashMap<>();
        Map<String, Vehicle> vehicleMap = new HashMap<>();
        ParseState state = ParseState.UNKNOWN;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    if (line.trim().equals("#network")) {
                        state = ParseState.NETWORK;
                    } else if (line.trim().equals("#vehicles")) {
                        state = ParseState.VEHICLES;
                    }
                    continue;
                }

                switch (state) {
                    case NETWORK:
                        String[] parts = line.split("\\s+");
                        int id = Integer.parseInt(parts[0]);
                        String start = parts[1];
                        String end = parts[2];
                        int cost = Integer.parseInt(parts[3]);
                        int time = Integer.parseInt(parts[4]);
                        VehicleType type = VehicleType.fromString(parts[5]);

                        // Create the nodes if they don't already exist
                        ShippingNode fromNode = nodeMap.computeIfAbsent(start, name -> new ShippingNode(name));
                        ShippingNode toNode = nodeMap.computeIfAbsent(end, name -> new ShippingNode(name));

                        ShippingNode.connectNeighbors(id, fromNode, toNode, cost, time, type);
                        break;
                    case VEHICLES:
                        parts = line.split("\\s+");
                        type = VehicleType.fromString(parts[0]);
                        String name = parts[1];
                        ShippingNode node = nodeMap.get(parts[2]);

                        Vehicle vehicle = vehicleMap.computeIfAbsent(name, x -> new Vehicle(type, name, node));
                        break;
                    default:
                        throw new IOException("File Parser encountered a fatal error");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading file", e);
        }

        FileData data = new FileData();
        data.nodes = new ArrayList<>(nodeMap.values());
        data.vehicles = new ArrayList<>(vehicleMap.values());

        return data;
    }

}
