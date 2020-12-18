package pl.calharad.ga.graph.struct;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Edge {
    @EqualsAndHashCode.Include
    private final Integer id;
    @Setter
    private Vertex begin;
    @Setter
    private Vertex end;
    @Setter
    private Integer colorId;
}
