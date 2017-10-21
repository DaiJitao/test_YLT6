package dai.core.compute;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by daijitao on 2017/9/21.
 */
public class ReadIndexToCell {
    static final String _path = "D:\\temp\\zeroDataBefore\\WAA.xlsx";

    public static void main(String[] args) {
        ReadIndexToCell readIndexToCell = new ReadIndexToCell();
        HashMap<Integer, String> hashMap = readIndexToCell.getIndexAndCell();
        String c = hashMap.get(2);
        System.out.println(c);
        System.out.println(readIndexToCell.getCellIndex(2, hashMap)[1]);

    }

    HashMap<Integer, String> getIndexAndCell()
    {
        ReadWriteExcelFile readWriteExcelFile = new ReadWriteExcelFile();
        XSSFSheet sheet = readWriteExcelFile.getSheet(_path);
        XSSFRow row = null; //ReadWriteExcelFile.readRow(sheet, 0);
        Iterator rows = sheet.iterator();
        HashMap<Integer, String> hashMap = new HashMap<>();
        int count = 1;
        while(rows.hasNext())
        {
            row = (XSSFRow) rows.next();
            String content = row.getCell(0).getStringCellValue();
            hashMap.put(count, content);
            //System.out.println("cout=" + count + " content=" + content);
            count++;
            /*
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
            */
        }
        return hashMap;

    }
    // 获取单元格下标
    int[] getCellIndex(int key, HashMap<Integer, String> map){
        String cells = map.get(key);
        String[] temp = cells.split("_");
        int[] result = new int[2];
        result[0] = Integer.valueOf(temp[0]);
        result[1] = Integer.valueOf(temp[1]);
        return result;
    }


}
