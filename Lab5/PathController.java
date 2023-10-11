package Lab5;

import java.util.ArrayList;

public class PathController {
    public static Path getBestPathForShipment(ShippingNode start, ShippingNode end, ArrayList<Vehicle> vehicles) {

        ArrayList<Path> vehiclePathsToStartNode = Vehicle.getBestVehicleCandidates(start, vehicles);

        Path bestPath = new Path();
        bestPath.setTime(Integer.MAX_VALUE);

        for (Path vehiclePath : vehiclePathsToStartNode) {
            Path shippingPathCandidate = PathFinder.findPath(start, end, vehiclePath.getVehicle());
            if (shippingPathCandidate != null && shippingPathCandidate.getTime() < bestPath.getTime()) {
                Path tempPath = vehiclePath.deepCopy();
                tempPath.addPath(shippingPathCandidate);
                bestPath = tempPath;
            }
        }

        bestPath.getVehicle().setCurrentNode(end);

        return bestPath;

    }

}
