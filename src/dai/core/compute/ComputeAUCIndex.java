package dai.core.compute;

import dai.excel.write.read.MainApp;

import java.util.HashMap;
import java.util.Random;


/**
 *  不要计算的指标 负数指标
 */
public class ComputeAUCIndex {
    public static void main(String[] args) {


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
            int temp = getRandomInt(1,889);// [1,889]
            randomArr[i] = temp;
        }
        return randomArr;
    }

    // 1. 随机选择单元格
    // 1.2.让他们为0, 进行数据的零化处理
    public void zeroCellIndex()
    {
        ReadIndexToCell toCell = new ReadIndexToCell();
        int[] randomIndex = getRandonArray();
        HashMap<Integer, String> hashMap = toCell.getIndexAndCell();

        for (int i = 0; i < 90; i++) {
            int key = randomIndex[i];
            int[] cellIndex = toCell.getCellIndex(key, hashMap);


        }

    }

    // 2. 在此利用零化数据再次计算一遍概率，肯定比原来多余90行
    public void computeAgain(){
        MainApp mainApp = new MainApp();

    }
    //3. 多90行， 提取多的90行
    public void getExtra(){

    }
    // 4.   比较 90行和 原来的240比较
    // 4.1 X_90 > X_S => n1
    // 4.2 X_90 = X_S => n11
    public double getA_AUC(){
        int n = 240 * 90;
        int n1 = getN1();
        int n11 = getN11();
        double A_AUC = (n1 + 0.5 * n11) / n;
        return A_AUC;
    }
    // 4.1 X_90 > X_S => n1
    public int getN1(){
        int X_90 = 0;
        int X_source = 0;
        int n1 = 0;
        if (X_90 > X_source)
            n1++;
        return n1;
    }
    public int getN11()
    {
        int X_90 = 0;
        int X_source = 0;
        int n11 = 0;
        if (X_90 ==  X_source)
            n11++;
        return n11;
    }

    public double getAUC(){
        double temp = 0.0;
        for (int i = 0; i < 100; i++) {
                 temp = temp + getA_AUC();
        }
        return temp / 100;
    }



}
