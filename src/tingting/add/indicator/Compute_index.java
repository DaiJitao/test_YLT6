package tingting.add.indicator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import dai.core.compute.IntersectionTwoNodes;
import dai.core.compute.NodeTest;

public class Compute_index {

	private static Compute_index compute_index = new Compute_index();

	private Compute_index() {
	}

	public static Compute_index getInstance() { // 饿汉式，天生线程安全
		return compute_index;
	}

	NodeTest nodeTest = NodeTest.getInstance();// 获得nodeTest类
	IntersectionTwoNodes intersectionTwoNodes = new IntersectionTwoNodes();

	public double Jaccard_index(int nodeName1, int nodeName2) {
		boolean temp = nodeTest.isNO_Concurrence(nodeName1, nodeName2);
		// System.out.println("Jaccard_index isNO_Concurrence : " + temp);
		if (temp) {
			HashMap<Integer, HashMap<Double, Double>> hashMap1 = nodeTest.AllNeighbors(nodeName1); // 获得nodeName1的所有邻居
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(nodeName2);
			// System.out.println("hashMap1" + hashMap1);
			// System.out.println("hashMap2" + hashMap2);
			HashMap<Double, Double> inner1 = hashMap1.get(nodeName1);// 获得所有邻居的信息
			HashMap<Double, Double> inner2 = hashMap2.get(nodeName2);

			int node1_len = inner1.size();
			int node2_len = inner2.size();
			// System.out.println("node1_len : " + inner1.size());
			// System.out.println("node2_len : " + inner2.size());

			if (intersectionTwoNodes.isIntersection(inner1, inner2)) // 判断两个节点是否有交集
			{
				int con_nums = intersectionTwoNodes.getNode1MapData().size();// 获得共同节点的个数
				// System.out.println("共同邻居的长度 " + con_nums);
				// System.out.println((node1_len + node2_len - con_nums) != 0);
				if ((node1_len + node2_len - con_nums) > 0) {
					return (double) con_nums / (node1_len + node2_len - con_nums);
				} else {
					return 0;
				}
			} else {
				return 0;
			}

		} else {
			return 0;
		}

	}

	public double WAA_index(int nodeName1, int nodeName2) {
		return nodeTest.Sxy(nodeName1, nodeName2, "A");

	}

	public double WRA_index(int nodeName1, int nodeName2) {
		return nodeTest.Sxy(nodeName1, nodeName2, "R");

	}

	public double WCN_index(int nodeName1, int nodeName2) {
		return nodeTest.Sxy(nodeName1, nodeName2, "C");

	}

	public double rWAA_index(int nodeName1, int nodeName2) {
		int x = nodeName1;
		int y = nodeName2;
		if (x == y) {
			System.out.println("S " + x + "和" + y + "是自己和自己连接");
			return 0;
		} else if (nodeTest.isNO_Concurrence(x, y)) {
			// 判断其邻居是否有交集，
			HashMap<Integer, HashMap<Double, Double>> hashMap1 = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);

			if (intersectionTwoNodes.isIntersection(hashMap1.get(x), hashMap2.get(y))) {
				HashMap<Double, Double> hashMap_X = intersectionTwoNodes.getNode1MapData(); // 存储与X信息相关的共同邻居
				HashMap<Double, Double> hashMap_Y = intersectionTwoNodes.getNode2MapData(); // 存储与Y信息相关的共同邻居
				// System.out.println(hashMap_X);
				return nodeTest.r_Weight(hashMap_X, hashMap_Y, x, y, "A");
			} else {
				return 0;
			}
		} else {
			System.out.println("S " + x + "和" + y + "没有直接邻居");
			return 0;
		}
	}//

	public double rWRA_index(int nodeName1, int nodeName2) {
		int x = nodeName1;
		int y = nodeName2;
		if (x == y) {
			System.out.println("S " + x + "和" + y + "是自己和自己连接");
			return 0;
		} else if (nodeTest.isNO_Concurrence(x, y)) {
			// 判断其邻居是否有交集，
			HashMap<Integer, HashMap<Double, Double>> hashMap = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);

			if (intersectionTwoNodes.isIntersection(hashMap.get(x), hashMap2.get(y))) {
				HashMap<Double, Double> hashMap_X = intersectionTwoNodes.getNode1MapData(); // 存储与X信息相关的共同邻居
				HashMap<Double, Double> hashMap_Y = intersectionTwoNodes.getNode2MapData(); // 存储与Y信息相关的共同邻居
				// System.out.println(hashMap_X);
				return nodeTest.r_Weight(hashMap_X, hashMap_Y, x, y, "R");
			} else {
				return 0;
			}

		} else {
			System.out.println("S " + x + "和" + y + "没有直接邻居");
			return 0;
		}
	}// end

	public double rWCN_index(int nodeName1, int nodeName2) {
		int x = nodeName1;
		int y = nodeName2;
		if (x == y) {
			System.out.println("S " + x + "和" + y + "是自己和自己连接");
			return 0;
		} else if (nodeTest.isNO_Concurrence(x, y)) {
			// 判断其邻居是否有交集，
			HashMap<Integer, HashMap<Double, Double>> hashMap = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);

			if (intersectionTwoNodes.isIntersection(hashMap.get(x), hashMap2.get(y))) {
				HashMap<Double, Double> hashMap_X = intersectionTwoNodes.getNode1MapData(); // 存储与X信息相关的共同邻居
				HashMap<Double, Double> hashMap_Y = intersectionTwoNodes.getNode2MapData(); // 存储与Y信息相关的共同邻居
				// System.out.println(hashMap_X);
				return nodeTest.r_Weight(hashMap_X, hashMap_Y, x, y, "C");

			} else {

				return 0;
			}
		} else {
			System.out.println("S " + x + "和" + y + "没有直接邻居");
			return 0;
		}
	}

	//
	public double Salton_index(int x, int y) {
		int nums[] = get_Con_nums(x, y);
		int len = nums.length;
		if (len == 1) {
			return -1;
		} else {
			return nums[0] / Math.sqrt(nums[1] * nums[2]);
		}
	}

	public double LHNI_index(int x, int y) {
		int nums[] = get_Con_nums(x, y);
		int len = nums.length;
		if (len == 1) {
			return -1;
		} else {
			return (double) nums[0] / (nums[1] * nums[2]);
		}
	}

	//
	public double SΦrenson(int x, int y) {
		// System.out.println(nodeTest.isNO_Concurrence(x, y));
		if (nodeTest.isNO_Concurrence(x, y)) {
			HashMap<Integer, HashMap<Double, Double>> hashMap1 = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);
			HashMap<Double, Double> innerhashMap1 = hashMap1.get(x);
			HashMap<Double, Double> innerhashMap2 = hashMap2.get(y);

			int x_nums = innerhashMap1.size();
			int y_nums = innerhashMap2.size();
			// System.out.println(x_nums);

			if (intersectionTwoNodes.isIntersection(innerhashMap1, innerhashMap2)) {
				int size = intersectionTwoNodes.getNode2MapData().size();
				return (double) (2 * size) / (x_nums + y_nums);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public double greater_nodePre_index(int x, int y) {
		int nums[] = get_Con_nums(x, y);
		int len = nums.length;
		if (len == 1) {
			return -1;
		} else {
			double temp = Math.min(nums[1], nums[2]);
			if (temp == 0) {
				return 0;
			} else {
				return (2 * nums[0]) / temp;
			}
		}
	}// end

	public double CN_index(int x, int y) {
		int nums[] = get_Con_nums(x, y);
		int len = nums.length;
		if (len == 1) {
			return 0;
		} else {
			return nums[1] * nums[2];

		}
	}// end
		// 存疑

	public double greater_nodeBad_index(int x, int y) {
		int nums[] = get_Con_nums(x, y);
		int len = nums.length;
		if (len == 1) {
			return -1;
		} else {
			double temp = Math.max(nums[1], nums[2]);
			if (temp == 0) {
				return 0;
			} else {
				return (2 * nums[0]) / temp;
			}
		}
	}

	public double AA_index(int x, int y) {

		if (nodeTest.isNO_Concurrence(x, y)) {
			HashMap<Integer, HashMap<Double, Double>> hashMap1 = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);
			HashMap<Double, Double> innerhashMap1 = hashMap1.get(x);
			HashMap<Double, Double> innerhashMap2 = hashMap2.get(y);

			if (intersectionTwoNodes.isIntersection(innerhashMap1, innerhashMap2)) {
				HashMap<Double, Double> hashMap = intersectionTwoNodes.getNode1MapData();
				Set<Double> keys = hashMap.keySet();
				Iterator<Double> keysIterator = keys.iterator();
				double SUMxy = 0;
				while (keysIterator.hasNext()) {
					double p = keysIterator.next();
					int key = (int) p;
					int nums = nodeTest.AllNeighbors(key).get(key).size();
					if (nums != 0) {
						SUMxy += 1 / intersectionTwoNodes.log2(nums);
					} else {
						return 0;
					}

				}
				return SUMxy;
			} else {
				return 0;
			}
		} else {
			return 0;
		}

	}

	//
	public double RA_index(int x, int y) {
		if (nodeTest.isNO_Concurrence(x, y)) {
			HashMap<Integer, HashMap<Double, Double>> hashMap1 = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);
			HashMap<Double, Double> innerhashMap1 = hashMap1.get(x);
			HashMap<Double, Double> innerhashMap2 = hashMap2.get(y);

			if (intersectionTwoNodes.isIntersection(innerhashMap1, innerhashMap2)) {
				HashMap<Double, Double> hashMap = intersectionTwoNodes.getNode1MapData();
				Set<Double> keys = hashMap.keySet();
				Iterator<Double> keysIterator = keys.iterator();
				double SUMxy = 0.0;
				while (keysIterator.hasNext()) {
					double p = keysIterator.next();
					int key = (int) p;
					int nums = nodeTest.AllNeighbors(key).get(key).size();
					if (nums != 0) {
						SUMxy += 1.0 / nums;
					} else {
						return 0;
					}

				}
				return SUMxy;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	// 面向切面编程，只对分子进行处理
	int[] get_Con_nums(int x, int y) {
		int[] temp;
		if (nodeTest.isNO_Concurrence(x, y))
		{
			HashMap<Integer, HashMap<Double, Double>> hashMap1 = nodeTest.AllNeighbors(x);
			HashMap<Integer, HashMap<Double, Double>> hashMap2 = nodeTest.AllNeighbors(y);
			HashMap<Double, Double> innerhashMap1 = hashMap1.get(x);
			int x_nums = innerhashMap1.size();
			HashMap<Double, Double> innerhashMap2 = hashMap2.get(y);
			int y_nums = innerhashMap2.size();

			if (intersectionTwoNodes.isIntersection(innerhashMap1, innerhashMap2)) {
				temp = new int[3];
				temp[0] = intersectionTwoNodes.getNode1MapData().size();
				temp[1] = x_nums;
				temp[2] = y_nums;
			} else {
				temp = new int[1];
			}
		} else {
			temp = new int[1];// 代表直接相连
		}
		return temp;
	}

}
