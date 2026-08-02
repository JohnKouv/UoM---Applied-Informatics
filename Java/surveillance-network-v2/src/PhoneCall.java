
public class PhoneCall extends Communication{
	
	private int seconds;
	
	//Constructor
	public PhoneCall(String num1, String num2, int day, int month, int year, int seconds)
	{
		super(num1, num2, day, month, year); // Uses the superclass constructor
		this.seconds = seconds;
	}
	
	// Printing 
	public void printInfo() 
	{
		System.out.println("This phone call has the following info");
		super.printInfo(); // Uses the superclass Communication
		System.out.println("Duration: " + seconds) ;
	}
	
	//Returns seconds of the call
	public int getCallDuration() 
	{
		return seconds;
	}
}
