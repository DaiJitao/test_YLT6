package tingting.add.indicator;

public class Test_Compute_index {
	static Compute_index compute_index = Compute_index.getInstance();

	public static void main(String[] args) {
		 int x = 15, y = 83;
//		 print("AA_index", compute_index.AA_index(x, y));
//		 print("greater_nodeBad_index", compute_index.greater_nodeBad_index(x,y));
//		 print("greater_nodePre_index", compute_index.greater_nodePre_index(x, y));
//		
//		 print("Jaccard_index", compute_index.Jaccard_index(x, y));
//		 print("LHNI_index", compute_index.LHNI_index(x, y));
//		 print("RA_index", compute_index.RA_index(x, y));
//		
//		 print("rWAA_index", compute_index.rWAA_index(x, y));
		 print("rWCN_index", compute_index.rWCN_index(x, y));
//		 print("rWRA_index", compute_index.rWRA_index(x, y));
//		
//		 print("Salton_index", compute_index.Salton_index(x, y));
//		 print("S¦µrenson", compute_index.S¦µrenson(x, y));
//		 print("WAA_index", compute_index.WAA_index(3, 12));
//		
//		 print("WCN_index", compute_index.WCN_index(3, 12));
//		 print("WRA_index", compute_index.WRA_index(x, y));

	}

	static void print(String arg, double v) {
		System.out.println(arg + ":  " + v);
	}

}
