package pl.calharad.ga.graph.io;

import pl.calharad.ga.graph.struct.Graph;
import pl.calharad.ga.graph.util.Pair;

import java.util.List;

public class GraphReader {
    private final CustomFileReader reader = new CustomFileReader();
    private int edgeCount = 0;

    public Graph readGraph(String fileName) {
        List<String> lines = reader.readFile(fileName);
        getEdgeCount(lines);
        return buildGraph(lines);
    }

    private void getEdgeCount(List<String> lines) {
        String edgeCountStr = lines.remove(0)
                .replace("<", "")
                .replace(">", "");
        edgeCount = convertOrThrow(edgeCountStr);
    }

    private Graph buildGraph(List<String> lines) {
        Graph graph = new Graph();
        for (String line: lines) {
            resolveEdge(line, graph);
        }
        if(edgeCount != graph.getEdgeCount()) {
            throw new FileException("Number of edge count doesn't fit with real edge count");
        }
        return graph;
    }

    private void resolveEdge(String line, Graph graph) {
        Pair<Integer, String> edgePair = getSingleValue(line);
        Pair<Integer, String> startPair = getSingleValue(edgePair.getLast());
        Pair<Integer, String> endPair = getSingleValue(startPair.getLast());
        graph.addEdge(edgePair.getFirst(), startPair.getFirst(), endPair.getFirst());
    }

    private Pair<Integer, String> getSingleValue(String str) {
        int begin = str.indexOf("<");
        int end = str.indexOf(">");
        if(begin == -1 || end == -1) {
            throw new FileException("Illegal content of file. Aborting . . .");
        }
        String valueStr = str.substring(begin + 1, end);
        Integer val = convertOrThrow(valueStr);
        String nextStr = str.substring(end + 1);
        return new Pair<>(val, nextStr);
    }

    private Integer convertOrThrow(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new FileException("Couldn't convert value from file");
        }
    }
}
