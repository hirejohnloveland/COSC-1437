package Lab5;

import java.util.ArrayList;
import java.util.Comparator;

// Slightly altered algorithm to use Dijkstra instead of A*
// Did this because A* is better suited to coordinate plane problems or problems
// where the individual peices are spatially related. Becuase the sites aren't
// clearly spatially related, this problem lends itself to graph traversal.
// With A* I was continually fighting the code to make it not
// exit prematurely.
public class PathFinder {
	public static Path findPath(ShippingNode start, ShippingNode end, Vehicle vehicle) {
		start.resetNodeAndAllOtherNodes();
		ArrayList<ShippingNode> open = new ArrayList<ShippingNode>();
		ArrayList<ShippingNode> closed = new ArrayList<ShippingNode>();
		Path initialpath = new Path();
		start.setPath(initialpath);
		open.add(start);

		while (!open.isEmpty()) {
			// Get the most promising node from the list
			ShippingNode current = open.stream().min(Comparator.comparingInt(node -> node.getTime())).orElse(null);

			open.remove(current);

			if (current.equals(end)) {
				break;
			}

			ArrayList<ShippingNodeConnection> neighborConnections = current.getNeighbors();
			for (ShippingNodeConnection connection : neighborConnections) {
				if (vehicle != null && !vehicle.canTraverse(connection)) {
					continue;
				}
				// Find the time of the connection to the neighbor
				ShippingNode neighborNode = connection.getDestinationNode();
				int tentativeTime = current.getTime() + connection.getTime();

				if (closed.contains(neighborNode)) {
					continue;
				}

				if (!open.contains(neighborNode) || tentativeTime < neighborNode.getTime()) {
					// Set path and cost for connection to neighbor if we have
					// found a better path

					Path newPath = current.getPath().deepCopy();
					newPath.addConnectionToPath(connection);
					neighborNode.setPath(newPath);

					// Add to open list if we haven't visited this node
					if (!open.contains(neighborNode)) {
						open.add(neighborNode);
					}

				}
			}
			closed.add(current);
		}
		return end.getPath() != null ? end.getPath() : null;
	}
}
