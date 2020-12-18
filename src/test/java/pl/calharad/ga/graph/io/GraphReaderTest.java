package pl.calharad.ga.graph.io;

import org.junit.Test;
import pl.calharad.ga.graph.struct.Graph;
import static org.junit.Assert.*;

public class GraphReaderTest {

    @Test
    public void readGraph() throws FileException {
        String s = getClass().getClassLoader().getResource("testGraphReader.txt").getFile();
        GraphReader reader = new GraphReader();
        Graph graph = reader.readGraph(s);
        assertEquals(5, graph.getEdgeCount());
    }

    @Test(expected = FileException.class)
    public void readGraph_ShouldThrowFileException() throws FileException {
        String s = "testDoesNotExist.txt";
        GraphReader reader = new GraphReader();
        Graph graph = reader.readGraph(s);
    }
}
