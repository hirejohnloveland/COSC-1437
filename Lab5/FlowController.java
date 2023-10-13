package Lab5;

import java.util.ArrayList;

public class FlowController {

    private ArrayList<Vehicle> vehicles;
    private ArrayList<Shipment> shipments;

    public FlowController(FileData data) {
        this.vehicles = data.vehicles;
        this.shipments = data.shipments;
    }

    public void optimizeNetwork() {

        int counter = 1;
        for (Shipment shipment : shipments) {
            ShippingNode start = shipment.getSourceNode();
            ShippingNode end = shipment.getDestinationNode();

            System.out.println(
                    "\nTrip # " + counter + " is from " + start.getName() + " to "
                            + end.getName() + "\n");
            Path bestPath = PathController.getBestPathForShipment(start, end, vehicles);
            OutputResults.printToConsole(bestPath);
            counter++;
        }
    }

}
