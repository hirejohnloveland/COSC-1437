package Lab5;

import java.util.ArrayList;

public class FlowController {
    public void optimizeNetwork(ArrayList<ShippingNode> nodes, ArrayList<Vehicle> vehicles) {
        // Todo - move start and end code to cargo class,

        String startName = "Abaca";
        ShippingNode start = ShippingNode.getNodeByName(nodes, startName);

        String endName = "Chiano";
        ShippingNode end = ShippingNode.getNodeByName(nodes, endName);

        System.out.println("\nThis trip is from " + startName + " to " + endName + "\n");

        Path bestPath = PathController.getBestPathForShipment(start, end, vehicles);

        OutputResults.printToConsole(bestPath);

        startName = "Brecha";
        start = ShippingNode.getNodeByName(nodes, startName);

        endName = "Chiano";
        end = ShippingNode.getNodeByName(nodes, endName);

        System.out.println("\nThis trip is from " + startName + " to " + endName +
                "\n");
        bestPath = PathController.getBestPathForShipment(start, end, vehicles);

        OutputResults.printToConsole(bestPath);

    }

}
