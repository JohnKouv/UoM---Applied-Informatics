import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFrame extends JFrame
{
	private JPanel Panel = new JPanel();
	private JTextField suspect_name = new JTextField("Please enter suspect's name");
	private JButton button_find = new JButton("Find");
	
	private Registry new_registry;
	private ArrayList<Suspect> suspects_list;
	
	public SearchFrame(Registry registry) 
	{
		
		new_registry = registry;
		suspects_list = registry.getSuspects();
		
		//Panel additions
		Panel.add(suspect_name);
		Panel.add(button_find);
		
		//ButtonListener addition
		ButtonListener listener = new ButtonListener();
		button_find.addActionListener(listener);
		
		//Panel in the Window
		this.setContentPane(Panel);
		
		//Window Creation
		this.setSize(350,150);
		this.setTitle("Find Suspect");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	class ButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String aName = suspect_name.getText();
			
			boolean flag = false;

			for(int j=0;j<suspects_list.size();j++) 
			{	
				if(aName.equals(suspects_list.get(j).getName())) 
				{
					new SuspectInfoFrame(new_registry,aName,j);
					SearchFrame.this.dispose();
					flag = true;
				}
			}
			if (flag==false) 
			{
				JOptionPane.showMessageDialog(null, "Suspect " + aName + " Not Found.");
			}
		}
		
	}
}
