package Lab5;

public class Shipment {
    private int time;
    private String customerName;
    private ShippingNode sourceNode;
    private ShippingNode destinationNode;
    private int weigth;
    private boolean isDelivered;
    private boolean vehicleIsCommitted;
    private ShippingNode currentNode;
    private Path shippingPath;

    public int getTime() {
        return time;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ShippingNode getSourceNode() {
        return sourceNode;
    }

    public ShippingNode getDestinationNode() {
        return destinationNode;
    }

    public int getWeigth() {
        return weigth;
    }

    public Shipment(int time, String customerName, ShippingNode sourceNode, ShippingNode destinationNode, int weigth) {
        this.time = time;
        this.customerName = customerName;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.weigth = weigth;
        this.isDelivered = false;
        this.vehicleIsCommitted = false;
        this.currentNode = sourceNode;
    }

}