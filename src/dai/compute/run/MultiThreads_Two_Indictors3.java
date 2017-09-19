package dai.compute.run;

import dai.excel.write.read.MainApp;

public class MultiThreads_Two_Indictors3 implements Runnable{
	
	public static void main(String[] args) {
		Thread thread = new Thread(new MultiThreads_Two_Indictors3());
		thread.start();
	}
	public MainApp test = new MainApp();
	@Override
	public void run() {		
		test.computeTop_7_10_index();
	}

}
