package dai.write.read.txt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import dai.core.compute.NodeTest;
import tingting.add.indicator.Compute_index;

public class Write {
	//利用代理模式实现
	
	static Compute_index compute_index = Compute_index.getInstance();
	
    public static void main(String[] args) {
        write("D:/temp/123.txt");//文件名字
    }
 
    public static void write(String path) {
    	
        try {
            File f = new File(path);
             
            if (f.exists()) 
            {
                System.out.println("文件存在");
            } else 
            {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            //添加写的代码
            long start = System.currentTimeMillis();
            for (int i = 1;i < 100; i++){//行
    			for (int j=i+1;j < 100; j++ ){//列
    				double temp = compute_index.WAA_index(i, j);
    				double temp2 = compute_index.WRA_index(i, j);
    				double temp3 = compute_index.WCN_index(i, j);
    				System.out.println("S"+ i +"__"+j+" : " + temp);
    				 output.write("S"+ i +"__"+j+" : " + temp +" " + temp2+ " " + temp3 +"\n");   				
    			}
    		}
            long time = (System.currentTimeMillis() - start)/1000;
    		System.out.println("花费了秒数：" + time);
    		output.write("花费了秒数：" + time);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			
		}
    }
}
