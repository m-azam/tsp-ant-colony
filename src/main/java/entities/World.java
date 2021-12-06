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
    double evaporationCoefficient;
    ArrayList<Route> antRoutes = new ArrayList<>();

    public World(int numberOfCities, int numberOfAnts, int pheromoneWeight, int visibilityWeight, double evaporationCoefficient) {
        this.numberOfAnts = numberOfAnts;
        this.visibilityWeight = visibilityWeight;
        this.pheromoneWeight = pheromoneWeight;
        this.numberOfCities = numberOfCities;
        this.evaporationCoefficient = evaporationCoefficient;
        distanceMatrix = new double[numberOfCities][numberOfCities];
        pheromoneMatrix = new double[numberOfCities][numberOfCities];
        for (int iterator = 0; iterator < numberOfCities; iterator++) {
            cities.add(new City(xBound, yBound));
        }
        calculateDistanceMatrix();
        initPheromoneMatrix();
        for (int index = 0; index < numberOfAnts; index++) {
            if (index == 1054) {
                int i = 0;
            }
            Route antRoute = new Route(this);
            antRoutes.add(antRoute);
            updatePheromone(antRoute);
        }
    }

    public ArrayList<Route> getAntRoutes() {
        return antRoutes;
    }

    public Route getBestRoute() {
        Route bestRoute = antRoutes.get(antRoutes.size() - 1);
        for (Route route : antRoutes) {
            if (route.totalDistance < bestRoute.totalDistance) {
                bestRoute = route;
            }
        }
        return bestRoute;
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

    private void updatePheromone(Route antRoute) {
        double totalDistance = antRoute.totalDistance;
        decayPheromone();
        for (int index = 0; index < antRoute.sequence.size() - 1; index++) {
            pheromoneMatrix[index][index + 1] += (1 / totalDistance);
        }
    }

    private void decayPheromone() {
        for (int source = 0; source < cities.size(); source++) {
            for (int destination = 0; destination < cities.size(); destination++) {
                pheromoneMatrix[source][destination] = pheromoneMatrix[source][destination] * (1 - evaporationCoefficient);
            }
        }
    }

}
