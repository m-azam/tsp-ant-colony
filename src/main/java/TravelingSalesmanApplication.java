import entities.Route;
import entities.World;
import org.jfree.ui.RefineryUtilities;

import java.util.Objects;
import java.util.Scanner;

public class TravelingSalesmanApplication {
    public static void main(String[] args) {
        int numberOfCities = 10;
        int numberOfAnts = 5000;
        int pheromoneWeight = 1;
        int visibilityWeight = 2;
        double evaporationCoefficient = 0.001;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter for default values.");
        System.out.println("Enter number of cities, default is 10:");
        String citiesInput = scanner.nextLine();
        if (!Objects.equals(citiesInput, "")) {
            numberOfCities = Integer.parseInt(citiesInput);
        }
        System.out.println("Enter numberOfAnts, default is 5000:");
        String numberOfAntsInput = scanner.nextLine();
        if (!Objects.equals(numberOfAntsInput, "")) {
            numberOfAnts = Integer.parseInt(numberOfAntsInput);
        }
        System.out.println("Enter numberOfAnts, default is 1:");
        String pheromoneWeightInput = scanner.nextLine();
        if (!Objects.equals(pheromoneWeightInput, "")) {
            pheromoneWeight = Integer.parseInt(pheromoneWeightInput);
        }
        System.out.println("Enter numberOfAnts, default is 2:");
        String visibilityWeightInput = scanner.nextLine();
        if (!Objects.equals(visibilityWeightInput, "")) {
            visibilityWeight = Integer.parseInt(visibilityWeightInput);
        }
        System.out.println("Enter evaporationCoefficient, default is 0.001:");
        String evaporationCoefficientInput = scanner.nextLine();
        if (!Objects.equals(evaporationCoefficientInput, "")) {
            evaporationCoefficient = Double.parseDouble(evaporationCoefficientInput);
        }



        World world = new World(numberOfCities, numberOfAnts, pheromoneWeight, visibilityWeight, evaporationCoefficient);
        Route lastRoute = world.getAntRoutes().get(world.getAntRoutes().size() - 1);
        Route bestRoute = world.getBestRoute();


//        Route route = new Route(numberOfCities, world.distanceMatrix);
//        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(world, route);
//        ArrayList<Route> allRoutes = simulatedAnnealing.fetchAllRoutes();
//        DistanceGraph distanceGraph = new DistanceGraph(allRoutes);
//        distanceGraph.pack();
//        RefineryUtilities.centerFrameOnScreen(distanceGraph);
//        distanceGraph.setVisible(true);
//        Route bestRoute = simulatedAnnealing.getBestRoute();
//        double improvement = ((allRoutes.get(0).getTotalDistance() - bestRoute.getTotalDistance())
//                / allRoutes.get(0).getTotalDistance()) * 100;
//        System.out.println("Percentage improvement from initial route"+ improvement);
        TourGraph tourGraph = new TourGraph(world, bestRoute);
        tourGraph.pack();
        RefineryUtilities.centerFrameOnScreen(tourGraph);
        tourGraph.setVisible(true);
    }
}
