import java.util.Objects;
import java.util.Scanner;

public class TravelingSalesmanApplication {
    public static void main(String[] args) {
        int numberOfCities = 100;
        int numberOfAnts = 10;
        int pheromoneWeight = 1;
        int visibilityWeight = 2;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter for default values.");
        System.out.println("Enter number of cities, default is 100:");
        String citiesInput = scanner.nextLine();
        if (!Objects.equals(citiesInput, "")) {
            numberOfCities = Integer.parseInt(citiesInput);
        }
//        System.out.println("Enter number of ants, default is 10:");
//        String startingTemperatureInput = scanner.nextLine();
//        if (!Objects.equals(startingTemperatureInput, "")) {
//            startingTemperature = Double.parseDouble(startingTemperatureInput);
//        }
//        System.out.println("Enter cooling factor, default is 0.8:");
//        String coolingFactorInput = scanner.nextLine();
//        if (!Objects.equals(coolingFactorInput, "")) {
//            coolingFactor = Double.parseDouble(coolingFactorInput);
//        }
//        System.out.println("Enter iterations per Temperature, default is 100:");
//        String iterationsPerTemperatureInput = scanner.nextLine();
//        if (!Objects.equals(iterationsPerTemperatureInput, "")) {
//            iterationsPerTemperature = Integer.parseInt(iterationsPerTemperatureInput);
//        }
//        System.out.println("Enter max pair of cities to swap, default is number of cities - 2:");
//        String maxCitySwapInput = scanner.nextLine();
//        if (!Objects.equals(maxCitySwapInput, "")) {
//            maxCitySwap = Integer.parseInt(maxCitySwapInput);
//        } else {
//            maxCitySwap = numberOfCities - 2;
//        }
//        System.out.println("Enter final temperature, default is 5.0:");
//        String finalTemperatureInput = scanner.nextLine();
//        if (!Objects.equals(finalTemperatureInput, "")) {
//            finalTemperature = Double.parseDouble(finalTemperatureInput);
//        }



//        World world = new World(numberOfCities, startingTemperature, coolingFactor, iterationsPerTemperature
//                , finalTemperature, maxCitySwap);
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
//        TourGraph tourGraph = new TourGraph(world, bestRoute);
//        tourGraph.pack();
//        RefineryUtilities.centerFrameOnScreen(tourGraph);
//        tourGraph.setVisible(true);
    }
}
