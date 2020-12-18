package pl.calharad.ga.graph.algorithm;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.calharad.ga.graph.io.GraphReader;
import pl.calharad.ga.graph.props.AlgorithmProperties;
import pl.calharad.ga.graph.props.PropertiesReader;
import pl.calharad.ga.graph.struct.Edge;
import pl.calharad.ga.graph.struct.Graph;
import pl.calharad.ga.graph.utils.FileUtils;

public class AlgorithmLauncherTest {

    AlgorithmLauncher launcher;

    @Before
    public void init() {
        launcher = new AlgorithmLauncher();
    }

    private AlgorithmProperties properties(String caseName) {
        String s = FileUtils.getPropertiesPath(caseName);
        return new PropertiesReader().getAlgorithmProperties(s);
    }

    private Graph graph(String caseName) {
        String s = FileUtils.getPropertiesPath(caseName);
        return new GraphReader().readGraph(s);
    }

    @Test
    public void launch_case_1() {
        AlgorithmProperties props = properties("case_1");
        Graph graph = graph("case_1");
        graph = launcher.launch(props, graph);
        assertEquals(4, graph.getEdges().stream().map(Edge::getColorId).distinct().count());
    }
}
