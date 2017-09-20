package dai.core.compute;

import dai.excel.write.read.MainApp;

import java.util.Random;

/**
 *  不要计算的指标 负数指标
 */
public class ComputeAUCIndex {
    public static void main(String[] args) {
        int[] array = getRandonArray();
        System.out.println(array[89]);
    }


    public static int[] getRandonArray() {
        Random random = new Random();
        int[] randomArr = new int[90];
        for (int i = 0; i <90; i++) {
            int temp = random.nextInt(48);// [0,48)
            randomArr[i] = temp + 1;
        }
        return randomArr;
    }

    // 1. 随机选择单元格 让他们为0
    public void test()
    {
        for (int i = 0; i < 100; i++) {
            int cellNumber = 180;
            int dui = 90;
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
