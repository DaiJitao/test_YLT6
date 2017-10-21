package dai.core.compute;

import dai.excel.write.read.MainApp;
import test.com.feng.ReadExcelForBaoBao;

import java.util.HashMap;
import java.util.Random;


/**
 * 不要计算的指标 负数指标
 */
public class ComputeAUCIndex {

    private static HashMap<String, Double> data240 = new HashMap<>(); //用于存放那些240行的数据
    static {
        ReadExcelForBaoBao baoBao = new ReadExcelForBaoBao();
        data240 = baoBao.readExcel("D:\\temp\\index_no_random\\2010_240rows\\2010\\", "WRA.xlsx");
    }

    private static Random random = new Random();

    //返回 [n,m]之间的随机数
    public static int getRandomInt(int min, int max)
    {
        int out = max + 1;
        int temp = random.nextInt(out - min); // [0, out - min -1]
        int result = temp + min;
        return result;
    }

    //随机选择90个cell
    public int[] getRandonArray() {
        int[] randomArr = new int[90];
        for (int i = 0; i < 90; i++) {
            int temp = getRandomInt(1, 889);// [1,889]
            randomArr[i] = temp;
        }
        return randomArr;
    }

    // 1. 随机选择单元格
    // 1.2.让他们为0, 进行数据的零化处理
    public void zeroCellIndex() {
        ReadIndexToCell toCell = new ReadIndexToCell();
        int[] randomIndex = getRandonArray();
        HashMap<Integer, String> hashMap = toCell.getIndexAndCell();

        for (int i = 0; i < 90; i++) {
            int key = randomIndex[i];
            int[] cellIndex = toCell.getCellIndex(key, hashMap);
            /** 零化数据集*/
        }
    }

    // 2. 在此利用零化数据再次计算一遍概率，肯定比原来多余90行
    public void computeAgain() {
        MainApp mainApp = new MainApp();
    }

    //3. 多90行， 提取多的90行
    public void getExtraRows() {
    }

    // 4.  比较 90行和 原来的240比较
    // 4.1 X_90 > X_S => n1
    // 4.2 X_90 = X_S => n11
    public double getA_AUC() {
        int n = 240 * 90;
        int n1 = getN1();
        int n11 = getN11();
        double A_AUC = (n1 + 0.5 * n11) / n;
        return A_AUC;
    }

    // 4.1 X_90 > X_S => n1
    public int getN1() {

        int X_90 = 0;
        int X_source = 0;
        int n1 = 0;
        if (X_90 > X_source)
            n1++;
        return n1;
    }

    public int getN11() {
        int X_90 = 0;
        int X_source = 0;
        int n11 = 0;
        if (X_90 == X_source)
            n11++;
        return n11;
    }

    public double getAUC()
    {
        double temp = 0.0;
        for (int i = 0; i < 100; i++)
        {
            temp = temp + getA_AUC();
        }
        return temp / 100;
    }


    /**
     *
     * @return {8_15=1.7259324552532591, 1_3=1.974273881358959, 2_19=0.5, 12_18=1.1309297535714573}
     */
    //首先找到240行仲没有的，组成map
    private HashMap<String,Double> getRemovedataFrom240(int i){
        HashMap<String, Double> data90 = new HashMap<>();
        ReadExcelForBaoBao baoBao = new ReadExcelForBaoBao();
        data90 = baoBao.readExcel("D:\\temp\\index_extra_rows\\WRA\\",  "WRA"+ i +".xlsx");
        //System.out.println(data90.size());
        //System.out.println(data240.size());
        HashMap<String, Double> resultHashMap = new HashMap<>();
        for (String key : data90.keySet())
        {
            if (!data240.containsKey(key)){//如果源数据没有
                resultHashMap.put(key, data90.get(key));
            }

        }
        return resultHashMap;
    }

    public static void main(String[] args) {
        ComputeAUCIndex aucIndex = new ComputeAUCIndex();


        double sum_AUC = 0.0;
        for (int i = 0; i < 100; i++) {
            HashMap<String, Double> resultDiff = aucIndex.getRemovedataFrom240(i);
            int diffLen = resultDiff.size();
            int sum_n = 240 * diffLen;
            int n1 = 0;
            int n2 = 0;
            for (String key : resultDiff.keySet())
            {
                double v_90 = resultDiff.get(key);
                for (String key_240 : data240.keySet()){
                    double v_240 = data240.get(key_240);
                    if (v_90 - v_240 > 0.0 ) {
                        n1++;
                    } else if (v_90 - v_240 == 0.0) {
                        n2++;
                    }
                }
            }
            double auc;
            if (sum_n == 0) {
                auc = 0.0;
            } else {
                auc = (n1 + 0.5 * n2) /sum_n;
            }
            sum_AUC += auc;
            System.out.println("" + i + ": " + auc);
        }
        System.out.println("avg: " + sum_AUC / 100);

    }

}
