package Lab5;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class FlowController {

    private ArrayList<Vehicle> vehicles;
    private ArrayList<Shipment> shipments;

    public FlowController(FileData data) {
        this.vehicles = data.vehicles;
        this.shipments = data.shipments;
    }

    public void optimizeNetwork() {
        // thread safe copy of the undelivered list to allow for concurrent modification
        CopyOnWriteArrayList<Shipment> undelivered = new CopyOnWriteArrayList<>(shipments);
        while (undelivered.size() > 0) {
            for (Shipment shipment : shipments) {
                // Remove the shipment from the list if it already delivered
                if (shipment.isShipmentDelivered()) {
                    undelivered.remove(shipment);
                }
                // Try to reserve a vehicle
                if (!shipment.isShipmentDelivered() && shipment.getReservedVehicle() == null) {
                    Vehicle reservedVehicle = Vehicle.reserveVehicle(shipment, vehicles);
                    if (reservedVehicle != null) {
                        shipment.commitShipmentToVehicle(reservedVehicle);
                    }
                }

                // If the shipment has a vehicle reserved, advance the vehicle forward on it's
                // pathway
                if (shipment.getReservedVehicle() != null) {
                    shipment.getReservedVehicle().advanceVehicle();
                }
            }
        }
        for (Shipment shipment : shipments) {
            OutputResults.printToConsole(shipment);
        }
    }
}
