package dai.excel.write.read;

import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class WriteExcel_Navigator {
	static ReadExcel excel = new ReadExcel();
	static String titles[] = new String[500];
	public static void main(String[] args) {
		add_navigator();
	}
	
	public static void add_navigator(){
		String path = "D:\\temp\\navigater.xlsx";
		XSSFSheet sheet = excel.getSheet(path, 0);
		XSSFRow row = excel.readRow(sheet, 0);
		Iterator cells = row.cellIterator();
		int index = 0;
		XSSFCell cell;
		String words = "";
		while (cells.hasNext()){
			//String content = (String) cells.next();
			
			cell = (XSSFCell) cells.next();				
			if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
				words = cell.getStringCellValue();
				//System.out.print(words + "           ");
			}
			
			titles[index++] = words;
			System.out.println(index + " " + titles[index]);
		}
		
	}

}
