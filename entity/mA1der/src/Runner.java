import entity.BusinessTrip;

public class Runner {
    public static void main(String[] args) {
        BusinessTrip[] businessTrips = new BusinessTrip[5];
        businessTrips[0] = new BusinessTrip("John Smith", 50.20, 3);
        businessTrips[1] = new BusinessTrip("Alice Johnson", 100.75, 2);
        businessTrips[2] = null;
        businessTrips[3] = new BusinessTrip("Mark Davis", 80.60, 4);
        businessTrips[4] = new BusinessTrip();

        // Output array content using show() method
        for (BusinessTrip trip : businessTrips) {
            if (trip != null) {
                trip.show();
                System.out.println();
            }
        }

        // Find business trip with maximum cost
        double maxCost = 0;
        BusinessTrip maxCostTrip = null;

        for (BusinessTrip trip : businessTrips) {
            if (trip != null && trip.getTotal() > maxCost) {
                maxCost = trip.getTotal();
                maxCostTrip = trip;
            }
        }

        if (maxCostTrip != null) {
            System.out.println("Business trip with maximum cost:");
            maxCostTrip.show();
        }

        // Update transportation expenses for the last object
        if (businessTrips.length > 0 && businessTrips[businessTrips.length - 1] != null) {
            businessTrips[businessTrips.length - 1].setTransportationExpenses(60.40);
        }

        // Output total duration of two initial business trips
        double totalDuration = 0;
        int count = 0;

        for (BusinessTrip trip : businessTrips) {
            if (trip != null && count < 2) {
                totalDuration += trip.getNumberOfDays();
                count++;
            }
        }

        System.out.println("Total duration of two initial business trips: " + totalDuration);

        // Output array content using toString() method implicitly
        for (BusinessTrip trip : businessTrips) {
            if (trip != null) {
                System.out.println(trip);
            }
        }
    }
}
