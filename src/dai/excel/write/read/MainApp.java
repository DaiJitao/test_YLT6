package dai.excel.write.read;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tingting.add.indicator.Compute_index;

public class MainApp {

    public static void main(String[] args) {
        MainApp testWriteExcel = new MainApp();
        testWriteExcel.computeTop_00_index();
        testWriteExcel.computeTop_3_index();
        testWriteExcel.computeTop_4_6_index();
        testWriteExcel.computeTop_7_10_index();
        testWriteExcel.computeTop_11_14_index();
    }

    /**
     * 计算指标 save to local
     */
    public void computeTop_3_index() {
        int rowIndex = 48;
        int cellIndex = 48;
        String name1 = "Salton"; // 1，
        Salton_createXLSX_And_saveData("D:\\temp\\index\\" + name1 + ".xlsx", name1, rowIndex, cellIndex);//
        String name2 = "Jaccard";
        Jaccard_createXLSX_And_saveData("D:\\temp\\index\\" + name2 + ".xlsx", name2, rowIndex, cellIndex);//
        String name3 = "Srenson"; // 1，
        Salton_createXLSX_And_saveData("D:\\temp\\index\\" + name3 + ".xlsx", name3, rowIndex, cellIndex);//

        String name4 = "CN"; // 1，
        //Salton_createXLSX_And_saveData("D:\\temp\\index\\" + name4 + ".xlsx", name4, rowIndex, cellIndex);//
        CN_createXLSX_And_saveData("D:\\temp\\index\\" + name4 + ".xlsx", name4, rowIndex, cellIndex);
    }

    public void computeTop_00_index() {
        int rowIndex = 48;
        int cellIndex = 48;
        String name1 = "Salton"; // 1，
        Salton_createXLSX_And_saveData("D:\\temp\\index\\" + name1 + ".xlsx", name1, rowIndex, cellIndex);//
        String name11 = "WRA"; // 1，
        WRA_createXLSX_And_saveData("D:\\temp\\index\\" + name11 + ".xlsx", name11, rowIndex, cellIndex);//
        String name4 = "greater_nodePre";
        greater_nodePre_createXLSX_And_saveData("D:\\temp\\index\\" + name4 + ".xlsx", name4, rowIndex, cellIndex);//
    }

    public void computeTop_4_6_index() {
        int rowIndex = 48;
        int cellIndex = 48;
        String name4 = "greater_nodePre";
        greater_nodePre_createXLSX_And_saveData("D:\\temp\\index\\" + name4 + ".xlsx", name4, rowIndex, cellIndex);//
        String name5 = "greater_nodeBad"; // 1，
        greater_nodeBad_createXLSX_And_saveData("D:\\temp\\index\\" + name5 + ".xlsx", name5, rowIndex, cellIndex);//
        String name6 = "LHNI";
        LHNI_createXLSX_And_saveData("D:\\temp\\index\\" + name6 + ".xlsx", name6, rowIndex, cellIndex);//
    }

    public void computeTop_7_10_index() {
        int rowIndex = 48;
        int cellIndex = 48;
        String name7 = "AA"; // 1，
        AA_createXLSX_And_saveData("D:\\temp\\index\\" + name7 + ".xlsx", name7, rowIndex, cellIndex);//
        String name8 = "RA";
        RA_createXLSX_And_saveData("D:\\temp\\index\\" + name8 + ".xlsx", name8, rowIndex, cellIndex);//

        String name9 = "WCN"; // 1，
        WCN_createXLSX_And_saveData("D:\\temp\\index\\" + name9 + ".xlsx", name9, rowIndex, cellIndex);//
        String name10 = "WAA";
        WAA_createXLSX_And_saveData("D:\\temp\\index\\" + name10 + ".xlsx", name10, rowIndex, cellIndex);//
    }

    public void computeTop_11_14_index() {
        int rowIndex = 48;
        int cellIndex = 48;
        String name11 = "WRA"; // 1，
        WRA_createXLSX_And_saveData("D:\\temp\\index\\" + name11 + ".xlsx", name11, rowIndex, cellIndex);//
        String name12 = "rWCN";
        rWCN_createXLSX_And_saveData("D:\\temp\\index\\" + name12 + ".xlsx", name12, rowIndex, cellIndex);//
        String name13 = "rWAA"; // 1，
        rWAA_createXLSX_And_saveData("D:\\temp\\index\\" + name13 + ".xlsx", name13, rowIndex, cellIndex);//
        String name14 = "rWRA";
        rWRA_createXLSX_And_saveData("D:\\temp\\index\\" + name14 + ".xlsx", name14, rowIndex, cellIndex);//
    }


    static void createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表
        XSSFSheet sheet2 = wb.createSheet();
        XSSFSheet sheet3 = wb.createSheet();

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue("WAA_index");
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 253; i++) {
                for (int j = i + 1; j < 253; j++) {
                    int x = i + 1;
                    int y = j + 1;
                    double temp = compute_index.WAA_index(x, y);
                    System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                    if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        //System.out.println(rowNums + "行 "+ 1+"列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void Salton_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格 /////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.Salton_index(x, y);
                    //if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                   // }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void Jaccard_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.Jaccard_index(x, y);
                    //if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                   // }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void SΦrenson_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.SΦrenson(x, y);
                    //if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void greater_nodePre_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.greater_nodePre_index(x, y);
                   // if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                   // }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void greater_nodeBad_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.greater_nodeBad_index(x, y);
             //       if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
               //     }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void LHNI_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.LHNI_index(x, y);
                //    if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                  //  }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void AA_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.AA_index(x, y);
                    //if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void RA_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.RA_index(x, y);
                   // if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void WCN_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.WCN_index(x, y);
                  //  if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void WAA_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.WAA_index(x, y);
                   // if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void WRA_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.WRA_index(x, y);
                    //if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void rWCN_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.rWCN_index(x, y);
                    //if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void rWAA_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.rWAA_index(x, y);
               //     if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
             //       }


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void rWRA_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.rWRA_index(x, y);
                   // if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                    //}


                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void CN_createXLSX_And_saveData(String xlsxPath, String sheetName, int rowIndex, int cellIndex) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet1 = wb.createSheet(sheetName);//在下标0处，创建一张表

        //保存为xlsx文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(xlsxPath);
            XSSFRow rowHeader = sheet1.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell(1);
            cellHeader.setCellValue(sheetName);
            Compute_index compute_index = Compute_index.getInstance();
            /**
             * 在此写入单元格/////////////////////////////////////////////////////////////////////////////////
             */
            int rowNums = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i + 1; j < cellIndex; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    double temp = compute_index.CN_index(x, y);
                   // if (temp > 0.0) {
                        rowNums++;
                        XSSFRow row = sheet1.createRow(rowNums);
                        XSSFCell cell_0 = row.createCell(0);
                        XSSFCell cell_1 = row.createCell(1);

                        System.out.println(rowNums + "行 " + 1 + "列计算中..." + temp);
                        cell_0.setCellValue(x + "_" + y);
                        cell_1.setCellValue(temp);
                 //   }
                }
            }
            double time = (System.currentTimeMillis() - startTime) / 1000.0;
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

    static void close(FileOutputStream outPut) {
        try {
            outPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
