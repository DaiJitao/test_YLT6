package dai.write.read.txt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import dai.core.compute.NodeTest;
import tingting.add.indicator.Compute_index;

public class Write {
	//���ô���ģʽʵ��
	
	static Compute_index compute_index = Compute_index.getInstance();
	
    public static void main(String[] args) {
        write("D:/temp/123.txt");//�ļ�����
    }
 
    public static void write(String path) {
    	
        try {
            File f = new File(path);
             
            if (f.exists()) 
            {
                System.out.println("�ļ�����");
            } else 
            {
                System.out.println("�ļ������ڣ����ڴ���...");
                if (f.createNewFile()) {
                    System.out.println("�ļ������ɹ���");
                } else {
                    System.out.println("�ļ�����ʧ�ܣ�");
                }
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            //���д�Ĵ���
            long start = System.currentTimeMillis();
            for (int i = 1;i < 100; i++){//��
    			for (int j=i+1;j < 100; j++ ){//��
    				double temp = compute_index.WAA_index(i, j);
    				double temp2 = compute_index.WRA_index(i, j);
    				double temp3 = compute_index.WCN_index(i, j);
    				System.out.println("S"+ i +"__"+j+" : " + temp);
    				 output.write("S"+ i +"__"+j+" : " + temp +" " + temp2+ " " + temp3 +"\n");   				
    			}
    		}
            long time = (System.currentTimeMillis() - start)/1000;
    		System.out.println("������������" + time);
    		output.write("������������" + time);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			
		}
    }
}
