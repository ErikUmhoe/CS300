import java.util.Scanner;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		String wikiChoice;
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a Wikipedia topic:" );
		wikiChoice = input.nextLine();
		wikiChoice = wikiChoice.replaceAll(" ", "_");
		wikiChoice = wikiChoice.trim();
		System.out.println(wikiChoice);

		int count = 0;
		boolean found = false;
		for(String i : new Generator<String>(100,"/wiki/"+wikiChoice,new NetWikiLinkFunction()))
		{
			System.out.println(count +": " + i);
			count++;
			if(i.indexOf("Philosophy") > 0)
			{	
				found = true;
				break;
			}
			else if(i.contains("FAILED"))
				break;
				
		}
		
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
