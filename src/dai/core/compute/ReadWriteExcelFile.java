package dai.core.compute;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelFile {
	private static XSSFSheet sheet = null;
	private ReadWriteExcelFile(){
		
	}

	/**
	 * get sheet from xlsx(local file)
	 * 本地文件，
	 * @param path
	 * @return
	 */
	public static XSSFSheet getSheet(String path){
		if (sheet == null){
			//String path = "D:\\temp\\test600.xlsx";
			InputStream excelFile;
			XSSFWorkbook workbook = null;
			try {
				excelFile = new FileInputStream(path);
				workbook = new XSSFWorkbook(excelFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			sheet = workbook.getSheetAt(0);			
		}
		return sheet;
	}

	public static void readXLSXFile(String path) throws Exception{
		InputStream excelFile = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		
		XSSFSheet sheet = workbook.getSheetAt(0);//读取第一张sheet
		
		XSSFRow row;
		XSSFCell cell;
		System.out.println("读取指定的行");
		
		System.out.println();
		
		Iterator rows = sheet.iterator();
		
		while(rows.hasNext()){
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext())
			{
				cell = (XSSFCell) cells.next();
				
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					System.out.print(cell.getStringCellValue() + " ");
				}
				else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
					System.out.print(cell.getNumericCellValue() + "  ");
				}
			}
			System.out.println();
		}
		
	}
		
	//读取指定的行
	public static XSSFRow readRow(XSSFSheet sheet,int rowIndex){		
		return sheet.getRow(rowIndex); //下标从0开始计算
		
	}

}
