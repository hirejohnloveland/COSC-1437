package Lab5;

public class OutputResults {
    public static void printToConsole(Path path, Path vehiclePath) {
        System.out.println("The vehicle Path to start is as follows: ");
        for (ShippingNode node : vehiclePath.getNodes()) {
            System.out.println(node.getName());
        }
        System.out.println("Transit cost to start is " + vehiclePath.getCumulativeCost());
        System.out.println("Transit time to start is " + vehiclePath.getCumulativeTime());

        System.out.println("The vehicle Path from start to end is as follows: ");
        for (ShippingNode node : path.getNodes()) {
            System.out.println(node.getName());
        }
        System.out.println("Transit cost from start is " + path.getCumulativeCost());
        System.out.println("Transit time from start is " + path.getCumulativeTime());

        System.out.println("Cumulative Time is " + (path.getCumulativeTime() + vehiclePath.getCumulativeTime()));
        System.out.println("Cumulative Cost is " + (path.getCumulativeCost() + vehiclePath.getCumulativeCost()));
    }
}
