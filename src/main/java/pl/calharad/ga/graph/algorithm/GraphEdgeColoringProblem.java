package pl.calharad.ga.graph.algorithm;

import io.jenetics.*;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Problem;
import io.jenetics.util.ISeq;
import io.jenetics.util.IntRange;
import pl.calharad.ga.graph.struct.Edge;
import pl.calharad.ga.graph.struct.Graph;
import pl.calharad.ga.graph.struct.Vertex;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

class GraphEdgeColoringProblem implements Problem<ISeq<Integer>, IntegerGene, Integer> {

    private final int size;
    private final Graph baseGraph;

    public GraphEdgeColoringProblem(Graph graph) {
        this.baseGraph = requireNonNull(graph);
        size = baseGraph.getEdgeCount();
    }

    @Override
    public Function<ISeq<Integer>, Integer> fitness() {
        return seq -> {
            ISeq<Edge> edges = getEdge(seq);
            int duplicates = edges.stream()
                    .flatMap(e -> Stream.of(e.getBegin(), e.getEnd()))
                    .distinct().mapToInt(Vertex::countDuplicates).sum();
            if(duplicates > 0) {
                return (duplicates + 1) * size;
            }
            return edges.stream().map(Edge::getColorId)
                    .distinct().mapToInt(v -> 1).sum();
        };
    }

    @Override
    public Codec<ISeq<Integer>, IntegerGene> codec() {
        return Codec.of(
                Genotype.of(IntegerChromosome.of(IntRange.of(1, size + 1), size)),
                gt -> gt.chromosome().stream().map(Gene::allele).collect(ISeq.toISeq())
        );
    }

    private ISeq<Edge> getEdge(ISeq<Integer> values) {
        Graph graph = baseGraph.copy();
        ISeq<Edge> seq = ISeq.of(graph.getEdges());
        for(int i=0; i<values.size(); i++) {
            seq.get(i).setColorId(values.get(i));
        }
        return seq;
    }
}
