import java.util.Iterator;

/**
 * Class that creates a linked list of Integers that is iterable and an interator.
 * 
 * @author Erik U
 * @author Nick S
 *
 */
public class EvenNumberGenerator implements Iterable<Integer>, Iterator<Integer>{

	// Current node.
	private Integer current;

	// Number of even objects desired to be printed / created.
	private int numberOfEvens;

	// Variable to be used in hasNext() method to make sure that no iteration past
	// the number of evens desired occurs.
	private int count;

	/**
	 * Initializes a new EvenNumberGenerator to return a single even number
	 * each time it's next() method is called.  The first even number returned
	 * in this way is firstEven.  Subsequent even numbers returned in this way
	 * will be the smallest even number that is larger than the previous.
	 * <p>
	 * After numberOfEvens numbers have been generated and returned from this 
	 * next() method, the generator will end: its hasNext() method will return 
	 * false, and its next() method will throw a NoSuchElementException when 
	 * called after this point.
	 * 
	 * @param numberOfEvens - the number of evens that can be generated
	 * @param firstEven - the first and smallest even that will be generated
	 * @throws IllegalArgumentException - when numberOfEvens is negative, or
	 *                                    when firstEven is not an even number
	 */
	public EvenNumberGenerator(int numberOfEvens, Integer firstEven) throws IllegalArgumentException {
		current = firstEven;
		// Checks if the number is even and is a positive integer.
		if(current % 2 != 0 || current < 0)
			throw new IllegalArgumentException();
		this.numberOfEvens = numberOfEvens;
		count = 0;

	}

	/**
	 * Allows for iteration through generator.
	 * 
	 * @Override
	 */
	public Iterator<Integer> iterator() {
		return this;
	}

	/**
	 * Returns whether iteration can continue or not.
	 * 
	 * @Override
	 */
	public boolean hasNext() {

		return count < numberOfEvens;
	}

	/**
	 * Returns the next even Integer.
	 * 
	 * @Override
	 */
	public Integer next() {
		Integer item = current;
		current = current+2;
		count++;
		return item;
	}

}