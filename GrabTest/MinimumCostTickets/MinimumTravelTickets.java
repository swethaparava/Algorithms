package GrabTest;

public class MinimumTravelTickets {

    /**
     * 1-day ticket, costs 2, valid for one day;
     * 7-day ticket, costs 7, valid for seven consecutive days (e.g. if the first valid day is X, then the last valid day is X+6);
     * 30-day ticket, costs 25, valid for all thirty days of the month.
     * You want to pay as little as possible.
     */
    public static void main(String[] args) {
        //int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        //int[] A = {1, 2, 3, 4, 5,6, 7,8,9,10,11,12,13,14,15,16,17,18,19,20, 29, 30};
        solution(A);
    }

    public static int solution(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        if (A.length <= 3) { // any 3 days will have 6 as minimum cost, if 4 days then Min(8,7) which can be 7, hence will not come
            // come under this condition
            return 2 * A.length;
        }
        if (A.length >= 23) { // 3*7=21, 22/23 - 4, (total cost is 25, more than this falls under 30 day pass)
            return 25;
        }
        int[] dp = new int[31];
        dp[0] = 0;
        // take trips array and set travel dates
        int[] trips = new int[31];
        for (int day : A) {
            trips[day] = 1;
        }
        for (int i = 1; i <= 30; i++) {

            if (trips[i] == 0) {
                dp[i] = dp[i - 1];
                continue;
            }

            int min = dp[i - 1] + 2;  // one day cost
            int j = 0;
            if (i > 7) {
                j = i - 7; // as you progress, always worry about last 7 days to decide whether to take 7 pass or not
            }
            while (j <= i - 4) { //after considering last 7 days, first 3 of last 7 days is what matters as the cost after that will be more than 7
                min = Math.min(min, dp[j] + 7);
                j++;
            }
            min = Math.min(min, 25);
            dp[i] = min;
        }

        return dp[30];
    }

}
