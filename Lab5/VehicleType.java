package Lab5;

public enum VehicleType {
    ROAD(5, "road"),
    AIR(25, "air"),
    WATER(15, "water"),
    RAIL(100, "rail");

    private final int weight;
    private final String type;

    VehicleType(int weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getType() {
        return this.type;
    }

    public static VehicleType fromString(String typeStr) {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.getType().equalsIgnoreCase(typeStr)) {
                return vehicleType;
            }
        }
        throw new IllegalArgumentException("No VehicleType with type " + typeStr + " found.");
    }
}