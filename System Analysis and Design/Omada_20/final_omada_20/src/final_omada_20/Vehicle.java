package final_omada_20;

public class Vehicle {

	private String brand;
	private int productionYear;
	private String licensePlate;
	private String model;

	/**
	 * 
	 * @param license_plate
	 */
	public Vehicle getVehicleInfo(String license_plate) {
		// TODO - implement Vehicle.getVehicleInfo
		throw new UnsupportedOperationException();
	}

	public String getBrand() {
		return this.brand;
	}

	/**
	 * 
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getProductionYear() {
		return this.productionYear;
	}

	/**
	 * 
	 * @param productionYear
	 */
	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	/**
	 * 
	 * @param licensePlate
	 */
	public void setLicensePlate(int licensePlate) {
		// TODO - implement Vehicle.setLicensePlate
		throw new UnsupportedOperationException();
	}

	public String getModel() {
		return this.model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

}