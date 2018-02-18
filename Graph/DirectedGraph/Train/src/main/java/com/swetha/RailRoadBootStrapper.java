package com.swetha;

import com.swetha.service.RailRoadService;
import com.swetha.service.RailRoadServiceImpl;

import java.io.FileNotFoundException;

public class RailRoadBootStrapper {
    /**
     * starting point of the problem solution, mandates input file path as one argument
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide input text file as argument to proceed further");
            System.exit(1);
        } else {
            String filePath = args[0];

            RailRoadService railRoadService = new RailRoadServiceImpl();
            try {
                /**
                 * validate input data and initialize graph
                 */

                railRoadService.initialize(filePath);
                // Print distances
                railRoadService.findDistance();

                // print number of routes with max stops
                railRoadService.findCountWithMaxStops();

                // print number of routes with exact stops
                railRoadService.findCountWithExactStops();

                // print shortest distance
                railRoadService.findShortestLength();

                // print number of routes with max distance
                railRoadService.findCountWithMaxDistance();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
