
public class RecordNode {

	private int day;
	private double amount;
	private RecordNode next;
	
	
	public int getDay() {
		return day;
	}

	public RecordNode(int day, double amount)
	{
		this.day = day;
		this.amount = amount;
		next = null;
	}
	
	public RecordNode(int day, double amount, RecordNode next)
	{
		this.day = day;
		this.amount = amount;
		this.next = next;
	}
	
	public void setDay(int day) {
		this.day = day;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public RecordNode getNext() {
		return next;
	}


	public void setNext(RecordNode next) {
		this.next = next;
	}

	public int indexOf(int day, int seq_num)
	{
		
		int count = 1;
		int index = 0;
		RecordNode temp = this;
		
		while(temp.getNext()!= null)
		{
			if(temp.getDay() == day && count == seq_num)
			{
				return index;
				
			}
			if(temp.getDay() == day)
				count++;
			index++;
			temp = temp.getNext();
		}
		if(temp.getDay() == day && count == seq_num)
			return index;
		return -1;
		
	}

	public int indexOf(int day)
	{
	
		int index = 0;
		RecordNode temp = this;
		while(temp.getNext()!= null)
		{
			if(temp.getDay() == day && temp.getNext().getDay()!=day)
			{
				System.out.println("Index: " + index);
				return index;
			}
				index++;
			temp = temp.getNext();
		}
		return -1;
		
	}
	
	public int size()
	{
		int size = 0;
		RecordNode temp = this;
		while(temp.getNext() != null)
		{
			temp = temp.getNext();
			size++;
		}
		return size;
	}

	public static void main(String[] args) {
		
		
	}
	

}