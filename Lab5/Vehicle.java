package Lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vehicle {

    private VehicleType type;
    private String name;
    private ShippingNode currentNode;
    private Path vehiclePath;

    public Vehicle(VehicleType vehicleType, String name, ShippingNode startingNode) {
        this.type = vehicleType;
        this.name = name;
        this.currentNode = startingNode;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return type.getType();
    }

    public void setCurrentNode(ShippingNode nextNode) {
        if (currentNode != nextNode) {
            System.out
                    .println("Vehicle " + name + " moved from " + currentNode.getName() + " to " + nextNode.getName());
            this.currentNode = nextNode;
        } else {
            System.out.println("Vehicle " + name + " started and ended at " + currentNode.getName());
        }

    }

    public boolean canTraverse(ShippingNodeConnection connection) {
        VehicleType connectionType = connection.getType();
        return (connectionType.getType() == type.getType());
    }

    public Path getPathToNode(ShippingNode target) {
        if (target == this.currentNode) {
            Path emptyPath = new Path();
            emptyPath.setVehicle(this);
            return emptyPath;
        }
        return PathFinder.findPath(this.currentNode, target, this);
    }

    public static ArrayList<Path> getBestVehicleCandidates(ShippingNode startNode,
            ArrayList<Vehicle> vehicles) {
        ArrayList<Path> vehiclePathsToStart = new ArrayList<>();

        // find all the vehicles capable of reaching the starting node
        for (Vehicle vehicle : vehicles) {
            Path tentativeVehiclePath = vehicle.getPathToNode(startNode);
            if (tentativeVehiclePath != null) {
                tentativeVehiclePath.setVehicle(vehicle);
                vehiclePathsToStart.add(tentativeVehiclePath);
            }
        }

        // Prune all but the quickest path by type
        Map<String, Path> bestPaths = new HashMap<>();

        for (Path vehiclePath : vehiclePathsToStart) {
            String type = vehiclePath.getVehicle().getType();
            if (!bestPaths.containsKey(type) ||
                    vehiclePath.getTime() < bestPaths.get(type).getTime()) {
                bestPaths.put(type, vehiclePath);
            }
        }

        return new ArrayList<>(bestPaths.values());
    }

}