import java.awt.List;
import java.util.Scanner;

public class AccountBook {
	
	private RecordNode head;
	private RecordNode tail;
	private double balance;

	
	
	public AccountBook()
	{
		head = null;
		tail = null;
		balance = 0;

	}
	
	
	/**
	 * Insert a record node into the account book. The money amount can be either
	 * negative, meaning the user spent money, or positive, meaning the user
	 * received money. If in the account book there are records on the same day, you
	 * need to insert the record after the last of them; Otherwise, you need to
	 * insert the record between records on earlier days and those on later days.
	 * 
	 * @param day
	 *            The day of the record to be inserted.
	 * @param amount
	 *            The money amount of the record.
	 */
	public void insertRecord(int day, double amount) 
	{		
		
		
			
		if(head == null)
		{
			head = new RecordNode(day,amount,null);
			tail = head;
		}
		else if(head.getDay() > day)
		{
			head = new RecordNode(day, amount, head);
		}
		else
		{
			int size = 1;
			RecordNode temp = head;
			while(temp!=null)
			{
				size++;
				temp = temp.getNext();
			}
			temp = head;

			int index = temp.indexOf(day);
			if(index == size)
			{
				temp.setNext(new RecordNode(day, amount, null));
				tail = temp.getNext();
			}
			else if(index < 0 && tail.getDay() <= day)
			{
				while(temp.getNext()!=null)
					temp = temp.getNext();
				temp.setNext(new RecordNode(day, amount, null));
				tail = temp.getNext();
			}
			else if(index < 0 && tail.getDay() > day)
			{
				boolean found = false;
				while(temp.getNext()!=null && found != true)
				{
					if(temp.getNext().getDay() > day)
					{
						temp.setNext(new RecordNode(day, amount,temp.getNext()));
						found = true;
					}
					temp = temp.getNext();
				}
			}
			else
			{
				for(int i = 0; i < index;i++)
				{
					temp = temp.getNext();
				}
				temp.setNext(new RecordNode(day, amount, temp.getNext()));
			}

			
		}
		balance+=amount;
		
//		boolean done = false;
//		if(head == null)
//		{
//			head = new RecordNode(day, amount, null);
//			tail = head;
//			balance += amount;
//			done = true;
//		}
//		else {
//			RecordNode temp = head;
//			if(temp.getNext() == null)
//			{
//				temp.setNext(new RecordNode(day, amount, null));
//				tail = temp.getNext();
//			}
//			else
//			{
//				while(temp.getNext() != null && !done)
//				{
//					if(temp.getDay() > day)
//					{
//						temp.setNext(new RecordNode(day, amount, temp.getNext()));
//						done = true;
//					}
//					temp = temp.getNext();
//				}
//			}
//			if(!done)
//			{
//				RecordNode x = new RecordNode(day, amount,null);
//				tail = x;
//				temp.setNext(tail);
//			}
//		}
	}

	/**
	 * Prepend a record into the account book. The day of the record should be the
	 * same as the EARLIEST record in the book. If there haven't been any records in
	 * the book yet, you should show user the warning message "WARNING: Unable to
	 * prepend a record, for no records in the account book yet." by printing it to
	 * the console.
	 * 
	 * @param amount
	 *            The money amount of the record to be prepended.
	 */
	public void prependRecord(double amount) 
	{
		if(head == null)
		{
			System.out.println("WARNING: Unable to prepand a record, for no records in the account book yet.");
		}
		
		else
		{
			RecordNode temp = new RecordNode(head.getDay(), amount, head);
			head = temp;
			
			balance += amount;
			
		}
	}

	/**
	 * Append a record into the account book. Similar as above, the day of the
	 * record should be the same as the LATEST record. If there haven't no records
	 * in the book yet, you should show user the warning message "WARNING: Unable to
	 * append a record, for no records in the account book yet.".
	 * 
	 * @param amount
	 *            The money amount of the record to be appended.
	 */
	public void appendRecord(double amount) {
		if(head == null)
		{
			System.out.println("WARNING: Unable to prepand a record, for no records in the account book yet.");
		}
		else
		{
			RecordNode temp = head;
			while(temp.getNext() != null)
			{
				temp = temp.getNext();
			}
			temp.setNext(new RecordNode(temp.getDay(), amount, null));
			tail = temp;
			balance += amount;
			
		}
		
	}

	/**
	 * Remove a record from the account book. The two arguments identify which
	 * record to remove. E.g., with day being 4 and seq_num being 2, the user
	 * would like to delete the second record on the 4th day. If the number of
	 * records on day is smaller than seq_num, you show user the warning message
	 * "WARNING: Unable to remove a record, for not enough records on the day
	 * specified.".
	 * 
	 * @param day
	 *            The day of the record to be removed.
	 * @param seq_num
	 *            The sequence number of the record within the day of it.
	 */
	public void removeRecord(int day, int seq_num) 
	{
		if(head !=null)
		{
			RecordNode temp = head;
			RecordNode sucNode;
			int index = temp.indexOf(day, seq_num);
			if(index == -1)
			{
				System.out.println("WARNING: Unable to remove a record, for not enough records on the day specified.");
			}
			else {
				int size = 0;
				while(temp!=null)
				{
					size++;
					temp = temp.getNext();
				}
				temp = head;
				if(index == 0)
				{
					sucNode = head.getNext();
					double tempB = head.getAmount();
					head = sucNode;
					if(sucNode == null)
						tail = sucNode;
					balance-=tempB;
				}
				else if(index==size)
				{
					double tempB = tail.getAmount();
					tail.setNext(null);
					balance-=tempB;
				}
				else
				{
					for(int i = 0; i < index-1; i++)
					{
						temp = temp.getNext();
					}
					double tempB = temp.getNext().getAmount();
					sucNode = temp.getNext().getNext();
					temp.setNext(sucNode);
		//			for(int i = 0; i < index-1; i ++)
		//			{
		//				temp = temp.getNext();
		//			}
		//			temp.setNext(temp.getNext().getNext());
					balance-=tempB;
				}
			
			}
		}
			else
				System.out.println("WARNING: Unable to remove a record, for not enough records on the day specified.");
		
		
		
	}
	
	
	/**
	 * Modify a record in the account book. Similar as above, day and seq_num
	 * identify which record to modify, while amount indicates the excepted money
	 * amount of the record after modification E.g., with the three arguments being
	 * 4 2 100 respectively, the user would like to modify the second record on the
	 * 4th day, and change the amount to 100. If the number of records on day is
	 * smaller than seq_num , you should show user the warning message "WARNING:
	 * Unable to modify a record, for not enough records on the day specified.".
	 * 
	 * @param day
	 *            The day of the record to be modified.
	 * @param seq_num
	 *            The sequence number of the record within the day of it.
	 * @param amount
	 *            The amount of the record after modified.
	 */
	public void modifyRecord(int day, int seq_num, double amount) 
	{
		if(head != null)
		{
			RecordNode temp = head;
			int index = temp.indexOf(day, seq_num);
			if(index == -1)
			{
				System.out.println("WARNING: Unable to remove a record, for not enough records on the day specified.");
			}
			if(index == 0)
			{
				balance -= temp.getAmount();
				head.setAmount(amount);
				temp.setAmount(amount);
				balance+=amount;
			}
			else
			{
				for(int i = 0; i < index; i++)
				{
					temp = temp.getNext();
				}
				
				balance -= temp.getAmount();
				temp.setAmount(amount);
				balance+= amount;
			}
	}
		else
		{
			System.out.println("WARNING: Unable to modify an element because no elements exist");
		}
	
	}

	/**
	 * Show user the overall balance by printing some leading textual prompt
	 * followed by the balance to the console, e.g., "Balance: -90.95". The balance
	 * should be initialized as 0 at first, and accumulates as the user
	 * insert/prepend/append/remove/modify records.
	 */
	public void showBalance() 
	{
		System.out.printf("Balance: $%.2f\n", balance);
	}

	/**
	 * Display all the records so far as well as the overall balance. If there
	 * haven't been no records in the book yet, you should display "No records in
	 * the book yet." before displaying the account balance.
	 */
	public void display() 
	{
		System.out.println("Day\t\tAmount");
		System.out.println("===================");
		RecordNode temp = head;
		
			while(temp != null)
			{
				System.out.print(temp.getDay() + "\t\t");
				System.out.printf("$%.2f\n", temp.getAmount());
				temp = temp.getNext();
			}
			
			System.out.printf("Balance: $%.2f\n", balance);
		
		
	}

	/**
	 * Show the records and accumulated balance on the day specified. If in the
	 * account book there haven't been any records on the day specified yet, you
	 * should display "No records on the day yet." before displaying the accumulated
	 * balance.
	 * 
	 * @param day
	 *            The day of the summary to be shown.
	 */
	
	//STARTING NEW
	public void showDaySummary(int day) 
	{
		double balanceDay = 0;
		RecordNode temp = head;
		System.out.println("Day\t\tAmount");
		System.out.println("===================");
		while(temp.getNext() != null)
		{
			if(temp.getDay() == day)
			{
				System.out.print(temp.getDay()+"\t\t");
				System.out.printf("$%.2f\n", temp.getAmount());
				balanceDay += temp.getAmount();
			}
			temp = temp.getNext();
			
		}
		if(day == tail.getDay())
		{
			System.out.print(temp.getDay()+"\t\t");
			balanceDay += temp.getAmount();

			System.out.printf("$%.2f\n", temp.getAmount());
		}
		
		System.out.printf("Accumulated Balance: $%.2f\n", balanceDay);
	}
	
	

	
	public static void main(String[] args)
	{
		AccountBook book = new AccountBook();
		String userChoice = "";
		Scanner input = new Scanner(System.in);;
		Scanner scan;
		
		do
		{
			
			System.out.println("Please enter in a command: ");
			
				userChoice = input.nextLine();
				if(!userChoice.isEmpty())
				{
					if(userChoice.charAt(0) == 'i')
					{
						
						scan = new Scanner(userChoice);
						while (!scan.hasNextInt())
				        {
				           scan.next();
				        }
				        int day = scan.nextInt();
				        double amount = scan.nextDouble();
				        if(day > 31 || day < 0)
						{
							System.out.println("Warning: Invalid day number.");
						}
				        else
				        	book.insertRecord(day, amount);
					
					}
					
					else if(userChoice.charAt(0) == 'p')
					{
						scan = new Scanner(userChoice);
		
						while (!scan.hasNextDouble())
				        {
				           scan.next();
				        }
				        double amount = scan.nextDouble();
						book.prependRecord(amount);
						
					}
					
					else if(userChoice.charAt(0) == 'a')
					{
						scan = new Scanner(userChoice);
		
						while (!scan.hasNextDouble())
				        {
				           scan.next();
				        }
				        double amount = scan.nextDouble();
						book.appendRecord(amount);
					}
					
					else if(userChoice.charAt(0) == 'r')
					{
						scan = new Scanner(userChoice);
						while (!scan.hasNextInt())
				        {
				           scan.next();
				        }
				        int day = scan.nextInt();
						
						int seq_num = scan.nextInt();
						
						if(day > 31 || day < 0)
						{
							System.out.println("WARNING: Invalid day number.");
						}
						else if(seq_num < 1)
						{
							System.out.println("WARNING: Invalid sequence number");
						}
						
						else {
							book.removeRecord(day, seq_num);
						}
					}
					
					else if(userChoice.charAt(0) == 'm')
					{
						scan = new Scanner(userChoice);
		
						while (!scan.hasNextInt())
				        {
				           scan.next();
				        }
				        int day = scan.nextInt();
				        int seq_num = scan.nextInt();
						double amount = scan.nextDouble();
						
						if(day > 31 || day < 0)
						{
							System.out.println("WARNING: Invalid day number.");
						}
						else if(seq_num < 1)
						{
							System.out.println("WARNING: Invalid sequence number");
						}
						
						else {
							book.modifyRecord(day, seq_num, amount);
						}
					}
					
					else if(userChoice.charAt(0) == 'b')
					{
						book.showBalance();
					}
					else if(userChoice.charAt(0) == 's')
					{
						scan = new Scanner(userChoice);
		
						while (!scan.hasNextInt())
				        {
				           scan.next();
				        }
				        int day = scan.nextInt();	
				        book.showDaySummary(day);
					}
					else if(userChoice.charAt(0) == 'd')
						book.display();
					else if(userChoice.charAt(0) != 'q')
					{
						System.out.println("WARNING: Unrecognized command.");
					}
				}
			
				
		}while(!userChoice.equalsIgnoreCase("q"));
		
}
	
}