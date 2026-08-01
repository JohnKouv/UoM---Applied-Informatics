package final_omada_20;

public class SparePartType {

	private String type; // Type of the spare part
	private int cost; // Cost of each type
	private int number;
	
	public SparePartType(String type, int cost) {
		this.type = type;
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	

}