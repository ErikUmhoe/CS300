import java.util.Scanner;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		String wikiChoice;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter a Wikipedia topic:" );
	}

}

class DoubleFunction implements Function<Integer, Integer> {

	@Override
	public Integer apply(Integer t) {

		return t * 2;
	}

}

class AddExclamationFunction implements Function<String, String> {

	@Override
	public String apply(String t) {
		return t += "!";
	}

}
