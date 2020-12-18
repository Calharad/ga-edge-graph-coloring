package pl.calharad.ga.graph.props;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public AlgorithmProperties getAlgorithmProperties(String path) {
        if(path == null) {
            return new AlgorithmProperties();
        }
        Properties properties = loadPropertiesFile(path);
        return new AlgorithmProperties(properties);
    }

    private Properties loadPropertiesFile(String path) {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader(path));
            return prop;
        } catch (IOException e) {
            throw new PropertyException("Property file not found");
        }
    }
}
