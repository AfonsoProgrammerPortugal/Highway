import java.lang.reflect.Array;

/**
 * A queue system implemented with an array of queues.
 * 
 * @param <E> The type of the elements in the queues.
 * 
 */
public class ArrayQueueSystem<E> implements QueueSystem<E> {

	private Queue<E>[] queues;
	private boolean[] activated;

	private int currentQueue;
	private int nQueues; // how many queues do we have?
	private int nElements; // total number of elements

	/**
	 * Constructor
	 * 
	 * @requires howManyQueues > 0
	 * @param howManyQueues the initial number of active queues
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueueSystem(int howManyQueues) {

		queues = (Queue<E>[]) Array.newInstance(Queue.class, howManyQueues * 2);
		activated = new boolean[howManyQueues * 2];

		for (int i = 0; i < howManyQueues; i++) {
			queues[i] = new ArrayQueue<>();
			activated[i] = true;
		}
		currentQueue = 0;
		nQueues = howManyQueues;
		nElements = 0;
	}

	public void enqueue(E e) throws IllegalQueueRequest {
		if (!activated[currentQueue])
			throw new IllegalQueueRequest("Cannot enqueue in a deactivated queue");

		queues[currentQueue].enqueue(e);
		nElements++;
	}

	public void dequeue() throws IllegalQueueRequest {
		if (!activated[currentQueue])
			throw new IllegalQueueRequest("Cannot dequeue in a deactivated queue");

		if (queues[currentQueue].isEmpty())
			throw new IllegalQueueRequest("Cannot dequeue from an empty queue");

		queues[currentQueue].dequeue();
		nElements--;
	}

	public E front() throws IllegalQueueRequest {
		if (!activated[currentQueue])
			throw new IllegalQueueRequest("Cannot get front element in a deactivated queue");

		if (queues[currentQueue].isEmpty())
			throw new IllegalQueueRequest("Cannot get front element from an empty queue");

		return queues[currentQueue].front();
	}

	public boolean isEmpty() throws IllegalQueueRequest {
		if (!activated[currentQueue])
			throw new IllegalQueueRequest("Cannot check if empty in a deactivated queue");

		return queues[currentQueue].isEmpty();
	}

	public void create() {
		if (queues.length == nQueues)
			grow();
		queues[nQueues] = new ArrayQueue<>();
		nQueues++;
	}

	@SuppressWarnings("unchecked")
	private void grow() {

		Queue<E>[] newQueues = (Queue<E>[]) Array.newInstance(Queue.class, queues.length * 2);
		boolean[] newActivated = new boolean[queues.length * 2];

		for (int i = 0; i < queues.length; i++) {
			newQueues[i] = queues[i];
			newActivated[i] = activated[i];
		}
		queues = newQueues;
		activated = newActivated;
	}

	public boolean isActivated(int i) {
		return activated[i];
	}

	public void activate(int i) {
		activated[i] = true;
	}

	// @requires howManyActiveQueues()>1 [not included in the API, but we need it]
	public void deactivate(int i) throws IllegalQueueRequest {
		if (!queues[i].isEmpty())
			throw new IllegalQueueRequest("Trying to deactivate a non empty queue");
		activated[i] = false;
		// we need to keep active the current queue, so let's choose the first active
		// one
		boolean found = false;
		for (int j = 0; j < queues.length && !found; j++) {
			if (activated[j]) {
				currentQueue = j;
				found = true;
			}
		}
	}

	public void focus(int i) throws IllegalQueueRequest {
		if (!activated[i])
			throw new IllegalQueueRequest("Cannot focus into a deactived queue");
		currentQueue = i;
	}

	public int current() {
		return currentQueue;
	}

	public int howManyActiveQueues() {
		int nActives = 0;
		for (int i = 0; i < nQueues; i++) {
			if (activated[i])
				nActives++;
		}
		return nActives;
	}

	public int howManyQueues() {
		return nQueues;
	}

	public int focusMin() {
		int minIdx = -1;
		int minOccupation = Integer.MAX_VALUE;

		for (int i = 0; i < nQueues; i++) {
			if (activated[i] && queues[i].size() < minOccupation) {
				minOccupation = queues[i].size();
				minIdx = i;
			}
		}

		currentQueue = minIdx;
		return queues[currentQueue].size();
	}

	public int focusMax() {
		int maxIdx = -1;
		int maxOccupation = Integer.MIN_VALUE;

		for (int i = 0; i < nQueues; i++) {
			if (activated[i] && queues[i].size() > maxOccupation) {
				maxOccupation = queues[i].size();
				maxIdx = i;
			}
		}

		currentQueue = maxIdx;
		return queues[currentQueue].size();
	}

	public String toString() {
		String END_LINE = System.lineSeparator();

		StringBuilder sb = new StringBuilder();

		sb.append("Total number of elements in queue system " + nElements + END_LINE);
		sb.append("Current queue " + currentQueue + END_LINE);
		for (int i = 0; i < nQueues; i++) {
			sb.append(queues[i]);
			sb.append(activated[i] ? "    " : " not").append(" active" + END_LINE);
		}
		return sb.toString();
	}

	@Override
	public int size() {
		return nElements;
	}
}