package dai.core.compute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import dai.write.read.txt.Write;

/**
 * ��ȡԴ�ļ�
 */
public class NodeTest {

	private HashMap<Double, Double> node1MapData = new HashMap<>(); // ���ڴ�Ž��� ��
	// �ڵ� ���� Ԫ��
	private HashMap<Double, Double> node2MapData = new HashMap<>();

	
	private static NodeTest nodeTest = new NodeTest(); // ����ʿ �����̰߳�ȫ

	public static NodeTest getInstance() {
		return nodeTest;
	}

	private NodeTest() {
	}

	static String path = "D:\\temp\\2010.xlsx"; // ·��,ָ���ļ�·��.Դ�ļ�
															// nlp-words-new.xlsx
															// test_index.xlsx
	IntersectionTwoNodes intersectionTwoNodes = new IntersectionTwoNodes();

	public void setPath(String path) {
		this.path = path;
	}


	// ����Sxy�����ǹ����ھ�
	public double Sxy(int x, int y, String p) {
		if (x == y) {
			System.out.println("S " + x + "��" + y + "���Լ����Լ�����");
			return 0;
		} else if (isNO_Concurrence(x, y)) {
			// �ж����ھ��Ƿ��н�����
			HashMap<Integer, HashMap<Double, Double>> outer_hashMap_X = AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> outer_hashMap_Y = AllNeighbors(y);

			if (intersectionTwoNodes.isIntersection(outer_hashMap_X.get(x), outer_hashMap_Y.get(y))) {
				HashMap<Double, Double> inner_hashMap_X = intersectionTwoNodes.getNode1MapData(); // �洢��X��Ϣ��صĹ�ͬ�ھ�
				HashMap<Double, Double> inner_hashMap_Y = intersectionTwoNodes.getNode2MapData(); // �洢��Y��Ϣ��صĹ�ͬ�ھ�
				// System.out.println(hashMap_X);
				return Weight(inner_hashMap_X, inner_hashMap_Y, x, y, p);

			} else {
				return 0;
			}

		} else {
			System.out.println("S " + x + "��" + y + "û��ֱ���ھ�");
			return 0;
		}
	}

	// ���� node1 �� node2 ֮���Ȩ��, node1 �� node2 ��ֱ���ھӽڵ�
	private double Weight(HashMap<Double, Double> map1, HashMap<Double, Double> map2, int node1, int node2, String p) {
		Collection<Double> nodeNames = map1.keySet();
		Iterator<Double> iterator = nodeNames.iterator();// �Թ����ھӽ��б���
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
				fen_mu = 2 * nodeTest.Sz((int) nodeName);
			}
			if (p.equals("A")) {
				fen_mu = 2 * intersectionTwoNodes.log2(1 + Sz((int) nodeName));
			}

			if (fen_mu != 0) {
				SUMxy += ((intersectionTwoNodes.stand_data(1, value1) + intersectionTwoNodes.stand_data(1, value2))
						/ fen_mu);
			}
		}
		return SUMxy;
	}

//	// ���� node1 �� node2 ֮���Ȩ��, node1 �� node2 ��ֱ���ھӽڵ�
//	private double Weight(HashMap<Double, Double> map, int node1, int node2) {
//		Collection<Double> nodeNames = map.keySet();
//		Iterator<Double> iterator = nodeNames.iterator();// �Թ����ھӽ��б���
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

	public double r_Weight(HashMap<Double, Double> map1, HashMap<Double, Double> map2, int node1, int node2, String p) {
		Collection<Double> nodeNames = map1.keySet();
		Iterator<Double> iterator = nodeNames.iterator();// �Թ����ھӽ��б���
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
				fen_mu = 2 * Sz((int) nodeName);
			}
			if (p.equals("A")) {
				fen_mu = 2 * intersectionTwoNodes.log2(1 + nodeTest.Sz((int) nodeName));
				// System.out.println(fen_mu);
			}

			SUMxy += ((intersectionTwoNodes.stand_data(1, value1) * intersectionTwoNodes.stand_data(1, value2))
					/ fen_mu);
		}
		return SUMxy;
	}

	// ���� Sz,û����
	public double Sz(int nodeName) {
		HashMap<Integer, HashMap<Double, Double>> key_AllNeighbors = nodeTest.AllNeighbors(nodeName);// {2={1.0=1.0,
		// 5.0=2.0}}
		HashMap<Double, Double> allNeighbors = key_AllNeighbors.get(nodeName);
		double sum = 0D;
		Iterator<Double> values = allNeighbors.values().iterator();
		while (values.hasNext()) {
			sum += intersectionTwoNodes.stand_data(1, values.next());
		}
		return sum;
	}

	// �ж������ڵ��Ƿ��֣�false ���� ���֣� true������ ������,
	public boolean isNO_Concurrence(int node1, int node2) {

		HashMap<Integer, HashMap<Double, Double>> outerMap1 = nodeTest.AllNeighbors(node1);
		HashMap<Double, Double> innerMap1 = outerMap1.get(node1);

		HashMap<Integer, HashMap<Double, Double>> outerMap2 = nodeTest.AllNeighbors(node2);
		HashMap<Double, Double> innerMap2 = outerMap2.get(node2);

		if (innerMap1.containsKey((double) node2)) {
			return false;
		} else {
			return true;
		}
	}

	private static XSSFSheet sheet = ReadWriteExcelFile.getSheet(path);

	// �������� ĳһ�ڵ� �����е��ھ�, return {2={1.0=1.0, 5.0=2.0}}
	// �������е��ھ�
	public HashMap<Integer, HashMap<Double, Double>> AllNeighbors(int nodeName) {
		XSSFRow row = ReadWriteExcelFile.readRow(sheet, nodeName);
		XSSFCell cell;
		Iterator cells = row.cellIterator();
		HashMap<Double, Double> hashMapInner = new HashMap<>();
		HashMap<Integer, HashMap<Double, Double>> hashMapOuter = new HashMap<>();

		int count = 0;
		while (cells.hasNext()) {

			cell = (XSSFCell) cells.next();

			// ��ȡ�Ķ�Ϊ��ֵ����
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
		// System.out.println("����: " + count + "�ڵ�" + nodeName + "���ھ� " +
		// hashMapOuter.toString());
		return hashMapOuter;
	}

}
