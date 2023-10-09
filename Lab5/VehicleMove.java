package Lab5;

import java.util.ArrayList;

public class VehicleMove {
    Vehicle vehicle;
    ShippingNode destination;

    public VehicleMove(Vehicle vehicle, ShippingNode destination) {
        this.vehicle = vehicle;
        this.destination = destination;
    }

    public void executeMove() {
        vehicle.setCurrentNode(destination);
    }

    public static void commitMoves(ArrayList<VehicleMove> plannedMoves) {
        for (VehicleMove move : plannedMoves) {
            move.executeMove();
        }
    }
}
