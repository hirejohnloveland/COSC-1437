package Lab5;

public class Shipment {
    private int time;
    private String customerName;
    private ShippingNode sourceNode;
    private ShippingNode destinationNode;
    private int weigth;
    private int eta;
    private boolean isDelivered;
    private Vehicle reservedVehicle;
    private Path shippingPath = new Path();

    public int getEta() {
        return eta;
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

    public Vehicle getReservedVehicle() {
        return reservedVehicle;
    }

    public ShippingNode getCurrentNode() {
        return shippingPath.getCurrentNode();
    }

    public boolean isShipmentDelivered() {
        return isDelivered;
    }

    public void commitShipmentToVehicle(Vehicle vehicle) {
        this.reservedVehicle = vehicle;
    }

    public Path getShippingPath() {
        return shippingPath;
    }

    public ShippingNodeConnection getNextConnection() {
        return shippingPath.getNextConnection();
    }

    public void addTransitTime(int transitTime) {
        shippingPath.setTime(shippingPath.getTime() + transitTime);
    }

    public void advanceShipment() {
        if (shippingPath.getCurrentNode() == destinationNode) {
            isDelivered = true;
            reservedVehicle = null;
            return;
        } else
            shippingPath.advancePath();
    }

    public void releaseShipmentFromVehicle() {
        reservedVehicle = null;
        if (shippingPath.getCurrentNode() == destinationNode) {
            isDelivered = true;
        }
    }

    public Shipment(int time, String customerName, ShippingNode sourceNode, ShippingNode destinationNode, int weigth) {
        this.time = time;
        this.customerName = customerName;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.weigth = weigth;
        this.isDelivered = false;
        this.reservedVehicle = null;
        shippingPath = PathFinder.findPath(sourceNode, destinationNode, null);
        eta = shippingPath.getTime();
        System.out.println("Shipment has an eta of " + eta);
    }

}