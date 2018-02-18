package com.swetha.service;

import java.io.FileNotFoundException;

/**
 * The interface Rail road service.
 */
public interface RailRoadService {

    /**
     * Initializes graph by reading the input from given text file.
     *
     * @param path the path
     * @throws FileNotFoundException the file not found exception
     */
    void initialize(String path) throws Exception;

    /**
     * Find distance. Calculates the distances required as provided in the input file
     * and prints the distances to console
     */
    void findDistance();

    /**
     * Find count with max stops.
     * Calculates the count of the routes with max number of stops
     * provided in the input file and prints the number to console.
     */
    void findCountWithMaxStops();

    /**
     * Find count with exact stops.
     * Calculates the count of the routes with exact number of stops
     * provided in the input file and prints the number to console.
     */
    void findCountWithExactStops();

    /**
     * Find shortest length.
     * finds the shortest distance between the source and destination
     * provided in the input file and prints the shortest distance to console.
     */
    void findShortestLength();

    /**
     * Find count with max distance.
     * Calculates the count of the routes with a maximum distance as
     * provided in the input file and prints the number to console.
     */
    void findCountWithMaxDistance();

    /**
     * Distance of route int.
     *
     * @param cityNames the city names
     * @return the int
     * @throws Exception the exception
     */
    int distanceOfRoute(String... cityNames) throws Exception;

    /**
     * Number of trips with max stops int.
     *
     * @param start    the start
     * @param end      the end
     * @param maxStops the max stops
     * @return the int
     * @throws Exception the exception
     */
    int numberOfTripsWithMaxStops(String start, String end, int maxStops) throws Exception;

    /**
     * Number of trips with exact stops int.
     *
     * @param start the start
     * @param end   the end
     * @param stops the stops
     * @return the int
     * @throws Exception the exception
     */
    int numberOfTripsWithExactStops(String start, String end, int stops) throws Exception;

    /**
     * Length of shortest route int.
     *
     * @param start the start
     * @param end   the end
     * @return the int
     * @throws Exception the exception
     */
    int lengthOfShortestRoute(String start, String end) throws Exception;

    /**
     * Number of diff routes with max distance int.
     *
     * @param start       the start
     * @param end         the end
     * @param maxDistance the max distance
     * @return the int
     * @throws Exception the exception
     */
    int numberOfDiffRoutesWithMaxDistance(String start, String end, int maxDistance) throws Exception;


}
