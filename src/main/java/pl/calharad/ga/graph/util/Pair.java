package pl.calharad.ga.graph.util;

import lombok.Getter;

@Getter
public class Pair <T, U> {
    private final T first;
    private final U last;

    public Pair(T first, U last) {
        this.first = first;
        this.last = last;
    }
}
