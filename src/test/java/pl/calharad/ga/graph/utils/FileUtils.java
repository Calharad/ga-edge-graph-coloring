package pl.calharad.ga.graph.utils;

import java.util.Objects;

public class FileUtils {
    public static String getPropertiesPath(String caseName) {
        return Objects.requireNonNull(FileUtils.class.getClassLoader().getResource("algorithm/" + caseName + "/algorithm.properties")).getFile();
    }

    public static String getGraphPath(String caseName) {
        return Objects.requireNonNull(FileUtils.class.getClassLoader().getResource("algorithm/" + caseName + "/algorithm.txt")).getFile();
    }
}
