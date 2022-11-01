package calc.util;

public class Pow {
    public static String pow(String a, String b) {
        String temp = a;            //存储a
        for (int i = 0; i < Integer.parseInt(b) - 1; i++) {
            a = Mul.mul(a, temp);   //乘方就是循环n次乘法
        }
        return a;                   //返回结果
    }
}
