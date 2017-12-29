package GrabTest;

public class ParkingCost {
    /**
     * has been accepted and scored 100
     *
     * @param E
     * @param L
     * @return
     */
    public int solution(String E, String L) {
        if (E.isEmpty() || !(E.split(":").length == 2)) {
            return 0;
        }
        if (L.isEmpty() || !(L.split(":").length == 2)) {
            return 0;
        }
        int entry = 2;
        int starthour = Integer.parseInt(E.split(":")[0]);
        int startmin = Integer.parseInt(E.split(":")[1]);
        int endhour = Integer.parseInt(L.split(":")[0]);
        int endmin = Integer.parseInt(L.split(":")[1]);

        int totalmins = (endhour * 60 + endmin) - (starthour * 60 + startmin);
        int totalhours;
        if (totalmins % 60 == 0) {
            totalhours = totalmins / 60;
        } else {
            totalhours = totalmins / 60 + 1;
        }
        int totalcost;

        if (totalhours > 1) {
            totalcost = entry + 3 + (totalhours - 1) * 4;
        } else if (totalhours == 1) {
            totalcost = entry + 3;
        } else {
            if (starthour == endhour && startmin == endmin) {
                // only entered and exited at the same time
                totalcost = entry;
            } else {
                totalcost = 0;
            }
        }
        return totalcost;
    }
}
