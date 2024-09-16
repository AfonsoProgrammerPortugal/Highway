import java.util.Scanner;

public class AuxTestsSimulation {
	
	public static void simulationTest (Scanner sc, Highway myHighway, int simulationTime) 
			throws IllegalQueueRequest {
	
	int clock = 1;

	int timeArrival = sc.nextInt();
	int durationNewArrival = sc.nextInt();
	double tollFee = sc.nextDouble();
	boolean eof = false;  //end of file not reached

	// process arrivals and departures for each unit of time 
	while ( clock <= simulationTime) {
		// process arrivals
		//more than 1 customer may arrive at the same time
		//stop reading data when the end of the file is reached
		while (clock == timeArrival && !eof) { 
			Vehicle v = new Vehicle (timeArrival, durationNewArrival, tollFee);
			myHighway.addVehicle (v) ;

			//prepare next arrival
			if (sc.hasNext()) 
				timeArrival = sc.nextInt();
			else eof = true;
			if (sc.hasNext())
				durationNewArrival = sc.nextInt();
			if (sc.hasNext())
                tollFee = sc.nextDouble();
		}

		// process departures
		myHighway.updateActiveQueues ();

		// reduce one active queue, if possible
		myHighway.updateNumberActiveQueues ();
	
		clock ++ ;
	}
	}
}
