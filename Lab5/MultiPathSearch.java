package Lab5;

import java.util.ArrayList;

public class MultiPathSearch {
    private ShippingNode target;
    private ArrayList<Vehicle> vehicles;
    private Path bestPath;
    private ArrayList<VehicleMove> plannedMoves = new ArrayList<>();
    private ArrayList<VehicleMove> confirmedMoves = new ArrayList<>();

    public Path findBestMultiPath(ShippingNode start, ShippingNode target, ArrayList<Vehicle> vehicles) {
        this.target = target;
        this.vehicles = vehicles;
        bestPath = null;

        // Call the depth first search with the current (0th index) vehicle
        dfsSearch(new Path(), start, vehicles.get(0));

        // Return the best path and null the state
        return finalizeAndReset();
    }

    private void dfsSearch(Path currentPath, ShippingNode currentNode, Vehicle currentVehicle) {
        System.out.println("Current Node: " + currentNode.getName() + ", Current Vehicle: " + currentVehicle.getName()
                + ", Current Path: " + currentPath.toString());
        // Return condition when we reach the target, we evaluate for every connection
        // until we find the best path, then function returns back up the chain to the
        // next node.
        if (currentNode.equals(target)) {
            if (bestPath == null || currentPath.getTime() < bestPath.getTime()) {
                bestPath = currentPath.deepCopy();
                confirmedMoves.clear();
                confirmedMoves.addAll(plannedMoves);
            }
            return;
        }

        // We haven't reached the target and also haven't got better path - backtrack
        if (bestPath != null && currentPath.getTime() >= bestPath.getTime()) {
            System.out.println("Backtracking from node: " + currentNode.getName()
                    + ". Reason: A shorter path was found previously.");
            return;
        }

        // We have already covered this node as it's on the path - backtrack
        if (currentPath.contains(currentNode)) {
            System.out.println("Backtracking from node: " + currentNode.getName()
                    + ". Reason: Node already visited in the current path.");
            return;
        }

        // If we haven't reached the target, and we haven't added the current node to
        // the current path, then evaluate all connections and vehicles to move the load
        // from the current node to the next node
        for (ShippingNodeConnection connection : currentNode.getNeighbors()) {
            ShippingNode destinationNode = connection.getDestinationNode();
            System.out
                    .println("Evaluating connection from " + currentNode.getName() + destinationNode.getName() + " via "
                            + connection.getType());
            Path transitPath = new Path();
            // Add the destination node to path in the first position (will be reversed once
            // we have the vehicle path)
            transitPath.addNodeToPath(destinationNode, connection.getCost(), connection.getTime());

            // If the current vehicle is on the current node, and can also make the
            // connection then attempt to find the best path using this vehicle
            if (currentVehicle.getCurrentNode().equals(currentNode) && currentVehicle.canTraverse(connection)) {
                tryMoveVehicle(currentVehicle, currentNode, destinationNode, transitPath);
            }

            // Iterate over the rest of the vehicles to see if they have a better path
            for (Vehicle nextVehicle : vehicles) {
                if (nextVehicle != currentVehicle && nextVehicle.canTraverse(connection)) {
                    tryMoveVehicle(nextVehicle, currentNode, destinationNode, transitPath);
                }
            }
        }
    }

    private void tryMoveVehicle(Vehicle vehicle, ShippingNode currentNode, ShippingNode destinationNode,
            Path transitPath) {
        System.out.println("Attempting to move vehicle: " + vehicle.getName() + " to " + destinationNode.getName());
        // Get the path from the vehicle's current position to the currentNode
        Path vehiclePath = vehicle.getPathToNode(currentNode);

        // Create a copy of the vehiclePath and append the transitPath
        Path newPath = vehiclePath.concat(transitPath);

        // Register the move of the vehicle to it's destination as a planned move
        VehicleMove move = new VehicleMove(vehicle, destinationNode);
        plannedMoves.add(move);

        // Recursive call
        dfsSearch(newPath, destinationNode, vehicle);

        // After returning from the DFS call:
        plannedMoves.remove(plannedMoves.size() - 1);
    }

    private Path finalizeAndReset() {

        // Isolate return path and move vehicles to their locations
        Path returnPath = bestPath.deepCopy();
        VehicleMove.commitMoves(confirmedMoves);

        // Null or remove state variables
        bestPath = null;
        plannedMoves.clear();
        confirmedMoves.clear();

        return returnPath;
    }
}
