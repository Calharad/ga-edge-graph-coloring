package pl.calharad.ga.graph.props;

import io.vavr.control.Try;

import java.util.Properties;

public class AlgorithmProperties {

    private static final String POPULATION = "algorithm.population";
    private static final String MUTATION_CHANCE = "algorithm.mutation_chance";
    private static final String CROSSOVER_CHANCE = "algorithm.crossover_chance";
    public static final int POPULATION_DEFAULT = 50;
    public static final double MUTATION_CHANCE_DEFAULT = 0.01;
    public static final double CROSSOVER_CHANCE_DEFAULT = 0.05;

    public final Integer population;
    public final Double mutationChance;
    public final Double crossoverChance;

    public AlgorithmProperties(Properties properties) {
        population = Try.of(() -> properties.getProperty(POPULATION))
                .mapTry(Integer::parseInt)
                .getOrElse(POPULATION_DEFAULT);
        mutationChance = Try.of(() -> properties.getProperty(MUTATION_CHANCE))
                .mapTry(Double::parseDouble)
                .getOrElse(MUTATION_CHANCE_DEFAULT);
        crossoverChance = Try.of(() -> properties.getProperty(CROSSOVER_CHANCE))
                .mapTry(Double::parseDouble)
                .getOrElse(CROSSOVER_CHANCE_DEFAULT);
    }

    public AlgorithmProperties() {
        population = POPULATION_DEFAULT;
        mutationChance = MUTATION_CHANCE_DEFAULT;
        crossoverChance = CROSSOVER_CHANCE_DEFAULT;
    }
}
