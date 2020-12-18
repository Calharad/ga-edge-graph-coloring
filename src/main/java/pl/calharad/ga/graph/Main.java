package pl.calharad.ga.graph;

import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("Error: Data file not provided");
            return;
        }
        String fileName = args[0];
        String propName = null;
        if (args.length > 1) {
            propName = args[1];
        }
        Application.launch(fileName, propName);
    }
}
