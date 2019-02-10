package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    // You will probably need more fields

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;

        this.maxNumQueues = maxNumQueues;
        r = new Random();
    }

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {
        //throw new UnsupportedOperationException("Not yet implemented");
        //runs the simulation for increasing lines up to the max number of lines


        System.out.println("Arrival rate: " + arrivalRate);
        for (int lines = 1; lines <= maxNumQueues; lines++){
            //the average wait time for the current number of open lines, to be printed before looping a second time
            //will need to be adjusted after every simulation
            double averageWaitTime = 0;

            //runs the simulation a set number of times
            for (int simulation = 0; simulation < numIterations; simulation++){
                //the total people who made it through the queue
                //the total wait time of people who made it through the queue
                //use these to set the average wait time
                Double totalPeople = 0.0;
                Double totalWaitTime = 0.0;
                Double simsAverageWaitTime = 0.0;

                //creates an array of LinkedQueue
                LinkedQueue[] arrayOfQueues = new LinkedQueue[lines];

                //creates a queue for each line open in this simulation
                for (int count = 0; count < lines; count++){
                    arrayOfQueues[count] = new LinkedQueue<Double>();
                }

                //simulates each minute of the simulation
                for (int minute = 0; minute < 720; minute++){
                    //number of people arriving this minute
                    int p = getRandomNumPeople(arrivalRate);

                    //if there is only 1 line
                    if (lines == 1){
                        //add people to the queue, element is the number of minutes they have been waiting
                        for (int people = p; people > 0; people--){
                            arrayOfQueues[0].offer(0.0);
                        }
                    } else {
                        for (int people = p; people > 0; people--){
                            int smallest = 0;
                            int location = 1;
                            while (location < arrayOfQueues.length) {
                                if (arrayOfQueues[smallest].size() > arrayOfQueues[location].size()){
                                    smallest = location;
                                } else {
                                    location++;
                                }
                            }
                            arrayOfQueues[smallest].offer(0.0);

                        }
                    }

                    //iterates through each queue in the simulation
                    for (int count = 0; count < arrayOfQueues.length; count++){
                        //removes the first two people from a line as long as the line has people in it.  Adds to the totalPeople and totalWaitTime
                        for (int removal = 0; removal < 2; removal++){
                            if (arrayOfQueues[count].peek() != null){
                                Double temp = (Double)arrayOfQueues[count].poll();
                                totalWaitTime += temp;
                                totalPeople += 1.0;
                            }
                        }

                        //adds 1 minute to each person still in the queue
                        for (int count2 = 0; count2 < arrayOfQueues[count].size(); count2++){
                            arrayOfQueues[count].offer((double)arrayOfQueues[count].poll() + 1.0);
                        }
                    }

                }

                simsAverageWaitTime = totalWaitTime / totalPeople;
                averageWaitTime += simsAverageWaitTime;
            }
            averageWaitTime = averageWaitTime / numIterations;
            System.out.println("Average wait time using " + lines + " queue(s): " + (int)averageWaitTime);
        }
    }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
