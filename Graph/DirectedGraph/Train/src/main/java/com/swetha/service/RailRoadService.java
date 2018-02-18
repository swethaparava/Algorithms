package com.swetha.service;

import java.io.FileNotFoundException;

public interface RailRoadService {

    void initialize(String path) throws FileNotFoundException;

    void findDistance();

    void findCountWithMaxStops();

    void findCountWithExactStops();

    void findShortestLength();

    void findCountWithMaxDistance();

    int distanceOfRoute(String... cityNames) throws Exception;

    int numberOfTripsWithMaxStops(String start, String end, int maxStops) throws Exception;

    int numberOfTripsWithExactStops(String start, String end, int stops) throws Exception;

    int lengthOfShortestRoute(String start, String end) throws Exception;

    int numberOfDiffRoutesWithMaxDistance(String start, String end, int maxDistance) throws Exception;



}
