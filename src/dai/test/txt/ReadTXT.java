package dai.test.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ReadTXT {

	/**
	 * ��ȡtxt�ļ�������
	 * 
	 * @param file
	 *            ��Ҫ��ȡ���ļ�����
	 * @return �����ļ�����
	 */
	public String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// ����һ��BufferedReader������ȡ�ļ�
			String s = null;
			while ((s = br.readLine()) != null) { // ʹ��readLine������һ�ζ�һ��
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	// ���Ǳ�������
	public static void readTxtFile(String filePath) {
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		File file = new File("I:\\temp\\DATA\\DATA_quanwen\\Data (1).txt");
		//System.out.println(txt2String(file));
		//readTxtFile("I:\\temp\\DATA\\DATA_Wendang\\Wendang (2).txt");
	}

}















