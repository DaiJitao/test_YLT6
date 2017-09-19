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
		BufferedWriter bufferedWriter = open_and_create(path);//���ļ�
		try {
			bufferedWriter.write("������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(bufferedWriter);
	}

	// �½��ļ�������
	public static BufferedWriter open_and_create(String path) 
	{
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
			output = new BufferedWriter(new FileWriter(f));
			String content = "";
			// ���д�Ĵ���
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
