package Lab5;

public class Vehicle {

    private VehicleType type;
    private String name;
    private ShippingNode currentNode;

    public Vehicle(VehicleType vehicleType, String name, ShippingNode startingNode) {
        this.type = vehicleType;
        this.name = name;
        this.currentNode = startingNode;

    }

    public String getName() {
        return this.name;
    }

    public ShippingNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(ShippingNode nextNode) {
        if (currentNode != nextNode) {
            System.out
                    .println("Vehicle " + name + " moved from " + currentNode.getName() + " to " + nextNode.getName());
            this.currentNode = nextNode;
        } else {
            System.out.println("Vehicle " + name + " started and ended at " + currentNode.getName());
        }

    }

    public boolean canTraverse(ShippingNodeConnection connection) {
        VehicleType connectionType = connection.getType();
        return (connectionType.getType() == type.getType());
    }

    public Path getPathToNode(ShippingNode target) {
        return PathFinder.findPath(this.currentNode, target, this);
    }
}