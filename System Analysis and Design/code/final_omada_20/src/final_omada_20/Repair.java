package final_omada_20;

import java.util.ArrayList;

public class Repair {

	ArrayList<RepairTask> repairTasks = new ArrayList<RepairTask>() ;
	ArrayList<SparePartType> spareParts = new ArrayList<SparePartType>();
	
	
	private int days;

	public Repair(int days) {
		this.days = days;
	}
	
	public void addRepairTask(RepairTask task) {
		repairTasks.add(task);
	}
	
	public void addSparePart(SparePartType part) {
		spareParts.add(part);
	}
	
	public int getTotalCost() {
		
		int sum = 0 ;
		
		for(RepairTask repairtask : repairTasks) {
			sum += repairtask.getCost();
		}
		
		for(SparePartType sptype : spareParts) {
			sum += sptype.getCost()*sptype.getNumber();
		}
		
		return sum;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	

	
	
}