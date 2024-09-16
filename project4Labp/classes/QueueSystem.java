/**
 * A queue system is a sequence of FIFO queues.
 * 
 * Queues are identified by a non-negative index (the first queue has index 0)
 * 
 * Queues inside a queue system can be active or not.
 * 
 * Queue commands (enqueue, dequeue, front, isEmpty) are only valid on active
 * queues.
 * 
 * All deactivated queues must be empty.
 * 
 * It is possible to create new queues. A new queue must be added at the end of
 * the sequence of existing queues. A new queue is inactive.
 * 
 * There is always one queue denoted the current queue.
 * 
 * The current queue must always be an active queue.
 * 
 * When a queue command is executed, it applies to the current queue.
 * 
 * @author Joao Neto
 *
 * @param <E> The type of elements saved in the queue system
 */
public interface QueueSystem<E> {

	/**
	 * The index of the current queue
	 * 
	 * @return the index of the current queue
	 */
	public int current();

	/**
	 * Adds an element to the rear of the current queue
	 * 
	 * @requires isActivated(current())
	 * @param e The object to insert.
	 * @throws IllegalQueueRequest if the current queue is not activated
	 */
	public void enqueue(E e) throws IllegalQueueRequest;

	/**
	 * Removes the element at the front of the current queue.
	 * 
	 * @requires isActivated(current())
	 * @requires !isEmpty()
	 * @throws IllegalQueueRequest if the current queue is not activated or is empty
	 */
	public void dequeue() throws IllegalQueueRequest;

	/**
	 * The element at the front of the current queue.
	 * 
	 * @return The element at the front of the current queue.
	 * @requires isActivated(current())
	 * @requires !isEmpty()
	 * @throws IllegalQueueRequest if the current queue is not activated or is empty
	 */
	public E front() throws IllegalQueueRequest;

	/**
	 * The current queue is empty?
	 * 
	 * @return True if the current queue is empty.
	 * @throws IllegalQueueRequest if queue is not active
	 */
	public boolean isEmpty() throws IllegalQueueRequest;

	/**
	 * The total number of queues in this queue system
	 * 
	 * @return the number of all existing queues
	 */
	public int howManyQueues();

	/**
	 * The total number of active queues in this queue system
	 * 
	 * @return the number of active queues
	 */
	public int howManyActiveQueues();

	/**
	 * The total number of elements inside this queue system
	 * 
	 * @return the number of elements in the queue system
	 */
	public int size();

	/**
	 * Creates a new queue at the end of this queue system, This new queue is non
	 * activated
	 * 
	 * @ensures !isActivated(howManyQueues()-1)
	 */
	public void create();

	/**
	 * Verifies whether the i-th queue is an active queue
	 * 
	 * @param i the index of the queue
	 * @requires i >= 0 && i < howManyQueues()
	 */
	public boolean isActivated(int i);

	/**
	 * Makes the i-th queue an active queue
	 * 
	 * @ensures isActivated(i)
	 * @param i the index of the queue
	 * @requires i >= 0 && i < howManyQueues()
	 */
	public void activate(int i);

	/**
	 * Makes the i-th queue a deactivated queue
	 * 
	 * @requires i >= 0 && i < howManyQueues() && the i-th queue is empty
	 * @ensures !isActivated(i)
	 * @param i the index of the queue
	 * @throws IllegalQueueRequest if the i-th queue is not empty
	 */
	public void deactivate(int i) throws IllegalQueueRequest;

	/**
	 * Makes the i-th queue, the current queue
	 * 
	 * @requires isActivated(i)
	 * @ensures current() == i
	 * @param i the index of the new current queue
	 * @throws IllegalQueueRequest if the queue is not activated
	 */
	public void focus(int i) throws IllegalQueueRequest;

	/**
	 * Focus on a queue with minimal occupation (if several equal, pick the queue
	 * with the least index)
	 * 
	 * @ensures isActivated(current())
	 * @return the number of elements in that queue
	 */
	public int focusMin();

	/**
	 * Focus on a queue with maximal occupation (if several equal, pick the queue
	 * with the least index)
	 * 
	 * @ensures isActivated(current())
	 * @return the number of elements in that queue
	 */
	public int focusMax();
}
