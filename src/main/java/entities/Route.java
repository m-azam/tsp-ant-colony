package entities;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Route {

    ArrayList<Integer> sequence = new ArrayList<>();
    double totalDistance = 0.0;
    public double[][] visibilityMatrix;
    int numberOfCities;
    HashSet<Integer> visitedCities = new HashSet<>();

    public Route(World world) {
        this.numberOfCities = world.numberOfCities;
        initVisibilityMatrix(world.distanceMatrix);
        calculateRoute(world.pheromoneMatrix, world.pheromoneWeight, world.visibilityWeight);
        calculateTotalDistance(world.distanceMatrix);
    }

    public void calculateTotalDistance(double[][] distanceMatrix) {
        totalDistance = 0.0;
        for (int city = 1; city < sequence.size(); city++) {
            totalDistance = totalDistance + distanceMatrix[sequence.get(city - 1)][sequence.get(city)];
        }
        totalDistance = totalDistance + distanceMatrix[sequence.get(sequence.size() - 1)][sequence.get(0)];
    }

    public void calculateRoute(double[][] pheromoneMatrix, int pheromoneWeight, int visibilityWeight) {
        int startingCity = ThreadLocalRandom.current().nextInt(numberOfCities);
        setVisitStatus(startingCity);
        int currentCity = startingCity;
        int[] range = IntStream.range(0, numberOfCities).toArray();
        while (sequence.size() < numberOfCities) {
            double[] nextVisitProbabilityDistribution = new double[numberOfCities];
            for (int index = 0; index < numberOfCities; index++) {
                nextVisitProbabilityDistribution[index] = -1;
            }
            nextVisitProbabilityDistribution[currentCity] = 0;
            for (int index = 0; index < numberOfCities; index++) {
                if (nextVisitProbabilityDistribution[index] == 0) {
                    continue;
                }
                double pheromone = pheromoneMatrix[currentCity][index];
                double visibility = visibilityMatrix[currentCity][index];
                nextVisitProbabilityDistribution[index] = Math.pow(pheromone, pheromoneWeight)
                        * Math.pow(visibility, visibilityWeight);
            }
            convertToProbabilities(nextVisitProbabilityDistribution);
            EnumeratedIntegerDistribution distribution = new EnumeratedIntegerDistribution(range
                    , nextVisitProbabilityDistribution);;
            currentCity = distribution.sample();
            setVisitStatus(currentCity);
        }
    }

    public void initVisibilityMatrix(double[][] distanceMatrix) {
        visibilityMatrix = new double[numberOfCities][numberOfCities];
        for (int source = 0; source < numberOfCities; source++) {
            for (int destination = 0; destination < numberOfCities; destination++) {
                if (distanceMatrix[source][destination] != 0) {
                    visibilityMatrix[source][destination] = 1 / distanceMatrix[source][destination];
                }
            }
        }
    }

    public void setVisitStatus(int city) {
        visitedCities.add(city);
        for (int index = 0; index < numberOfCities; index++) {
            visibilityMatrix[index][city] = 0;
        }
        sequence.add(city);
    }

    private void convertToProbabilities(double[] nextVisitProbabilityDistribution) {
        double sum = 0.0;
        for (double probability : nextVisitProbabilityDistribution) {
            sum = sum + probability;
        }
        for (int index = 0; index < numberOfCities; index++) {
            nextVisitProbabilityDistribution[index] = nextVisitProbabilityDistribution[index] / sum;
        }
    }


    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

}
