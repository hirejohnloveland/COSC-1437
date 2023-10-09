package Lab5;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileParser reader = new FileParser("squares.txt");
        FileData data = reader.readFile();

        FlowController flow = new FlowController();
        flow.optimizeNetwork(data.nodes, data.vehicles);
    }
}
