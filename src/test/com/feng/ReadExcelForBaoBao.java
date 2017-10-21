package test.com.feng;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dai.test.txt.WriteDataOnTXT;

public class ReadExcelForBaoBao {

	public HashMap<String, Double> readExcel(String basePath, String fileName) {
		//String basePath = "D:\\temp\\index_extra_rows\\AA\\";
		XSSFSheet bSheet = getSheet(basePath + fileName, 0);
		HashMap<String, Double> bHashMap = new HashMap<>();
        HashMap<String, Double> bData= null;
        try {
            bData = readXLSXFile(bSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bData;
	}
	
	//��ȡexcel����
	private HashMap<String, Double> readXLSXFile(XSSFSheet sheet) throws Exception{
		XSSFRow row;
		XSSFCell cell;		
		Iterator rows = sheet.iterator();
		int num = 0;
		HashMap<String, Double> hashMap = new HashMap<>();
		//�б���
		while(rows.hasNext())
        {
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			String words = null;
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
			if (bValue > 0.0) {
                hashMap.put(words, bValue);
            }
			num++;
		}
		return hashMap;
	}	
	
	//��ȡexcel����
	public static HashMap<String, Double> readXLSXFile(XSSFSheet sheet, int rowIndex, int colIndex, HashMap<String, Double> hashMap) throws Exception{		
		
		XSSFRow row = sheet.getRow(rowIndex);
		XSSFCell cell = row.getCell(colIndex);
		Double aValue = cell.getNumericCellValue();
		hashMap.put(rowIndex+"_"+colIndex, aValue);
  		
		return hashMap;
	}
	
	//��ȡexcel����
	public static double readXLSXFile(XSSFSheet sheet, int rowIndex, int colIndex) throws Exception{		
		System.out.println("��ȡָ������");	
		XSSFRow row = sheet.getRow(rowIndex);
		XSSFCell cell = row.getCell(colIndex);
		double aValue = cell.getNumericCellValue();
  		
		return aValue;
	}
	//��ȡָ������
	public static XSSFRow readRow(XSSFSheet sheet,int rowIndex){		
		return sheet.getRow(rowIndex); //�±��0��ʼ����		
	}
	//���sheet
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

