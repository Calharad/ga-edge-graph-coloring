package pl.calharad.ga.graph.algorithm;

import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.Limits;
import io.jenetics.util.ISeq;
import pl.calharad.ga.graph.props.AlgorithmProperties;
import pl.calharad.ga.graph.struct.Edge;
import pl.calharad.ga.graph.struct.Graph;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class AlgorithmLauncher {

    public Graph launch(AlgorithmProperties properties, Graph graph) {
        var problem = new GraphEdgeColoringProblem(graph);
        Engine<IntegerGene, Integer> e = Engine.builder(problem)
                .minimizing()
                .populationSize(properties.population)
                .alterers(
                        new Mutator<>(properties.mutationChance),
                        new MultiPointCrossover<>(properties.crossoverChance)
                )
                .build();
        Instant begin = Instant.now();
        Phenotype<IntegerGene, Integer> result = e.stream()
                .limit(Limits.bySteadyFitness(graph.getEdgeCount() * 100))
                .collect(EvolutionResult.toBestPhenotype());
        Instant end = Instant.now();
        long millis = Duration.between(begin, end).toMillis();
        System.out.printf("Execution time: %dms%n", millis);
        System.out.printf("Generation: %d%n", result.generation());
        ISeq<Integer> values = problem.codec().decode(result.genotype());
        values = beautifyResults(values);
        writeValues(values, graph);
        return graph;
    }

    private void writeValues(ISeq<Integer> values, Graph graph) {
        ISeq<Edge> edges = ISeq.of(graph.getEdges());
        for(int i=0; i<graph.getEdgeCount(); i++) {
            edges.get(i).setColorId(values.get(i));
        }
    }

    private ISeq<Integer> beautifyResults(ISeq<Integer> seq) {
        Map<Integer, Integer> colorsCount = new HashMap<>();
        Map<Integer, Integer> mappings = new HashMap<>();
        for(int i: seq) {
            colorsCount.putIfAbsent(i, 1);
            colorsCount.computeIfPresent(i, (j, v) -> v+1);
        }
        int min = 1;
        do {
            while(colorsCount.containsKey(min)) {
                min++;
            }
            int max = colorsCount.keySet().stream().mapToInt(i -> i).max().orElse(0);
            if(min > max) {
                break;
            }
            int value = colorsCount.remove(max);
            colorsCount.put(min, value);
            mappings.put(max, min);
        } while(true);
        return seq.map(i -> mappings.getOrDefault(i, i));
    }


}
