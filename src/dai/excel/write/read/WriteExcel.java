package dai.excel.write.read;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tingting.add.indicator.Compute_index;

public class WriteExcel {
	
	public static void main(String[] args) {		
		createXLSX_And_saveData("D:\\temp\\navigator.xlsx","navigator",0,0);
	}
	
	static void createXLSX_And_saveData(String xlsxPath, String sheetName,int rowIndex, int cellIndex){
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表
		HashMap<Integer, String> hashMap = new HashMap<>();
		//保存为xlsx文件
		FileOutputStream output = null;
		try {		
			output = new FileOutputStream(xlsxPath);
			XSSFRow rowHeader = sheet1.createRow(0);
			XSSFCell cellHeader = rowHeader.createCell(1);
			cellHeader.setCellValue(sheetName);
			Compute_index compute_index = Compute_index.getInstance();
			XSSFSheet sheet = ReadExcel.getSheet("D:\\temp\\myTest_index.xlsx", 0);
			HashMap<Integer, String> hashMapParam = new HashMap<>();
			/**
			 * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
			 */
			int rowNums = 0;
			long startTime = System.currentTimeMillis();
			
			for (int i=0;i < 253;i++){				
				for (int j = i+1;j<253;j++){
					int x = i+1;
					int y = j+1;
					rowNums++;
					XSSFRow row = sheet1.createRow(rowNums);
					XSSFCell cell_0 = row.createCell(0);
					XSSFCell cell_1 = row.createCell(1);					
					
					System.out.println(rowNums+ "行 "+ 1+"列计算中..." );
					cell_0.setCellValue(x+ "_" + y);
//					hashMap = ReadExcel.readXLSXFile(sheet,hashMapParam);
					
					cell_1.setCellValue(hashMap.get(x) + "+" + hashMap.get(y));					
				}
			}
			double time = (System.currentTimeMillis() - startTime)/1000.0;   
			System.out.println(time + " s");
			//cell.setCellValue(0);			
			/**
			 * 结束 ////////////////////////////////////////////////////////////////////////////////////////
			 */
			wb.write(output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(output);
			System.out.println("计算完成！");
		}		
	}//end

	static void close(FileOutputStream outPut){
		try {
			outPut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
