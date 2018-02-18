package com.swetha.service;

import com.swetha.model.DirectedGraph;
import com.swetha.model.Node;
import com.swetha.model.Path;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class RailRoadServiceImpl implements RailRoadService {

    private DirectedGraph<String> graph;
    private Map<String, String> cities;
    private Map<String, List<String>> inputMap;
    private static final String SHORTEST_PATH = "shortest_path";
    private static final String EXACT_STOPS = "exact_stops";
    private static final String MAX_STOPS = "max_stops";
    private static final String DISTANCE = "distance";
    private static final String MAX_DISTANCE = "max_distance";


    public RailRoadServiceImpl() {
        graph = new DirectedGraph<>();
        cities = new HashMap<>();
        inputMap = new HashMap<>();
    }

    @Override
    public void initialize(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(path));
        while (in.hasNext()) {
            String input = in.next();
            parse(input);
        }
    }

    @Override
    public void findDistance() {
        if (inputMap.containsKey(DISTANCE)) {
            List<String> distanceStrings = inputMap.get(DISTANCE);
            for (String input : distanceStrings) {
                try {
                    System.out.println("distance for " + input + " is: " + distanceOfRoute(input.split(",")));
                } catch (Exception e) {
                    System.out.println("distance for " + input + " is: " + e.getMessage());
                }
            }
        }

    }

    @Override
    public void findCountWithMaxStops() {
        if (inputMap.containsKey(MAX_STOPS)) {
            List<String> distanceStrings = inputMap.get(MAX_STOPS);
            for (String input : distanceStrings) {
                try {
                    String[] inputs = input.split(",");
                    System.out.println("Number of trips with max stops for " + input + " is: " +
                            numberOfTripsWithMaxStops(inputs[0], inputs[1], Integer.valueOf(inputs[2])));
                } catch (Exception e) {
                    System.out.println("Number of trips with max stops for " + input + " is: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void findCountWithExactStops() {
        if (inputMap.containsKey(EXACT_STOPS)) {
            List<String> distanceStrings = inputMap.get(EXACT_STOPS);
            for (String input : distanceStrings) {
                try {
                    String[] inputs = input.split(",");
                    System.out.println("Number of trips with exact stops for " + input + " is: " +
                            numberOfTripsWithExactStops(inputs[0], inputs[1], Integer.valueOf(inputs[2])));
                } catch (Exception e) {
                    System.out.println("Number of trips with exact stops for " + input + " is: " + e.getMessage());
                }
            }
        }
    }


    @Override
    public void findShortestLength() {
        if (inputMap.containsKey(SHORTEST_PATH)) {
            List<String> distanceStrings = inputMap.get(SHORTEST_PATH);
            for (String input : distanceStrings) {
                try {
                    String[] inputs = input.split(",");
                    System.out.println("shortest path for " + input + " is: " + lengthOfShortestRoute(inputs[0], inputs[1]));
                } catch (Exception e) {
                    System.out.println("shortest path for " + input + " is: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void findCountWithMaxDistance() {
        if (inputMap.containsKey(MAX_DISTANCE)) {
            List<String> distanceStrings = inputMap.get(MAX_DISTANCE);
            for (String input : distanceStrings) {
                try {
                    String[] inputs = input.split(",");
                    System.out.println("Number of trips with max distance for " + input + " is: " +
                            numberOfDiffRoutesWithMaxDistance(inputs[0], inputs[1], Integer.valueOf(inputs[2])));
                } catch (Exception e) {
                    System.out.println("Number of trips with max distance for " + input + " is: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public int distanceOfRoute(String... cityNames) throws Exception {
        List<String> cityList = new ArrayList<>();
        for (String name : cityNames) {
            cityList.add(cities.get(name));
        }
        return graph.distance(cityList);
    }


    @Override
    public int numberOfTripsWithMaxStops(String start, String end, int maxStops) throws Exception {
        Validate(start, end);
        // Predicate functional interface provided by java 8
        Predicate<Path> terminate = path -> path.hopCount() > maxStops;
        Predicate<Path> condition = path -> end.equals(path.last());

        return count(start, terminate, condition);
    }

    @Override
    public int numberOfTripsWithExactStops(String start, String end, int stops) throws Exception {
        Validate(start, end);
        // Predicate functional interface provided by java 8
        Predicate<Path> terminate = path -> path.hopCount() > stops;
        Predicate<Path> condition = path -> end.equals(path.last()) && path.hopCount() == stops;
        return count(start, terminate, condition);
    }


    @Override
    public int lengthOfShortestRoute(String start, String end) throws Exception {
        Validate(start, end);

        graph.dijkstra(start, end);

        Node endNode = graph.getNode(end);
        Path path = endNode.path(graph.getNode(start), graph);

        if (path.getNodes().isEmpty()) {
            throw new Exception("NODE NOT REACHABLE");
        } else {
            return path.distance();
        }
    }


    public int numberOfDiffRoutesWithMaxDistance(String start, String end, int maxDistance) throws Exception {
        Validate(start, end);
        // Predicate functional interface provided by java 8
        Predicate<Path> terminate = path -> path.distance() > maxDistance;
        Predicate<Path> condition = path -> end.equals(path.last()) && path.distance() <= maxDistance;
        return count(start, terminate, condition);

    }

    private int count(String start, Predicate<Path> terminate, Predicate<Path> condition) {
        int total = 0;
        // Start a DFS from each neighbour
        for (String neighbour : graph.getNeighbours(start)) {
            Path path = new Path();
            path.appendNode(start, 0);
            path.appendNode(neighbour, graph.weightForEdge(start, neighbour));
            total += graph.dfs(neighbour, terminate, condition, path);
        }
        return total;
    }

    private void parse(String input) {
        if (input != null && input.length() > 0) {
            String key = input.split(";")[0].toLowerCase();
            String value = input.split(";")[1];

            if (key.equals("graph")) {
                initGraphInput(value);
            } else {
                if (!inputMap.containsKey(key)) {
                    inputMap.put(key, new ArrayList<>());
                }
                inputMap.get(key).add(value);
            }
        }
    }

    private void initGraphInput(String value) {
        if (value != null && value.length() > 1) {
            String[] edges = value.split(",");
            for (String edge : edges) {
                String start = String.valueOf(edge.charAt(0));
                String end = String.valueOf(edge.charAt(1));
                int weight;

                try {
                    weight = Integer.parseInt(edge.substring(2));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Please provide weight");
                }
                addCity(start);
                addCity(end);
                graph.addEdge(start, end, weight);
            }
        }

    }

    private void addCity(String city) {
        graph.addNode(city);
        if (!cities.containsKey(city)) {
            cities.put(city, city);
        }
    }

    private void Validate(String start, String end) throws Exception {
        if (graph.contains(start) && graph.contains(end)) {
            // all good
            return;
        }
        throw new Exception("Node does not exists");
    }

}
