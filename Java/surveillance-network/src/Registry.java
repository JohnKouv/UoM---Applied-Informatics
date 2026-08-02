import java.util.ArrayList;

public class Registry {
	
	//Lists Declaration
	private ArrayList<Communication> communications = new ArrayList<Communication>(); // List with all Communications (PhoneCall or SMS)
	private ArrayList<Suspect> suspects = new ArrayList<Suspect>(); //List with all suspects
	
	//Inserts a suspect in suspects List
	public void addSuspect(Suspect aSuspect) 
	{
		suspects.add(aSuspect); 
	}

	//Registers a new communication with update on possible partners list on both of the two suspects 
	public void addCommunication(Communication aCommunication) 
	{
		communications.add(aCommunication); // Registration of the communication
		
		for(int i=0;i<suspects.size();i++)
		{
			//Checks if someone has in his list the first number Num1 
			if(suspects.get(i).telephone_numbers.contains(aCommunication.Num1)) 
			{
				for(int j=0;j<suspects.size();j++) 
				{
					//Checks if someone has in his list the second number Num2
					if(suspects.get(j).telephone_numbers.contains(aCommunication.Num2)) 
					{
						//Update of the Possible Partners List
						suspects.get(j).possible_partners.add(suspects.get(i)); 
						suspects.get(i).possible_partners.add(suspects.get(j));
					}
				}
			}
		}
	}
	
	//Returns suspect with most partners
	//In case of more than one suspects with the same max possible partners , the method returns the last one   
	public Suspect getSuspectWithMostPartners() 
	{
		int max = 0 ; // Holds the max at every repetition , the number of  the most partners
		int pos = 0 ; // Holds the position of the max 
		
		for(int i=0;i<suspects.size();i++) 
		{	
			//Possible Partners of the new suspect each time
			for(int j=0;j<suspects.get(i).possible_partners.size();j++) 
			{
				//Checks if current suspect has most partners than the current max
				if(suspects.get(i).possible_partners.size() > max) 
				{
						// if == true , means the current number should be declared the max one
						max = suspects.get(i).possible_partners.size();
						pos=i; // The pos variable is updated , so that it shows the position of the new max
				}
			}
		}
		return suspects.get(pos); 
	}
	
	//Returns the longest PhoneCall between number1 and number2
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) 
	{
		int phoneCall_max = 0 ;
		PhoneCall maxDuration = null;
		
		for(Communication i : communications)
		{
			//Checks if object c is of class PhoneCall
			if(i instanceof PhoneCall)	
			{
				PhoneCall phoneCall = (PhoneCall) i; 
				
				if(phoneCall.getNum1().equals(number1) && phoneCall.getNum2().equals(number2))
				{
					//Check if the current duration of the call is longer than the max one
					if(phoneCall.getCallDuration() > phoneCall_max) 
					{
						//Updates the max and the max duration
						phoneCall_max = phoneCall.getCallDuration();
						maxDuration = phoneCall;
					}
				}
			}
		}
		return maxDuration;
	}
	
	//Returns messages (SMS) between the number1 and number2 that contain certain words
	public ArrayList<SMS> getMessagesBetween(String number1, String number2) 
	{
		ArrayList<SMS> smsContain = new ArrayList<SMS>(); //Creation of a list that has all the messages that contain the certain words
		
		for(Communication i : communications)
		{
			if(i instanceof SMS) 
			{
				SMS sms = (SMS) i; 

				if(sms.getNum1().equals(number1) && sms.getNum2().equals(number2)) 
				{
					//Checks if in the message there are certain words
					if(sms.getSMS().contains("Bomb") || sms.getSMS().contains("Attack") || sms.getSMS().contains("Explosives") || sms.getSMS().contains("Gun")) 
					{
						smsContain.add(sms); // After the checks , adds the sms in the smsConain list
					}
				}
			}
		}
		return smsContain;
	}
	
public void printSuspectsFromCountry(String country) 
{
		System.out.println("Suspects coming from " + country +":");
		
		for(Suspect i:suspects)
		{
			if (i.getCountry().equals(country))
			{
				System.out.println(i.getName() +" (" + i.getCodeName() + ")");
			}
		}
		
	}
	
}