import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SuspectInfoFrame extends JFrame{

	private Border grey;
	
	// Window data declaration 
	private JPanel Central_Panel , suspect_info_p , SMS_p , Partners_p , sugg_Partners_p, same_country_p;
	private JButton SMS_b , Return_b;
	private JTextField sus_name , sus_coded_name, aNumberField;
	private JTextArea partners_f, sugg_partners_f, SMS_f , phone_f, same_country_f;
	private JLabel suspect_label , sugg_partners_label;
	
	// Declaration to point to the registry that I got from SearchFrame
	private Registry registry_c;
	// The position of the suspect
	int position;
	
	public SuspectInfoFrame(Registry re,String name, int pos) 
	{
		registry_c = re;
		this.position = pos;
		
		Central_Panel = new JPanel(); // Creation of the second panel of the window
		
		// Suspect panel 
		suspect_info_p = new JPanel();
		sus_name = new JTextField(name);
		sus_coded_name = new JTextField(registry_c.getSuspects().get(pos).getCodeName());
		
		// Phone Area
		phone_f = new JTextArea();
		String suspect_number= "";
		for(int i=0; i<registry_c.getSuspects().get(pos).getListOfNumbers().size(); i++) 
		{
			suspect_number += registry_c.getSuspects().get(pos).getListOfNumbers().get(i).toString() + "\n" ;
		}
		
		phone_f.append(suspect_number);
		
		suspect_info_p.add(sus_name);
		suspect_info_p.add(sus_coded_name);
		suspect_info_p.add(phone_f);
		
		phone_f.setPreferredSize(new Dimension(130,80));
		grey = BorderFactory.createLineBorder(Color.gray,1);
		suspect_info_p.setBorder(grey);
		
		// SMS Panel with the number that the user inserts
		SMS_p = new JPanel();
		aNumberField = new JTextField(10);
		SMS_f = new JTextArea();
		SMS_b = new JButton("Find SMS");
		
		SMS_p.add(aNumberField);
		SMS_p.add(SMS_f);
		SMS_p.add(SMS_b);
		
		SMS_p.setBorder(grey);
		SMS_f.setPreferredSize(new Dimension(200,150));
		
		// Button Listener
		FindSmsButtonListener findlistener = new FindSmsButtonListener();
		SMS_b.addActionListener(findlistener);
		
		// Possible Partners panel
		Partners_p = new JPanel();
		suspect_label = new JLabel("Partners");
		partners_f = new JTextArea();
		
		Collection<String> posPartList = new TreeSet<String>();
		
		for (Suspect i : registry_c.getSuspects().get(pos).getListOfAssociates()) 
		{
			posPartList.add(i.getName() + ", " + i.getCodeName() + "\n");
		}
		
		
		for(String ps : posPartList)
			partners_f.append(ps);
		
		Partners_p.add(suspect_label);
		Partners_p.add(partners_f);
		
		Partners_p.setBorder(grey);
		partners_f.setPreferredSize(new Dimension(200,150));
		
		// Possible Suggested Partners panel
		sugg_Partners_p = new JPanel();
		sugg_partners_label = new JLabel("Suggested Partners ----->");
		sugg_partners_f = new JTextArea();
		
		Collection<String> sugPosPartList = new TreeSet<String>();
		
		for (Suspect i : registry_c.getSuspects().get(pos).getSuggestedPartners())
		{
			posPartList.add(i.getName() + "\n");
		}
		
		Collection<String> noSugDups = new HashSet<String>(sugPosPartList);
		
		for (String sps : noSugDups)
		{
			sugg_partners_f.append(sps);
		}
		
		sugg_Partners_p.add(sugg_partners_label);
		sugg_Partners_p.add(sugg_partners_f);
		
		sugg_Partners_p.setBorder(grey);
		sugg_partners_f.setPreferredSize(new Dimension(200,100));
		
		same_country_p = new JPanel();
		same_country_f = new JTextArea();
		
		String sameCountrySuspects= "Suspects comming from Spain \n";
		for (Suspect i:registry_c.getSuspects()) 
		{
			if (i.getCountry().equals(registry_c.getSuspects().get(pos).getCountry())) 
			{
				sameCountrySuspects += i.getName() + "\n";
			}
		}
			
		same_country_f.append(sameCountrySuspects);
		
		same_country_p.add(same_country_f);
		
		same_country_p.setBorder(grey);
		same_country_f.setPreferredSize(new Dimension(300,100));
		
		//All the side panels in a central panel
		Central_Panel.add(suspect_info_p);
		Central_Panel.add(SMS_p);
		Central_Panel.add(Partners_p);
		Central_Panel.add(sugg_Partners_p);
		Central_Panel.add(same_country_p);
		
		// Return button
		Return_b = new JButton("Return to Search Screen");
		Central_Panel.add(Return_b);
		
		ReturnButtonListener listener = new ReturnButtonListener();
		Return_b.addActionListener(listener);
		
		this.setContentPane(Central_Panel);
		
		this.setTitle("Suspect Page");
		this.setSize(440,750);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	class ReturnButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			new SearchFrame(registry_c);
			SuspectInfoFrame.this.dispose();	
		}
	}
	
	class FindSmsButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String numberGiven = aNumberField.getText();
			ArrayList<SMS> messages = new ArrayList<SMS>();
			
			for(int i=0; i<registry_c.getSuspects().get(position).getListOfNumbers().size(); i++)
			{
				messages.addAll(registry_c.getMessagesBetween(registry_c.getSuspects().get(position).getListOfNumbers().get(i).toString(), numberGiven));
			}
			String allMess = "";
			for (SMS i : messages)
			{			
				allMess += i.getSMS() + "\n";
			}
			
			SMS_f.setText(allMess);
		}
	}
}
