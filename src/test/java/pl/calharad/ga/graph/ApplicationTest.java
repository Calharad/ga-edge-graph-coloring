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

    @Test
    public void testApplication_case_1() {
        String caseName = "case_1";
        Application.launch(FileUtils.getGraphPath(caseName), FileUtils.getPropertiesPath(caseName));
    }

    @Test
    public void testApplication_case_2() {
        String caseName = "case_2";
        Application.launch(FileUtils.getGraphPath(caseName), FileUtils.getPropertiesPath(caseName));
    }

    @Test
    public void testApplication_case_3() {
        String caseName = "case_3";
        Application.launch(FileUtils.getGraphPath(caseName), FileUtils.getPropertiesPath(caseName));
    }
}
