import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class HighwayTests {
	
	@Test
	void test_vehiclesinQueue1() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		String args = "tests.txt";
		Scanner sc = new Scanner(new File(args));

		int minActQueues = 2;
		int maxVehiPerQueue = 3; 

		Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
		int simulationTime = 1;
		AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

		sc.close();

		System.out.println( "----------Highway after simulation \n" + myHighway );

		int actual = myHighway.totalNrVehicles ();
		int esperado = 1;  

		assertEquals( "Total number of vehicles in queues should be equal ", esperado, actual);
	}

	@Test
	void test_vehiclesinQueue10() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		String args = "tests.txt";
		Scanner sc = new Scanner(new File(args));

		int minActQueues = 2;
		int maxVehiPerQueue = 3; 

		Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
		int simulationTime = 10;
		AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

		sc.close();

		System.out.println( "----------Highway after simulation \n" + myHighway );

		int actual = myHighway.totalNrVehicles ();
		int esperado = 8; 

		assertEquals( "Total number of vehicles in queues should be equal ", esperado, actual);
	}
	
	@Test
	void test_activeQueues60() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		String args = "tests.txt";
		Scanner sc = new Scanner(new File(args));

		int minActQueues = 2;
		int maxVehiPerQueue = 3; 

		Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
		int simulationTime = 60;
		AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

		sc.close();

		System.out.println( "----------Highway after simulation \n" + myHighway );

		int actual = myHighway.nrActiveQueues();
		int esperado = 5; 

		assertEquals( "Number of active queues should be equal ", esperado, actual);
	}
	
	@Test
	void test_activeQueues100() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		String args = "tests.txt";
		Scanner sc = new Scanner(new File(args));

		int minActQueues = 2;
		int maxVehiPerQueue = 3; 

		Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
		int simulationTime = 100;
		AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

		sc.close();

		System.out.println( "----------Highway after simulation \n" + myHighway );

		int actual = myHighway.nrActiveQueues();
		int esperado = 2; 

		assertEquals( "Number of active queues should be equal ", esperado, actual);
	}
	
	@Test
	void test_averageWaitingTime60() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		String args = "tests.txt";
		Scanner sc = new Scanner(new File(args));

		int minActQueues = 2;
		int maxVehiPerQueue = 3; 

		Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
		int simulationTime = 60;
		AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

		sc.close();

		System.out.println( "----------Highway after simulation \n" + myHighway );

		double actual = myHighway.averageWaitingTime();
		double esperado = 10.47;
		double delta = 0.05;

		assertEquals(esperado, actual, delta);
	}
	
	@Test
	void test_averageVehiclesPerQueue60() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
		String args = "tests.txt";
		Scanner sc = new Scanner(new File(args));

		int minActQueues = 2;
		int maxVehiPerQueue = 3; 

		Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
		int simulationTime = 60;
		AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

		sc.close();

		System.out.println( "----------Highway after simulation \n" + myHighway );

		double actual = myHighway.averageVehiclesPerQueue();
		double esperado = 1.6;
		double delta = 0.05;

		assertEquals(esperado, actual, delta);
	}
	
	@Test
    void test_totalTolls60() throws FileNotFoundException, IllegalQueueRequest {
	    Locale.setDefault(new Locale ("en", "US"));
        String args = "tests.txt";
        Scanner sc = new Scanner(new File(args));

        int minActQueues = 2;
        int maxVehiPerQueue = 3; 

        Highway myHighway= new Highway(minActQueues, maxVehiPerQueue);
        int simulationTime = 60;
        AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

        sc.close();

        System.out.println( "----------Highway after simulation \n" + myHighway );

        double actual = myHighway.totalTolls();
        double esperado = 16.65;
        double delta = 0.05;

        assertEquals(esperado, actual, delta);
    }

    @Test
    void test_averageTolls60() throws FileNotFoundException, IllegalQueueRequest {
        Locale.setDefault(new Locale("en", "US"));
        String args = "tests.txt";
        Scanner sc = new Scanner(new File(args));

        int minActQueues = 2;
        int maxVehiPerQueue = 3;

        Highway myHighway = new Highway(minActQueues, maxVehiPerQueue);
        int simulationTime = 20;
        AuxTestsSimulation.simulationTest(sc, myHighway, simulationTime);

        sc.close();

        System.out.println("----------Highway after simulation \n" + myHighway);

        double actual = myHighway.averageTolls();
        double esperado = 1.44;
        double delta = 0.05;

        assertEquals(esperado, actual, delta);
    }

}
