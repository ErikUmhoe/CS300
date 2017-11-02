import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * This class creates an iterable object that can be iterated upon to increase the number in a way defined
 * by a user passed in function
 * @author Erik U
 * @author Nick S
 *
 */
public class NumberGenerator implements Iterable<Integer>, Iterator<Integer>, Function<Integer,Integer>{
	
	private Integer current;	//Current node 
	private int numberOfEvens;	//Number of even objects desired to be printed / created
	private int count;			//Variable to be used in hasNext() method to make sure that no iteration past the number of evens desired occurs
	private Function<Integer, Integer> func;	//Function to be passed in that decides what to do to the numbers
	
	
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
	public NumberGenerator(int numberOfEvens, Integer firstEven, Function<Integer, Integer> num) throws IllegalArgumentException {
	   current = firstEven;
	   if(current < 0)
		   throw new IllegalArgumentException();
	   this.numberOfEvens = numberOfEvens;
	   count = 0;
	   func = num;
	}
	
	//Allows for iteration through generator
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return this;
	}
	//Returns whether iteration can continue or not
	@Override
	public boolean hasNext() {
		
		return count < numberOfEvens;
	}
	//Returns the next node with the function applied to the previous node.
	@Override
	public Integer next() {
		if(!hasNext())	throw new NoSuchElementException();
		Integer item = current;
		current = apply(current);
		count++;
		return item;
	}
	//Applies the passed in function to the current node, returning the new node.
	@Override
	public Integer apply(Integer t) {
		// TODO Auto-generated method stub
		return func.apply(t);
	}

	
}