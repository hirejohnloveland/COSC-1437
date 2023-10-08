package Lab5;

import java.util.ArrayList;

import org.w3c.dom.css.ViewCSS;

public class FlowController {
    public void optimizeNetwork(ArrayList<ShippingNode> nodes, ArrayList<Vehicle> vehicles) {
        String startName = "Abaca";
        ShippingNode start = ShippingNode.getNodeByName(nodes, startName);

        String endName = "Chiano";
        ShippingNode end = ShippingNode.getNodeByName(nodes, endName);

        VehiclePath vehiclePath = Vehicle.getOpenShortestPath(start, vehicles);
        ShippingNode.resetNodes(nodes);

        var path = PathFinder.findPath(start, end, vehiclePath.vehicle);
        vehiclePath.vehicle.setCurrentNode(end);
        ShippingNode.resetNodes(nodes);
        OutputResults.printToConsole(path, vehiclePath);

        startName = "Brecha";
        start = ShippingNode.getNodeByName(nodes, startName);

        endName = "Chiano";
        end = ShippingNode.getNodeByName(nodes, endName);

        vehiclePath = Vehicle.getOpenShortestPath(start, vehicles);

        ShippingNode.resetNodes(nodes);
        path = PathFinder.findPath(start, end, vehiclePath.vehicle);
        vehiclePath.vehicle.setCurrentNode(end);
        ShippingNode.resetNodes(nodes);
        OutputResults.printToConsole(path, vehiclePath);

    }

}
