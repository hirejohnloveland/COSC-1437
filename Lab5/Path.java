package Lab5;

import java.util.ArrayList;

public class Path {
    private ArrayList<ShippingNode> nodesInPath;
    private int cost;
    private int time;

    public Path() {
        nodesInPath = new ArrayList<>();
    }

    public Path deepCopy() {
        Path newPath = new Path();
        for (ShippingNode node : this.nodesInPath) {
            newPath.getNodes().add(node);
        }
        newPath.setTime(this.getTime());
        newPath.setCost(this.getCost());
        return newPath;
    }

    public Path concat(Path other) {
        Path newPath = this.deepCopy();
        for (ShippingNode node : other.getNodes()) {
            newPath.addNodeToPath(node);
        }
        int newCost = newPath.getCost() + other.getCost();
        newPath.setCost(newCost);
        int newTime = newPath.getTime() + other.getTime();
        newPath.setTime(newTime);

        return newPath;
    }

    public ArrayList<ShippingNode> getNodes() {
        return nodesInPath;
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

    public void addNodeToPath(ShippingNode node, int cost, int time) {
        this.nodesInPath.add(node);
        this.cost += cost;
        this.time += time;
    }

    private void addNodeToPath(ShippingNode node) {
        this.nodesInPath.add(node);
    }

    public boolean contains(ShippingNode node) {
        return nodesInPath.contains(node);
    }

    public boolean isEmpty() {
        return nodesInPath.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Path Cost is " + cost).append("\n");
        builder.append("Path Time is " + time).append("\n");
        builder.append("Nodes in Path are as follows").append("\n");
        for (ShippingNode node : nodesInPath) {
            builder.append(node.getName()).append("\n");
        }
        return builder.toString();
    }
}
