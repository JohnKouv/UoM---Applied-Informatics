package final_omada_20;

import java.util.ArrayList;

public class CarOps {
	ArrayList<RepairTask> repairTasks = new ArrayList<RepairTask>() ;
	ArrayList<SparePartType> spareParts = new ArrayList<SparePartType>();
	ArrayList<Repair> repairs = new ArrayList<Repair>();

	
	public void ConstructRepairObjects() {
		RepairTask RT1 = new RepairTask("Αλλαγή λαδιών", 20);
		RepairTask RT2 = new RepairTask("Αλλαγή φίλτρου καμπίνας", 5);
		RepairTask RT3 = new RepairTask("Συντήρηση φρένων", 30);

		repairTasks.add(RT1);	
		repairTasks.add(RT2);	
		repairTasks.add(RT3);	

	
		SparePartType SP1 = new SparePartType("Συσκευασία λαδιών 4lt", 30);
		SparePartType SP2 = new SparePartType("Φίλτρο λαδιού", 20);
		SparePartType SP3 = new SparePartType("Φίλτρο καμπίνας", 30);
		SparePartType SP4 = new SparePartType("Τακάκι φρένων εμπρός τροχού", 5);
		SparePartType SP5 = new SparePartType("Τακάκι φρένων πίσω τροχού", 5);
		SparePartType SP6 = new SparePartType("Υγρό φρένων", 10);
		
		spareParts.add(SP1);
		spareParts.add(SP2);
		spareParts.add(SP3);
		spareParts.add(SP4);
		spareParts.add(SP5);
		spareParts.add(SP6);
		
	
		Repair R1 = new Repair(1);
		R1.addRepairTask(RT1);
		R1.addRepairTask(RT2);
		SP1.setNumber(1);
		R1.addSparePart(SP1);
		SP2.setNumber(1);
		R1.addSparePart(SP2);
		SP3.setNumber(1);
		R1.addSparePart(SP3);
		
		repairs.add(R1);
		
		Repair R2 = new Repair(2);
		R2.addRepairTask(RT3);
		SP4.setNumber(4);
		R2.addSparePart(SP4);
		SP5.setNumber(4);
		R2.addSparePart(SP5);
		SP6.setNumber(1);
		R2.addSparePart(SP6);
		
		repairs.add(R2);

	}
	
	public void printData(){

		for (RepairTask i: repairTasks) {
		System.out.println("Repair Task Name: "+ i.getRepairType() + " and cost: " + i.getCost());
		}
	}
	
	public void printSpareParts() {
		for(SparePartType j:spareParts) {
			System.out.println("Spare Part Name: "+ j.getType() + " and cost: " + j.getCost());
		}
	}
	
	public void printRepairs() {
		for(Repair repair:repairs) {
			System.out.println("Repair days: "+ repair.getDays());
			System.out.println("Repair total cost: "+ repair.getTotalCost());
		}
	}
}