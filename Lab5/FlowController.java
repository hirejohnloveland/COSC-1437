package Lab5;

import java.util.ArrayList;

public class FlowController {
    public void optimizeNetwork(ArrayList<ShippingNode> nodes, ArrayList<Vehicle> vehicles) {
        String startName = "Abaca";
        ShippingNode start = ShippingNode.getNodeByName(nodes, startName);

        String endName = "Dolton";
        ShippingNode end = ShippingNode.getNodeByName(nodes, endName);

        System.out.println("\nThis trip is from " + startName + " to " + endName + "\n");

        MultiPathSearch multiPathSearch = new MultiPathSearch();
        Path bestPath = multiPathSearch.findBestMultiPath(start, end, vehicles);

        OutputResults.printToConsole(bestPath);

        ShippingNode.resetNodes(nodes);

        startName = "Brecha";
        start = ShippingNode.getNodeByName(nodes, startName);

        endName = "Chiano";
        end = ShippingNode.getNodeByName(nodes, endName);

        System.out.println("\nThis trip is from " + startName + " to " + endName + "\n");
        bestPath = multiPathSearch.findBestMultiPath(start, end, vehicles);

        OutputResults.printToConsole(bestPath);

    }

}
