package dai.excel.write.read;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dai.test.txt.WriteDataOnTXT;


/**
 * feng shu ting suoyong
 */

public class ReadExcel {
	public static void main(String[] args) throws Exception{
		String path = "D:\\temp\\feng2.0\\2013spa_rawdata.xlsx";
		String pathComA = "D:\\temp\\feng2.0\\comA.txt";
		String pathComB = "D:\\temp\\feng2.0\\comB.txt";
		String pathComC = "D:\\temp\\feng2.0\\comC.txt";
		String pathComD = "D:\\temp\\feng2.0\\comD.txt";
		String pathComE = "D:\\temp\\feng2.0\\comE.txt";
		String pathComF = "D:\\temp\\feng2.0\\comF.txt";
		
		WriteDataOnTXT dataOnTXT = new WriteDataOnTXT();
		
		XSSFSheet bSheet = getSheet(path, 1);
		HashMap<Integer, Double> bHashMap = new HashMap<>();
		HashMap<Integer, Double> bData= readXLSXFile(bSheet, bHashMap);
		
		XSSFSheet fSheet = getSheet(path, 2);
		HashMap<Integer, Double> fHashMap = new HashMap<>();
		HashMap<Integer, Double> fData= readXLSXFile(fSheet, fHashMap);
		
		XSSFSheet aSheet = getSheet(path, 0);		
		HashMap<String, Double> aHashMap = new HashMap<>();
		for (int i =1;i<= 123;i++){
			for (int j =1;j<= 123;j++){
				double aValue = readXLSXFile(aSheet, i, j);
				aHashMap.put(i+"_"+j, aValue);
			}
		}
		System.out.println(aHashMap.size());
		long num = 1;
		for (int l=1;l<=123;l++){
			for (int j=1;j<=123;j++){
				for (int i=1;i<=123;i++){
					double bValue = bData.get(l);
					double aL = aHashMap.get(l+"_"+j);
					double aJI = aHashMap.get(j+"_"+i);
					double fValue = fData.get(i);
					double content = bValue * aL * aJI * fValue;
					num++;
					if (num <= 310000){
						dataOnTXT.write(pathComA, l+"_"+j+"_"+i+":   "+content );
					} else if ( num <= 310000*2 && num > 310000){
						dataOnTXT.write(pathComB, l+"_"+j+"_"+i+":   "+content );
					} else if (num > 310000*2 && num <= 310000*3){
						dataOnTXT.write(pathComC, l+"_"+j+"_"+i+":   "+content );
					} else if (num > 310000*3 && num <= 310000*4){
						dataOnTXT.write(pathComD, l+"_"+j+"_"+i+":   "+content );
					} else if (num > 310000*4 && num <= 310000*5){
						dataOnTXT.write(pathComE, l+"_"+j+"_"+i+":   "+content );
					} else {
						dataOnTXT.write(pathComF, l+"_"+j+"_"+i+":   "+content );
					}
					
				}
			}
			
		}
	}
	
	//读取excel内容
	public static HashMap<Integer, Double> readXLSXFile(XSSFSheet sheet,HashMap<Integer, Double> hashMap) throws Exception{		
		XSSFRow row;
		XSSFCell cell;		
		Iterator rows = sheet.iterator();
		int num = 0;
		while(rows.hasNext()){
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			String words = "";
			double bValue = 0L;
			
			while (cells.hasNext())
			{				
				cell = (XSSFCell) cells.next();				
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					words = cell.getStringCellValue();
					//titles[num] = words;					
				}
				else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
					bValue = cell.getNumericCellValue();
				}							
			}				
			hashMap.put(num, bValue);
			num++;
		}
		return hashMap;
	}	
	
	//读取excel内容
	public static HashMap<String, Double> readXLSXFile(XSSFSheet sheet, int rowIndex, int colIndex, HashMap<String, Double> hashMap) throws Exception{		
		
		XSSFRow row = sheet.getRow(rowIndex);
		XSSFCell cell = row.getCell(colIndex);
		Double aValue = cell.getNumericCellValue();
		hashMap.put(rowIndex+"_"+colIndex, aValue);
  		
		return hashMap;
	}
	
	//读取excel内容
	public static double readXLSXFile(XSSFSheet sheet, int rowIndex, int colIndex) throws Exception{		
		System.out.println("读取指定的行");	
		XSSFRow row = sheet.getRow(rowIndex);
		XSSFCell cell = row.getCell(colIndex);
		double aValue = cell.getNumericCellValue();
  		
		return aValue;
	}
	//获取指定的行
	public static XSSFRow readRow(XSSFSheet sheet,int rowIndex){		
		return sheet.getRow(rowIndex); //下标从0开始计算		
	}
	//获得sheet
	public static XSSFSheet getSheet(String path,int index){
		XSSFSheet sheet = null;
		if (sheet == null){			
			InputStream excelFile;
			XSSFWorkbook workbook = null;
			try {
				excelFile = new FileInputStream(path);
				workbook = new XSSFWorkbook(excelFile);
			} catch (Exception e) {				
				e.printStackTrace();
			}			
			sheet = workbook.getSheetAt(index);			
		}
		return sheet;
	}	
}
