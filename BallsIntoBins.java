//Ali Sbeih

import java.util.Random;

/**
 a program that simulates
 * the balls into bins process and records various statistics
 * of the procedure
 */
public class BallsIntoBins {
    public static void main(String args[]) {
        Random rand = new Random();


        /** step size between tested values of n */
        final int STEP = 100;

        /** maximum value of n */
        final int MAX = 1_000_000;

        /**
         * Record the statistics for a range of values of n.
         * Record the statistics in a CSV file called
         * "statistics.csv"
         */
        CSVWriter csv = new CSVWriter("statistics.csv");

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("n value");
        csv.addEntry(" Minimum time of the first collision.");
        csv.addEntry(" Maximum time of the first collision.");
        csv.addEntry(" Average time of the first collision.");
        csv.addEntry(" Minimum of the maximum occupancy after the nth trial");
        csv.addEntry(" Maximum of the maximum occupancy after the nth trial");
        csv.addEntry(" Average of the maximum occupancy after the nth trial");
        csv.addEntry(" Minimum number of trials needed until all buckets have occupancy at least 1");
        csv.addEntry(" Maximum number of trials needed until all buckets have occupancy at least 1");
        csv.addEntry(" Average number of trials needed until all buckets have occupancy at least 1");
        csv.addEntry(" Power of two choices minimum of the maximum occupancy after the nth trial");
        csv.addEntry(" Power of two choices maximum of the maximum occupancy after the nth trial");
        csv.addEntry(" Power of two choices average of the maximum occupancy after the nth trial");
        csv.endLine();

        //Try values of n starting from 100 and doubling with a maximum of a million
        for (int size = STEP; size <= MAX; size = size * 2) {
            //Create arrays to store the range of statistics for repeated trials of the same n
            int[] coll = new int[30];
            int[] maxO = new int[30];
            int[] fullO = new int[30];
            int[] powerMax = new int[30];
//Perform the power of two choices stimulation
            for (int j = 0; j < 30; j++) {
                //create an array of bins
                int[] bins = new int[size];
                int powMax = 0;

                for (int i = 0; i < size; i++) {
                    //pick two bins
                    int r = rand.nextInt(size);
                    int p = rand.nextInt(size);
                    //place ball in the smaller bin
                    if (bins[r] <= bins[p]) {
                        bins[r]++;
                        if (bins[r] > powMax) powMax = bins[r];
                    } else {
                        bins[p]++;
                        if (bins[p] > powMax) powMax = bins[p];
                    }
                }
                powerMax[j] = powMax;
            }


//Stimulate the regular balls into bins process and record statistics
            for (int j = 0; j < 30; j++) {
                //create an array of bins
                int[] bins = new int[size];
                //int to record the first collision
                int firstColl = 0;
                //int to record the max occupancy
                int maxOcc = 0;
                //int to record when all the bins are occupied
                int allOcc = size;
                //int to determine that no bins are empty anymore
                int allFull = size;

//loop n times and check for first collision and max Occupancy
                for (int i = 0; i < size; i++) {
                    int r = rand.nextInt(size);
                    if (bins[r] != 0) {
                        if (firstColl == 0) firstColl = i;
                    } else allFull--;
                    bins[r]++;
                    if (bins[r] > maxOcc) maxOcc = bins[r];
                }
                //Check if all the bins have been filled. If not continue throwing balls
                if (allFull != 0) {
                    while (allFull != 0) {
                        int r = rand.nextInt(size);
                        if (bins[r] == 0) allFull--;
                        bins[r]++;
                        allOcc++;
                    }
                } else {
                    firstColl = size + 1;
                }
                coll[j] = firstColl;
                maxO[j] = maxOcc;
                fullO[j] = allOcc;
            }
//create min max and avg integer variables for all the statistics
            int minPow = powerMax[0];
            int maxPow = powerMax[0];
            int avgPow = powerMax[0];
            int minFirst = coll[0];
            int maxFirst = coll[0];
            int avgFirst = coll[0];
            int minMax = maxO[0];
            int maxMax = maxO[0];
            int avgMax = maxO[0];
            int minAll = fullO[0];
            int maxAll = fullO[0];
            int avgAll = fullO[0];
//calculate the values of all the integers created above
            for (int i = 1; i < 30; i++) {
                if (coll[i] < minFirst) minFirst = coll[i];
                if (coll[i] > maxFirst) maxFirst = coll[i];
                avgFirst += coll[i];

                if (maxO[i] < minMax) minMax = maxO[i];
                if (maxO[i] > maxMax) maxMax = maxO[i];
                avgMax += maxO[i];

                if (fullO[i] < minAll) minAll = fullO[i];
                if (fullO[i] > maxAll) maxAll = fullO[i];
                avgAll += fullO[i];

                if (powerMax[i] < minPow) minPow = powerMax[i];
                if (powerMax[i] > maxPow) maxPow = powerMax[i];
                avgPow += powerMax[i];
            }
            avgFirst = avgFirst / 30;
            avgMax = avgMax / 30;
            avgAll = avgAll / 30;
            avgPow = avgPow / 30;

//add the statistics to csv file
            csv.addEntry(size);
            csv.addEntry(minFirst);
            csv.addEntry(maxFirst);
            csv.addEntry(avgFirst);
            csv.addEntry(minMax);
            csv.addEntry(maxMax);
            csv.addEntry(avgMax);
            csv.addEntry(minAll);
            csv.addEntry(maxAll);
            csv.addEntry(avgAll);
            csv.addEntry(minPow);
            csv.addEntry(maxPow);
            csv.addEntry(avgPow);

            csv.endLine();
        }
        csv.close();
    }


}


