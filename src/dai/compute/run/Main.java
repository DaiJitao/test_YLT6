package dai.compute.run;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dai.compute.proxy.Compute_index_proxy;

public class Main {
//	
//	public static void main(String[] args) {
//		new Main().test();
//	}	
		
	private void test() {
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		MultiThreads_Two_Indictors1 indictors1 = new MultiThreads_Two_Indictors1();
		MultiThreads_Two_Indictors2 indictors2 = new MultiThreads_Two_Indictors2();
		MultiThreads_Two_Indictors3 indictors3 = new MultiThreads_Two_Indictors3();
		MultiThreads_Two_Indictors4 indictors4 = new MultiThreads_Two_Indictors4();
		
		executorService.submit(indictors1);
		executorService.submit(indictors2);
		executorService.submit(indictors3);
		executorService.submit(indictors4);

	}

}
