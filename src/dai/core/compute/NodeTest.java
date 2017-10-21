package dai.core.compute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import dai.excel.write.read.GetUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import dai.write.read.txt.Write;

/**
 * 获取源文件
 */
public class NodeTest {

	private HashMap<Double, Double> node1MapData = new HashMap<>(); // 用于存放交集 的
	// 节点 或者 元素
	private HashMap<Double, Double> node2MapData = new HashMap<>();
	public NodeTest() {
	}


    //static final String path = "D:\\temp\\2010.xlsx"; // 路径,指定文件路径.源文件
															// nlp-words-new.xlsx
															// test_index.xlsx
	//String path = "D:\\temp\\zeroDataAfter\\zero_xlsx\\2010_90.xlsx";

	IntersectionTwoNodes intersectionTwoNodes = new IntersectionTwoNodes();


	// 计算Sxy，考虑共有邻居
	public double Sxy(int x, int y, String p, XSSFSheet sheet) {
		if (x == y) {
			System.out.println("S " + x + "和" + y + "是自己和自己连接");
			return 0;
		} else if (isNO_Concurrence(x, y, sheet)) {
			// 判断其邻居是否有交集，
			HashMap<Integer, HashMap<Double, Double>> outer_hashMap_X = AllNeighbors(x, sheet);
			HashMap<Integer, HashMap<Double, Double>> outer_hashMap_Y = AllNeighbors(y, sheet);

			if (intersectionTwoNodes.isIntersection(outer_hashMap_X.get(x), outer_hashMap_Y.get(y))) {
				HashMap<Double, Double> inner_hashMap_X = intersectionTwoNodes.getNode1MapData(); // 存储与X信息相关的共同邻居
				HashMap<Double, Double> inner_hashMap_Y = intersectionTwoNodes.getNode2MapData(); // 存储与Y信息相关的共同邻居
				// System.out.println(hashMap_X);
				return Weight(inner_hashMap_X, inner_hashMap_Y, x, y, p, sheet);

			} else {
				return 0;
			}

		} else {
			System.out.println("S " + x + "和" + y + "没有直接邻居");
			return 0;
		}
	}

	// 计算 node1 与 node2 之间的权重, node1 与 node2 是直接邻居节点
	private double Weight(HashMap<Double, Double> map1, HashMap<Double, Double> map2, int node1, int node2, String p, XSSFSheet sheet) {
		Collection<Double> nodeNames = map1.keySet();
		Iterator<Double> iterator = nodeNames.iterator();// 对共有邻居进行遍历
		double SUMxy = 0;
		double value1 = 0;
		double value2 = 0;
		while (iterator.hasNext()) {
			double nodeName = iterator.next();
			value1 = map1.get(nodeName);
			value2 = map2.get(nodeName);
			
			double fen_mu = 0;

			if (p.equals("C")) {
				fen_mu = 2;
			}
			if (p.equals("R")) {
				fen_mu = 2 * Sz((int) nodeName, sheet);
			}
			if (p.equals("A")) {
				fen_mu = 2 * intersectionTwoNodes.log2(1 + Sz((int) nodeName, sheet));
			}

			if (fen_mu != 0) {
				SUMxy += ((intersectionTwoNodes.stand_data(1, value1) + intersectionTwoNodes.stand_data(1, value2))
						/ fen_mu);
			}
		}
		return SUMxy;
	}

//	// 计算 node1 与 node2 之间的权重, node1 与 node2 是直接邻居节点
//	private double Weight(HashMap<Double, Double> map, int node1, int node2) {
//		Collection<Double> nodeNames = map.keySet();
//		Iterator<Double> iterator = nodeNames.iterator();// 对共有邻居进行遍历
//		double SUMxy = 0;
//		while (iterator.hasNext()) {
//			double nodeName = iterator.next();
//			double value1 = node1MapData.get(nodeName);
//			double value2 = node2MapData.get(nodeName);
//			double fen_mu = 2 * myHashMap.log2(1 + nodeTest.Sz((int) nodeName));
//			SUMxy += ((value1 + value2) / fen_mu);
//		}
//		return SUMxy;
//	}

	public double r_Weight(HashMap<Double, Double> map1, HashMap<Double, Double> map2, int node1, int node2, String p, XSSFSheet sheet) {
		Collection<Double> nodeNames = map1.keySet();
		Iterator<Double> iterator = nodeNames.iterator();// 对共有邻居进行遍历
		double SUMxy = 0D;
		double value1 = 0D;
		double value2 = 0D;
		double fen_mu = 0D;
		while (iterator.hasNext()) {
			double nodeName = iterator.next();
			value1 = intersectionTwoNodes.stand_data(1, map1.get(nodeName));
			value2 = intersectionTwoNodes.stand_data(1, map2.get(nodeName));
			String param = p;

			if (p.equals("C")) {
				fen_mu = 2;
			}
			if (p.equals("R")) {
				fen_mu = 2 * Sz((int) nodeName, sheet);
			}
			if (p.equals("A")) {
				fen_mu = 2 * intersectionTwoNodes.log2(1 + Sz((int) nodeName, sheet));
				// System.out.println(fen_mu);
			}

			SUMxy += ((intersectionTwoNodes.stand_data(1, value1) * intersectionTwoNodes.stand_data(1, value2))
					/ fen_mu);
		}
		return SUMxy;
	}

	// 计算 Sz,没问题
	public double Sz(int nodeName, XSSFSheet sheet) {
		HashMap<Integer, HashMap<Double, Double>> key_AllNeighbors = AllNeighbors(nodeName, sheet);// {2={1.0=1.0,
		// 5.0=2.0}}
		HashMap<Double, Double> allNeighbors = key_AllNeighbors.get(nodeName);
		double sum = 0D;
		Iterator<Double> values = allNeighbors.values().iterator();
		while (values.hasNext()) {
			sum += intersectionTwoNodes.stand_data(1, values.next());
		}
		return sum;
	}

	// 判断两个节点是否共现，false 代表 共现， true代表是 不共现,
	public boolean isNO_Concurrence(int node1, int node2, XSSFSheet sheet) {
		HashMap<Integer, HashMap<Double, Double>> outerMap1 = AllNeighbors(node1, sheet);
		HashMap<Double, Double> innerMap1 = outerMap1.get(node1);

		HashMap<Integer, HashMap<Double, Double>> outerMap2 = AllNeighbors(node2, sheet);
		HashMap<Double, Double> innerMap2 = outerMap2.get(node2);

		if (innerMap1.containsKey((double) node2)) {
			return false;
		} else {
			return true;
		}
	}


	// 计算所有 某一节点 的所有的邻居, return {2={1.0=1.0, 5.0=2.0}}
	// 计算所有的邻居
	public HashMap<Integer, HashMap<Double, Double>> AllNeighbors(int nodeName, XSSFSheet sheet)
    {

		XSSFRow row = ReadWriteExcelFile.readRow(sheet, nodeName);
		XSSFCell cell;
		Iterator cells = row.cellIterator();
		HashMap<Double, Double> hashMapInner = new HashMap<>();
		HashMap<Integer, HashMap<Double, Double>> hashMapOuter = new HashMap<>();

		int count = 0;
		while (cells.hasNext()) {

			cell = (XSSFCell) cells.next();

			// 读取的都为数值类型
			double value = cell.getNumericCellValue();

			if (count >= 1) {
				if (value != 0) {
					// System.out.println("c = " + count + " value " + value);
					hashMapInner.put((double) count, value);
				}
			}
			count++;
		}
		hashMapOuter.put(nodeName, hashMapInner);
		// System.out.println("列数: " + count + "节点" + nodeName + "的邻居 " +
		// hashMapOuter.toString());
		return hashMapOuter;
	}

}
