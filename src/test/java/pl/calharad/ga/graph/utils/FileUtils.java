package pl.calharad.ga.graph.utils;

public class FileUtils {
    public static String getPropertiesPath(String caseName) {
        return FileUtils.class.getClassLoader().getResource("algorithm/" + caseName + "/algorithm.properties").getFile();
    }

    public static String getGraphPath(String caseName) {
        return FileUtils.class.getClassLoader().getResource("algorithm/" + caseName + "/algorithm.txt").getFile();
    }
}
