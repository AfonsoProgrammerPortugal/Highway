import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Client class of the Supermarket class; it runs a simulation of clients
 * arriving to the queues in a supermarket and being served.
 * 
 * @author
 *
 */
public class RunSimulation {

	private static final String END_LINE = System.lineSeparator();

	/**
	 * A Scanner instance is created to read the file of input data; A Supermarket
	 * instance is created using the two first values read from the file; A
	 * simulation is executed over the supermarket in several steps (as many as a
	 * value read from the file). In each step: - one or more customer arrivals at
	 * the queues; - a unit of time is elapsed (this may cause the end of service of
	 * some clients that were being served, and the deactivation of some empty
	 * queue).
	 * 
	 * @param args The first and only element of this parameter is the name of the
	 *             file containing the input data
	 * @throws FileNotFoundException
	 * @throws IllegalQueueRequest
	 */
	public static void main(String[] args) throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		Scanner sc = new Scanner(new File(args[0]));

		int minActQueues = sc.nextInt();
		int maxVehiPerQueue = sc.nextInt();
		
		Highway myHighway = new Highway(minActQueues, maxVehiPerQueue);
		System.out.println("---------- Highway before starting the simulation" + END_LINE + myHighway);
		System.out.println("----------");

		simulation(myHighway, sc);

		sc.close();
	}

	/**
	 * Runs a simulation over a supermarket, according to client arrival data read
	 * from a file.
	 * 
	 * This simulation is executed in several steps (as many as a value read from
	 * the file). In each step: - one or more customer arrives wanting to be served
	 * (register the goods and pay), and the system has to decide to which queue the
	 * customer will be assigned to; - a unit of time is elapsed (this may cause the
	 * end of service of some clients that were being served, and the deactivation
	 * of some empty queue).
	 * 
	 * Information is written to the standard output at the end of each 100 units of
	 * simulation time.
	 * 
	 * @param myHighway The instance of Supermarket that is the simulation target
	 * @param sc      The Scanner instance through which the input data is read.
	 * @requires myHighway != null && sc != null
	 * @throws IllegalQueueRequest
	 */
	public static void simulation(Highway myHighway, Scanner sc) throws IllegalQueueRequest {

		// initialize variables
		int simulationTime = sc.nextInt();
		int clock = 1;
	
		int timeArrival = sc.nextInt();
		int durationNewArrival = sc.nextInt();
		double tollFee = sc.nextDouble();
		boolean eof = false; // end of file not reached

		// process arrivals and departures for each unit of time
		while (clock <= simulationTime) {
			// process arrivals
			// more than 1 customer may arrive at the same time
			// stop reading data when the end of the file is reached
			while (clock == timeArrival && !eof) {
				Vehicle v = new Vehicle(timeArrival, durationNewArrival, tollFee);
				myHighway.addVehicle(v);
				// prepare next arrival
				if (sc.hasNext())
					timeArrival = sc.nextInt();
				else
					eof = true;
				if (sc.hasNext())
					durationNewArrival = sc.nextInt();
				if (sc.hasNext())
                    tollFee = sc.nextDouble();
			}

			// process departures
			myHighway.updateActiveQueues();

			// reduce one active queue, if possible
			myHighway.updateNumberActiveQueues();

			// print info every 100 simulation time units
			if (clock % 100 == 0) {
				System.out.println(END_LINE + "---------- After time " + clock);
				System.out.print(myHighway.toString());
				String strDouble = String.format("%.2f", myHighway.averageVehiclesPerQueue());
				System.out.println("Average number of vehicles per queue " + strDouble);
				System.out.println("----------");
			}

			clock++;
		}

		System.out.println(END_LINE + "---------- Highway after finishing the simulation");
		System.out.print(myHighway.toString());
		String strDouble = String.format("%.2f", myHighway.averageWaitingTime());
		System.out.println("Average waiting time in queue " + strDouble);
		System.out.println("----------");
	}

}
