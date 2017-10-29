import java.util.Iterator;

public class EvenNumberGenerator implements Iterable<Integer>, Iterator<Integer>{
	
	private Integer current;
	private int numberOfEvens;
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
	   if(current % 2 != 0 || current < 0)
		   throw new IllegalArgumentException();
	   this.numberOfEvens = numberOfEvens;
	   count = 0;
	   
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean hasNext() {
		
		return count < numberOfEvens;
	}

	@Override
	public Integer next() {
		Integer item = current;
		current = current+2;
		count++;
		return item;
	}
}
