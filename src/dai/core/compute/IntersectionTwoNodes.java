package dai.core.compute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class IntersectionTwoNodes {
	private  HashMap<Double, Double> node1MapData;//用于存放交集 的 节点 或者 元素
	private  HashMap<Double, Double> node2MapData;
	
	public IntersectionTwoNodes(){
		
	}
	
	public void setNode1MapData(HashMap<Double, Double> node1MapData) {
		this.node1MapData = node1MapData;
	}
	public void setNode2MapData(HashMap<Double, Double> node2MapData) {
		this.node2MapData = node2MapData;
	}
	public HashMap<Double, Double> getNode1MapData() {
		return node1MapData;
	}
	public HashMap<Double, Double> getNode2MapData() {
		return node2MapData;
	}

	//判断两个哈希表是否有交集(即是否有共同邻居),false 代表没有交集，
	public boolean isIntersection(HashMap<Double, Double> map1, HashMap<Double, Double> map2){
		HashMap<Double, Double> nodeXMapData = new HashMap<>();
		HashMap<Double, Double> nodeYMapData = new HashMap<>();
		boolean tmp = false;
		Set<Double> set1= map1.keySet();		
		
		Iterator<Double> iterator = set1.iterator();
		
		while (iterator.hasNext()) {
			double v1 = iterator.next();
//			System.out.print(v1);
//			System.out.println( "  " +map1.get(v1));
//			System.out.println(map2.containsKey(v1));
			if (map2.containsKey(v1)){				
				nodeXMapData.put(v1, map1.get(v1));	
				nodeYMapData.put(v1, map2.get(v1));
				tmp = true;
			}
		}
		setNode1MapData(nodeXMapData);
		setNode2MapData(nodeYMapData);
		return tmp;
		//return false;
	}
	
	public static double stand_data(int a, double value){	
		
		return Math.pow(Math.E, -(a/value));
	}
	
	
	public static void main(String[] args) {
		System.out.println(2/log2(4));
	}
	public static double log2(double N){
		return Math.log(N)/Math.log(2);
	}

}
