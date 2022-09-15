# Balls-in-Bins
A program that simulates the balls into bins process and records various statistics of the procedure. For the basic balls into bins process with n buckets, the program  reports the following.

The time of the first collision.

The maximum occupancy after the nth trial.

The number of trials needed until all buckets have occupancy at least 1.

Additionally, the program implements the “power of two choices” process and reports the maximum occupancy after n trials. The power of two choices process is that when choosing which bin in which to place a ball, instead of choosing a single bin, we choose two bins. If the two bins have the same occupancy, then the ball is placed in either bin; otherwise, the ball is placed in the smaller bin.



Also, there is a program CSVWriter.java that plots the statistics above for a range of values of n. Specifically, the program does multiple runs of each simulation for each choice of n, and reports the maximum, minimum, and average values for the items above encountered for each choice of n. Thus, the program shows not just how the statistics above grow with n, but how the values themselves are distributed over multiple runs of the same simulation. 

![image](https://user-images.githubusercontent.com/99061775/190509886-357ef504-9b80-4bf6-982c-1c1f57977658.png)
![image](https://user-images.githubusercontent.com/99061775/190510027-73bce5ab-b786-42b2-8e85-67da2e9dc266.png)
![image](https://user-images.githubusercontent.com/99061775/190510109-7c3f27be-fcd1-4ef3-922f-535814c94ae1.png)
![image](https://user-images.githubusercontent.com/99061775/190510884-30b73477-7158-48f8-8e3a-4e2975d40b87.png)
