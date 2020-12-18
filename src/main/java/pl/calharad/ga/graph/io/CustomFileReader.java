package pl.calharad.ga.graph.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

class CustomFileReader {

    private static final long MB_1 = 1024*1024;

    public List<String> readFile(String name) {
        File file = new File(name);
        checkFileCorrect(file);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new FileException("Failed to read file. Aborting. . .");
        }
    }


    private void checkFileCorrect(File f) {
        if(!f.exists()) {
            throw new FileException("File " + f.getAbsolutePath() + " not found");
        }
        if(!f.isFile()) {
            throw new FileException("File " + f.getAbsolutePath() + " is a directory");
        }
        if(!f.canRead()) {
            throw new FileException("File " + f.getAbsolutePath() + " cannot be read");
        }
        if(f.length() > MB_1) {
            throw new FileException("File " + f.getAbsolutePath() + " exceeds 1MB");
        }
    }
}
