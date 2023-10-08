package Lab5;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileParser reader = new FileParser("squares.txt");
        ArrayList<ShippingNode> nodes = reader.readFile();

        FlowController flow = new FlowController();
        flow.optimizeNetwork(nodes);

    }

}
