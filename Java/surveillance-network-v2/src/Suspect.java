import java.util.ArrayList;

public class Suspect 
{
	//Variable Declaration
	String name ;
	String coded_name ;
	String country ;
	String town ;
	
	//Lists Declaration
	ArrayList<String> telephone_numbers = new ArrayList<String>() ; //Telephone Number List
	ArrayList<Suspect> possible_partners = new ArrayList<Suspect>() ; //Possible Crime Partners List
	ArrayList<Suspect> common_partners = new ArrayList<Suspect>(); //Common Crime Partners List
	private ArrayList<Suspect> suggestedPartners = new ArrayList<Suspect>(); // Λίστα με προτεινόμενους πιθανούς συνεργάτες
	private ArrayList<Suspect> pospart = new ArrayList<Suspect>(); // Λίστα με προτεινόμενους πιθανούς συνεργάτες

	
	//Constructor
	public Suspect(String name, String coded_name, String country, String town)
	{
		this.name = name;
		this.coded_name = coded_name;
		this.country = country;
		this.town = town;
	}
	
	//Public methods of Suspect 
	
	public void addNumber(String number) 
	{
		telephone_numbers.add(number); 
	}
	
	public void addSuspect(Suspect aSuspect) 
	{
		boolean flag = false ; // Checks if the suspect is already been put into the possible_partners List
		// ANOTHER SOLUTION 
		/*for(int i=0; i<possible_partners.size(); i++) 
		{
			if(!possible_partners.get(i).equals(aSuspect) && flag==false) 
			{
				possible_partners.add(aSuspect);
				flag=true;
			}
		} */
		 int i = 0 ;
		 while (i<possible_partners.size() && flag==false)
		 {
		 		if(!possible_partners.get(i).equals(aSuspect) && flag==false) 
			{
				possible_partners.add(aSuspect);
				flag=true ;
			}
		 } 
	} 
	
	//Checks if two suspects are connected
	public boolean isConnectedTo(Suspect aSuspect) 
	{ 
		/*if(possible_partners.contains(aSuspect))
			return true;
		else 
			return false;*/
		 return (possible_partners.contains(aSuspect));
	}
	
	//Returns a list with all the common partners of the two suspects 
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) 
	{
		for(int i=0;i<possible_partners.size();i++) 
		{
				if(possible_partners.get(i).isConnectedTo(aSuspect) && !common_partners.contains(possible_partners.get(i)))
				{
					common_partners.add(possible_partners.get(i));
				}
		}
		
		return common_partners;
	}
	
	//Prints List of possible Partners of a suspect 
	// With * are those who come from the same country
	public void printInfo(Suspect aSuspect)
	{
		for(int i=0; i<possible_partners.size();i++) 
		{
			if (country == aSuspect.country)
			{
				System.out.println(possible_partners.get(i).name + ", " + possible_partners.get(i).coded_name + "*");
			}
			else
			{
				System.out.println(possible_partners.get(i).name + ", " + possible_partners.get(i).coded_name);
			}

		}
	}
	
	// Creates , prints and returns a list with the suggested possible partners 
	public ArrayList <Suspect> getSuggestedPartners() 
	{		
			Suspect aSuspect=null;
			for (Suspect i:possible_partners) 
			{	
				if(i.isConnectedTo(aSuspect) && possible_partners.contains(aSuspect)) 
					suggestedPartners.add(aSuspect);
			}
			System.out.println(suggestedPartners);
			return suggestedPartners;
			
			/*Suspect sSuspect = null;
			for (Suspect i:possible_partners)
			{
				if (i.isConnectedTo(sSuspect) && possible_partners.contains(sSuspect))
				{
					pospart.add(sSuspect);
					Suspect wSuspect = sSuspect ;
					for (Suspect j:pospart)
					{
						if(j.isConnectedTo(wSuspect) && !pospart.contains(wSuspect))
						{
							suggestedPartners.add(wSuspect);
						}
					}
				}
			}
			System.out.println(suggestedPartners);
			return suggestedPartners;*/
	}
	
	public String getName() {
		return name;
	}
	
	public String getCodeName() {
		return coded_name;
	}
		
	public String getCountry() {
		return country;
	}
	
	public ArrayList<String> getListOfNumbers() {
		return telephone_numbers;
	}
	
	public ArrayList<Suspect> getListOfAssociates(){
		return possible_partners;
	}
}