package dai.compute.proxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dai.compute.run.Main;
import tingting.add.indicator.Compute_index;

public class Compute_index_proxy {
	
	private void Main() {
		new Compute_index_proxy().testWRA();

	}
	
	Compute_index compute_index = Compute_index.getInstance();

	
	public void testWRA() {
		String path = "D:\\temp\\WRA.txt";
		BufferedWriter bufferedWriter = open_and_create(path);//打开文件
		try {
			bufferedWriter.write("戴继涛");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(bufferedWriter);
	}

	// 新建文件，并打开
	public static BufferedWriter open_and_create(String path) 
	{
		BufferedWriter output = null;

		try {
			File f = new File(path);

			if (f.exists()) {
				System.out.println("文件存在");
			} else {
				System.out.println("文件不存在，正在创建...");
				if (f.createNewFile()) {
					System.out.println("文件创建成功！");
				} else {
					System.out.println("文件创建失败！");
				}
			}
			output = new BufferedWriter(new FileWriter(f));
			String content = "";
			// 添加写的代码
			// output.write(content);
			// output.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	
	public void close(BufferedWriter output){
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
