package entities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Route {

    ArrayList<Integer> sequence = new ArrayList<>();
    double totalDistance = 0.0;
    public double[][] visibilityMatrix;
    int numberOfCities;

    public Route(World world) {
        this.numberOfCities = world.numberOfCities;
        initVisibilityMatrix(world.distanceMatrix);
        calculateRoute(world.pheromoneMatrix, world.pheromoneWeight, world.visibilityWeight);
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

    }

    public void initVisibilityMatrix(double[][] distanceMatrix) {
        for (int source = 0; source < numberOfCities; source++) {
            for (int destination = 0; destination < numberOfCities; destination++) {
                visibilityMatrix[source][destination] = 1 / distanceMatrix[source][destination];
            }
        }
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

}
