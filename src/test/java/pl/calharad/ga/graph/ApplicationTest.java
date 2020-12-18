package pl.calharad.ga.graph;

import org.junit.Test;
import pl.calharad.ga.graph.utils.FileUtils;

import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void testApplication_test() {
        String caseName = "test";
        Application.launch(FileUtils.getGraphPath(caseName), FileUtils.getPropertiesPath(caseName));
    }
}
