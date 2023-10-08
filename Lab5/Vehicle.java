package Lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vehicle {

    private VehicleType type;
    private ShippingNode currentNode;
    private boolean isAvailable;

    public Vehicle(VehicleType vehicleType) {
        this.type = vehicleType;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setCurrentNode(ShippingNode nextNode) {
        this.currentNode = nextNode;
    }

    public boolean canTraverse(ShippingNodeConnection connection) {
        VehicleType connectionType = connection.getType();
        return (connectionType.getType() == type.getType());
    }

    public Path getTimeToNode(ShippingNode target) {
        return PathFinder.findPath(this.currentNode, target, this);
    }

    public static VehiclePath getOpenShortestPath(ShippingNode target, ArrayList<Vehicle> vehicles) {
        HashMap<Vehicle, Path> map = new HashMap<>();

        for (Vehicle vehicle : vehicles) {
            Path path = vehicle.getTimeToNode(target);
            if (path.getNodes().size() != 0) {
                map.putIfAbsent(vehicle, path);
            }
        }

        Path shortestPath = null;
        Vehicle shortestPathVehicle = null;
        int shortestTime = Integer.MAX_VALUE;

        for (Map.Entry<Vehicle, Path> entry : map.entrySet()) {
            Path currentPath = entry.getValue();
            if (currentPath.getCumulativeTime() < shortestTime) {
                shortestTime = currentPath.getCumulativeTime();
                shortestPath = currentPath;
                shortestPathVehicle = entry.getKey();
            }
        }

        return new VehiclePath(shortestPath, shortestPathVehicle);

    }

}