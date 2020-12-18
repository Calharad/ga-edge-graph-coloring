package pl.calharad.ga.graph;

import pl.calharad.ga.graph.struct.Graph;

class GraphDrawer {

    public String drawGraph(Graph graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("Used colors:" ).append(graph.getEdgeColorCount()).append("\n");
        sb.append("edge : begin -> end = color\n");
        graph.getEdges().forEach(e -> sb
                .append(e.getId())
                .append(" : ")
                .append(e.getBegin().getId())
                .append(" -> ")
                .append(e.getEnd().getId())
                .append(" = ")
                .append(e.getColorId())
                .append("\n")
        );
        return sb.toString();
    }
}
