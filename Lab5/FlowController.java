package Lab5;

import java.util.ArrayList;

public class FlowController {
    public void optimizeNetwork(ArrayList<ShippingNode> nodes) {
        String startName = "Abaca";
        ShippingNode start = ShippingNode.getNodeByName(nodes, startName);

        String endName = "Chiano";
        ShippingNode end = ShippingNode.getNodeByName(nodes, endName);

        String vehicleNodeName = "Brecha";
        ShippingNode vehicleStartNode = ShippingNode.getNodeByName(nodes, vehicleNodeName);

        Vehicle vehicle1 = new Vehicle(VehicleType.fromString("road"));
        vehicle1.setCurrentNode(vehicleStartNode);

        Vehicle vehicle2 = new Vehicle(VehicleType.fromString("water"));
        vehicle2.setCurrentNode(end);

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        VehiclePath vehiclePath = Vehicle.getOpenShortestPath(start, vehicles);
        ShippingNode.resetNodes(nodes);

        var path = PathFinder.findPath(start, end, vehiclePath.vehicle);
        vehiclePath.vehicle.setCurrentNode(end);
        ShippingNode.resetNodes(nodes);
        OutputResults.printToConsole(path, vehiclePath.path);

        startName = "Brecha";
        start = ShippingNode.getNodeByName(nodes, startName);

        endName = "Chiano";
        end = ShippingNode.getNodeByName(nodes, endName);

        vehiclePath = Vehicle.getOpenShortestPath(start, vehicles);

        ShippingNode.resetNodes(nodes);
        path = PathFinder.findPath(start, end, vehiclePath.vehicle);
        vehiclePath.vehicle.setCurrentNode(end);
        ShippingNode.resetNodes(nodes);
        OutputResults.printToConsole(path, vehiclePath.path);

    }

}
