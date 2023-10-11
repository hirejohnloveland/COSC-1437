package Lab5;

import java.util.ArrayList;

public class Path {
    private ArrayList<ShippingNodeConnection> connectionsInPath;
    private int cost;
    private int time;
    private Vehicle vehicle;

    public Path() {
        connectionsInPath = new ArrayList<>();
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

    public void setTime(int time) {
        this.time = time;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Path deepCopy() {
        Path newPath = new Path();
        for (ShippingNodeConnection connection : this.connectionsInPath) {
            newPath.addConnectionToPath(connection);
        }
        newPath.vehicle = this.vehicle;
        return newPath;
    }

    public void addPath(Path addPath) {
        for (ShippingNodeConnection connection : addPath.getConnections()) {
            addConnectionToPath(connection);
        }
        return;
    }

    public void addConnectionToPath(ShippingNodeConnection connection) {
        this.connectionsInPath.add(connection);
        this.cost += connection.getCost();
        this.time += connection.getTime();
    }

    public boolean isEmpty() {
        return connectionsInPath.isEmpty();
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
