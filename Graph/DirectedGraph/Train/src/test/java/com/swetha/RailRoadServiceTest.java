package com.swetha;

import com.swetha.service.RailRoadService;
import com.swetha.service.RailRoadServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class RailRoadServiceTest {
    private RailRoadService service;

    @Before
    public void initObjects() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("input.txt").getFile());
            service = new RailRoadServiceImpl();
            service.initialize(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDistance() throws Exception {
        int distance = service.distanceOfRoute("A", "B", "C");
        assertEquals(9, distance);

        distance = service.distanceOfRoute("A", "D");
        assertEquals(5, distance);

        distance = service.distanceOfRoute("A", "D", "C");
        assertEquals(13, distance);

        distance = service.distanceOfRoute("A", "E", "B", "C", "D");
        assertEquals(22, distance);
    }


    @Test(expected = Exception.class)
    public void testDistanceException() throws Exception {
        service.distanceOfRoute("A", "E", "D");
    }

    @Test
    public void testFindDistance() throws Exception {
        // prints the distances in console
        service.findDistance();
    }

    @Test
    public void testCountRoutesWithMaxHops() throws Exception {
        int count = service.numberOfTripsWithMaxStops("C", "C", 3);
        assertEquals(2, count);
    }

    @Test
    public void testFindCountWithMaxStops() throws Exception {
        // prints the count with max hops
        service.findCountWithMaxStops();
    }

    @Test
    public void testCountRoutesWithExactHops() throws Exception {
        int count = service.numberOfTripsWithExactStops("A", "C", 4);
        assertEquals(3, count);
    }

    @Test
    public void testFindCountWithExactStops() throws Exception {
        // prints the count with exact hops
        service.findCountWithExactStops();
    }


    @Test
    public void testLengthOfShortestRoute() throws Exception {
        int distance = service.lengthOfShortestRoute("A", "C");
        assertEquals(9, distance);

        distance = service.lengthOfShortestRoute("B", "B");
        assertEquals(9, distance);
    }

    @Test(expected = Exception.class)
    public void testLengthOfShortestRouteException() throws Exception {
        service.lengthOfShortestRoute("B", "F");
    }

    @Test
    public void testfindShortestLength() throws Exception {
        // prints the shortest distance
        service.findShortestLength();
    }

    @Test
    public void testCountRoutesWithMaxDistance() throws Exception {
        int count = service.numberOfDiffRoutesWithMaxDistance("C", "C", 29);
        assertEquals(7, count);
    }


    @Test
    public void testfindCountWithMaxDistance() throws Exception {
        // prints the shortest distance
        service.findCountWithMaxDistance();
    }

}
