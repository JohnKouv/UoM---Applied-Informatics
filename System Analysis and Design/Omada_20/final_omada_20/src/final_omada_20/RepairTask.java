package final_omada_20;

public class RepairTask {

	
	private String RepairType ;
	private int cost ;

	public RepairTask(String repairType, int cost) {
		RepairType = repairType; // Repair procedure type
		this.cost = cost; // Cost of repair , counted in euro
}

	public String getRepairType() {
		return RepairType;
	}

	public void setRepairType(String repairType) {
		RepairType = repairType;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	

}