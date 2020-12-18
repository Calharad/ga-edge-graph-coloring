package pl.calharad.ga.graph.struct;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vertex {
    @Getter
    @EqualsAndHashCode.Include
    private final Integer id;
    @Getter
    private final Set<Edge> connectedEdges = new HashSet<>();

    public boolean edgeColorsUnique() {
        return connectedEdges.stream().mapToInt(Edge::getColorId).distinct().count() == connectedEdges.size();
    }

    public int countDuplicates() {
        return connectedEdges.stream().map(Edge::getColorId).collect(Collectors.groupingBy(i -> i))
                .values().stream().filter(l -> l.size() > 1).mapToInt(i -> 1).sum();
    }
}
