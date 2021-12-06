package entities;

import java.util.ArrayList;

public class World {

    public ArrayList<City> cities = new ArrayList<>();
    public double[][] distanceMatrix;
    int xBound = 3000;
    int yBound = 3000;
    public int pheromoneWeight;
    public int visibilityWeight;
    int numberOfAnts;
    public int numberOfCities;
    public double[][] pheromoneMatrix;

    public World(int numberOfCities, int numberOfAnts, int pheromoneWeight, int visibilityWeight) {
        this.numberOfAnts = numberOfAnts;
        this.visibilityWeight = visibilityWeight;
        this.pheromoneWeight = pheromoneWeight;
        this.numberOfCities = numberOfCities;
        distanceMatrix = new double[numberOfCities][numberOfCities];
        for (int iterator = 0; iterator < numberOfCities; iterator++) {
            cities.add(new City(xBound, yBound));
        }
        calculateDistanceMatrix();
        initPheromoneMatrix();
    }

    private void calculateDistanceMatrix() {
        for (int source = 0; source < cities.size(); source++) {
            for (int destination = 0; destination < cities.size(); destination++) {
                if (source == destination) {
                    distanceMatrix[source][destination] = 0.0;
                } else {
                    double dx = cities.get(destination).x - cities.get(source).x;
                    double dy = cities.get(destination).y - cities.get(source).y;
                    distanceMatrix[source][destination] = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                }
            }
        }
    }

    private void initPheromoneMatrix() {
        for (int source = 0; source < cities.size(); source++) {
            for (int destination = 0; destination < cities.size(); destination++) {
                pheromoneMatrix[source][destination] = 1.0;
            }
        }
    }

}
