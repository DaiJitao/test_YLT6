package dai.test.txt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteDataOnTXT {	
	public void write(String path,String content){
		
		BufferedWriter writer = open_and_create(path);
		try {
			writer.write(content + " \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(writer);		
	}
	
	public void write(BufferedWriter writer, String content){	
		
		try {			
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�����ļ�
    public BufferedWriter open_and_create(String path) {
    	BufferedWriter output = null;
        try {
            File f = new File(path);
             
            if (f.exists()) {
                System.out.println("�ļ�����");
            } else {
                System.out.println("�ļ������ڣ����ڴ���...");
                if (f.createNewFile()) {
                    System.out.println("�ļ������ɹ���");
                } else {
                    System.out.println("�ļ�����ʧ�ܣ�");
                }
            }
            output = new BufferedWriter(new FileWriter(f,true));
        } catch (Exception exception){
        	exception.printStackTrace();
        }
        return output;
    }
    
    //�ر��ļ�
    public void close(BufferedWriter writer){
    	try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}