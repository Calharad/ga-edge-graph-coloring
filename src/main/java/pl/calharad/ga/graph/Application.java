package pl.calharad.ga.graph;

import pl.calharad.ga.graph.algorithm.AlgorithmLauncher;
import pl.calharad.ga.graph.io.FileException;
import pl.calharad.ga.graph.io.GraphReader;
import pl.calharad.ga.graph.props.AlgorithmProperties;
import pl.calharad.ga.graph.props.PropertiesReader;
import pl.calharad.ga.graph.props.PropertyException;
import pl.calharad.ga.graph.struct.Graph;
import pl.calharad.ga.graph.util.Procedure;

public class Application {

    public static void launch(String filePath, String propPath) {
        Application app = new Application();
        app.filePath = filePath;
        app.propPath = propPath;
        app.run();
    }

    private Graph loadGraph() {
        GraphReader reader = new GraphReader();
        return reader.readGraph(filePath);
    }

    private AlgorithmProperties loadProperties() {
        PropertiesReader reader = new PropertiesReader();
        return reader.getAlgorithmProperties(propPath);
    }

    private String filePath;
    private String propPath;
    private final GraphDrawer drawer;

    protected Application() {
        drawer = new GraphDrawer();
    }

    protected void run() {
        safeRun(this::runInternal);
    }

    private void runInternal() {
        AlgorithmProperties properties = loadProperties();
        Graph graph = loadGraph();
        AlgorithmLauncher algorithm = new AlgorithmLauncher();
        graph = algorithm.launch(properties, graph);
        System.out.println("Graph solved.");
        System.out.println(drawer.drawGraph(graph));
    }

    private void safeRun(Procedure proc) {
        try {
            proc.run();
        } catch (PropertyException | FileException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

}
