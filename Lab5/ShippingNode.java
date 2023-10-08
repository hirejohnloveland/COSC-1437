package Lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShippingNode {
    private String name;
    private Path pathToGetTo = new Path();
    private ArrayList<ShippingNodeConnection> neighbors = new ArrayList<>();

    public ShippingNode(String name) {
        this.name = name;
        pathToGetTo = new Path();
    }

    public String getName() {
        return name;
    }

    public Path getPath() {
        return this.pathToGetTo;
    }

    public void setPath(Path path) {
        this.pathToGetTo = path;
    }

    public int getCost() {
        return pathToGetTo.getCumulativeCost();
    }

    public void setCost(int cost) {
        this.pathToGetTo.setCumulativeCost(cost);
    }

    public int getTime() {
        return this.pathToGetTo.getCumulativeTime();
    }

    public void setTime(int time) {
        this.pathToGetTo.setCumulativeTime(time);
    }

    public ArrayList<ShippingNodeConnection> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<ShippingNodeConnection> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(ShippingNodeConnection connection) {
        this.neighbors.add(connection);
    }

    public static ShippingNode getNodeByName(ArrayList<ShippingNode> nodes, String name) {
        return nodes.stream()
                .filter(node -> name.equals(node.getName()))
                .findFirst()
                .orElse(null);
    }

    public static void connectNeighbors(int id, ShippingNode fromNode, ShippingNode toNode, int cost, int time,
            VehicleType type) {
        ShippingNodeConnection newConnection = new ShippingNodeConnection(id, fromNode, toNode, cost, time, type);
        fromNode.addNeighbor(newConnection);
    }

    public static void resetNodes(List<ShippingNode> nodes) {
        for (ShippingNode node : nodes) {
            node.setPath(new Path());

        }
    }
}
