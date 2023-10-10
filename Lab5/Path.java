package Lab5;

import java.util.ArrayList;

public class Path {
    private ArrayList<ShippingNodeConnection> connectionsInPath;
    private int cost;
    private int time;

    public Path() {
        connectionsInPath = new ArrayList<>();
    }

    public Path deepCopy() {
        Path newPath = new Path();
        for (ShippingNodeConnection connection : this.connectionsInPath) {
            newPath.addConnectionToPath(connection);
        }
        return newPath;
    }

    public Path concat(Path addPath) {
        Path newPath = this.deepCopy();
        for (ShippingNodeConnection connection : addPath.getConnections()) {
            newPath.addConnectionToPath(connection);
        }
        return newPath;
    }

    public ArrayList<ShippingNodeConnection> getConnections() {
        return connectionsInPath;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void addTransitTime(int transitTime) {
        this.time += transitTime;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addConnectionToPath(ShippingNodeConnection connection) {
        this.connectionsInPath.add(connection);
        this.cost += connection.getCost();
        this.time += connection.getTime();
    }

    public boolean contains(ShippingNodeConnection connection) {
        return connectionsInPath.contains(connection);
    }

    public boolean containsExcludingLast(ShippingNode node) {
        int size = connectionsInPath.size();
        for (int i = 0; i < size - 1; i++) { // Exclude the last connection
            ShippingNodeConnection connection = connectionsInPath.get(i);
            if (node.equals(connection.getOriginNode()) || node.equals(connection.getDestinationNode())) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return connectionsInPath.isEmpty();
    }

    public boolean connectionIsVistedEarlier(ShippingNodeConnection testconnection) {
        int size = connectionsInPath.size();

        for (int i = 0; i < size - 1; i++) {
            if (connectionsInPath.get(i).isSimilarConnection(testconnection)) {
                return true;
            }
        }
        return false;
    }

    public boolean nodeIsVisitedEarlier(ShippingNode node) {
        for (ShippingNodeConnection connection : getConnections()) {
            if (connection.getOriginNode().equals(node) || connection.getDestinationNode().equals(node)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Path Cost is " + cost).append("\n");
        builder.append("Path Time is " + time).append("\n");
        builder.append("Nodes in Path are as follows").append("\n");
        for (ShippingNodeConnection connection : connectionsInPath) {
            builder.append(connection.toString())
                    .append("\n");
        }
        return builder.toString();
    }
}
