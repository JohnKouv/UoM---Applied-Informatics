package final_omada_20;

public class Main {

		public static void main(String[] args) {
			
			CarOps C1 = new CarOps();
			C1.ConstructRepairObjects();
			System.out.println("Repairs Characteristics -------");
			C1.printData();
			System.out.println("Spare Parts Characteristics----");
			C1.printSpareParts();
			System.out.println("Repairs total characteristics--");
			C1.printRepairs();

	}
}
