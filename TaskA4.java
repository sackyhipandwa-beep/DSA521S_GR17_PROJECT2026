// TaskA4.java
public class TaskA4 {

    public static void processDailyStatistics(int[] serviceTimes) {
        if (serviceTimes.length == 0) {
            System.out.println("No service data available.");
            return;
        }

        int totalStudents = serviceTimes.length;
        int totalServiceTime = 0;
        int highestTime = serviceTimes[0];
        int lowestTime = serviceTimes[0];
        int countOver10Mins = 0;

        for (int i = 0; i < totalStudents; i++) {
            int current = serviceTimes[i];

            totalServiceTime += current;

            if (current > highestTime) {
                highestTime = current;
            }

            if (current < lowestTime) {
                lowestTime = current;
            }

            if (current > 10) {
                countOver10Mins++;
            }
        }

        double averageServiceTime = (double) totalServiceTime / totalStudents;

        System.out.println("\n=== DAILY SERVICE STATISTICS ===");
        System.out.println("Total Students Served  : " + totalStudents);
        System.out.println("Total Service Time     : " + totalServiceTime + " minutes");
        System.out.printf("Average Service Time   : %.2f minutes\n", averageServiceTime);
        System.out.println("Highest Service Time   : " + highestTime + " minutes");
        System.out.println("Lowest Service Time    : " + lowestTime + " minutes");
        System.out.println("Services > 10 Minutes  : " + countOver10Mins);
        System.out.println("=================================\n");
    }

    public static void main(String[] args) {
        int[] dailyServiceTimes = {12, 5, 8, 4, 10, 15, 3, 11, 20, 6};
        processDailyStatistics(dailyServiceTimes);
    }
}