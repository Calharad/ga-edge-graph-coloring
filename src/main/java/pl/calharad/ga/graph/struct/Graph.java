package pl.calharad.ga.graph.struct;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private final Map<Integer, Vertex> vertexes = new HashMap<>();
    private final Map<Integer, Edge> edges = new HashMap<>();

    public void addEdge(Integer edgeId, Integer beginVertex, Integer endVertex) {
        Edge edge = new Edge(edgeId);
        Vertex begin = vertexes.computeIfAbsent(beginVertex, Vertex::new);
        Vertex end = vertexes.computeIfAbsent(endVertex, Vertex::new);
        edge.setBegin(begin);
        edge.setEnd(end);
        edges.put(edgeId, edge);
        begin.getConnectedEdges().add(edge);
        end.getConnectedEdges().add(edge);
    }

    public Collection<Edge> getEdges() {
        return edges.values();
    }

    public Graph copy() {
        Graph graph = new Graph();
        edges.values().forEach(e -> graph.addEdge(e.getId(), e.getBegin().getId(), e.getEnd().getId()));
        return graph;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public int getEdgeColorCount() {
        return edges.values().stream().map(Edge::getColorId)
                .distinct().mapToInt(i -> 1).sum();
    }
}
