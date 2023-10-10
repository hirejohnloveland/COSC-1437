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
        dfsSearch(new Path(), start, null, null);

        // Return the best path and null the state
        return finalizeAndReset();
    }

    private void dfsSearch(Path currentPath, ShippingNode currentNode, ShippingNodeConnection lastConnection,
            Vehicle currentVehicle) {
        System.out.println("Current Node: " + currentNode.getName()
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
            System.out.println("Backtracking from connection to node: " + currentNode.getName()
                    + ". Reason: A shorter path was found previously.");
            return;
        }

        // We have already covered this node as it's on the path - backtrack
        if (currentPath.containsExcludingLast(currentNode)) {
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
                    .println("Evaluating connection from " + currentNode.getName() + " to " + destinationNode.getName()
                            + " via "
                            + connection.getType());
            Path transitPath = new Path();
            // Add the destination node to path in the first position (will be reversed once
            // we have the vehicle path)
            transitPath.addConnectionToPath(connection);

            // Iterate over all the vehicles that can make the trip to find the best path to
            // the connection.
            for (Vehicle nextVehicle : vehicles) {
                if (nextVehicle.canTraverse(connection)) {
                    tryMoveVehicle(nextVehicle, currentNode, destinationNode, transitPath, connection);
                }
            }
        }
    }

    private void tryMoveVehicle(Vehicle vehicle, ShippingNode currentNode, ShippingNode destinationNode,
            Path transitPath, ShippingNodeConnection connection) {
        System.out.println("Attempting to move vehicle: " + vehicle.getName() + " to " + destinationNode.getName());
        // Get the path from the vehicle's current position to the currentNode
        Path vehiclePath = vehicle.getPathToNode(currentNode);

        // Create a copy of the vehiclePath and append the transitPath
        Path newPath = vehiclePath.concat(transitPath);

        // Register the move of the vehicle to it's destination as a planned move
        VehicleMove move = new VehicleMove(vehicle, destinationNode);
        plannedMoves.add(move);

        // Recursive call
        dfsSearch(newPath, destinationNode, connection, vehicle);

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
