
public class Communication {
	
	// Setting the variables as protected , because the must be reachable from the sub-classes PhoneCall and SMS
	protected String Num1; // First number involved
	protected String Num2; // Second number involved
	protected int year;
	protected int month;
	protected int day;


	
	//Constructor
	public Communication(String num1, String num2, int day, int month, int year)
	{
		this.Num1 = num1;
		this.Num2 = num2;
		this.year = year;
		this.month = month;
		this.day = day;


	}

	public void printInfo() 
	{
		System.out.println("Between " + Num1 + " --- " + Num2) ;
		System.out.println("on " + year +"/" + month +"/" + day) ;
	}
	
	public String getNum1() 
	{
		return Num1;
	}
	
	public String getNum2() 
	{
		return Num2;
	}
	
}
