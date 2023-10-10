package Lab5;

public class OutputResults {
    public static void printToConsole(Path path) {

        System.out.println("The Path from start to end is as follows: ");
        for (ShippingNodeConnection connection : path.getConnections()) {
            System.out.println(connection.toString());
        }
        System.out.println("Transit cost from start is " + path.getCost());
        System.out.println("Transit time from start is " + path.getTime());

    }
}
