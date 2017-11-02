import java.util.Iterator;
import java.util.function.Function;

/**
 * Class that creates a generator linked list that is Iterable and an Iterator
 * 
 * @author Erik U
 * @author Nick S
 *
 * @param <Type>
 */
public class Generator<Type> implements Iterable<Type>, Iterator<Type>, Function<Type,Type>{

	// Current node.
	private Type current;

	// Number of even objects desired to be printed / created.
	private int numberOfEvens;

	// Variable to be used in hasNext() method to make sure that no iteration past
	// the number of evens desired occurs.
	private int count;

	// Function to be passed in that decides what to do to the Type.
	private Function<Type, Type> func; 

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
	public Generator(int numberOfEvens, Type first, Function<Type, Type> num) throws IllegalArgumentException {

		current = first;
		this.numberOfEvens = numberOfEvens;
		count = 0;
		func = num;
	}

	/**
	 * Allows for iteration through generator.
	 * 
	 * @Override
	 */
	public Iterator<Type> iterator() {
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
	 * Returns the next node with the function applied to the previous node.
	 * 
	 * @Override
	 */
	public Type next() {
		Type item = current;
		current = apply(current);
		count++;
		return item;
	}

	/**
	 * Applies the passed in function to the current node, returning the new node.
	 * 
	 * @Override
	 */
	public Type apply(Type t) {
		return func.apply(t);
	}

}