package calc.util;

public class Comp {
    public static int comp(String a, String b) {
        int Flag = 1;                       //Flag为1 a>b,2 a=b,0a<b
        int[] c = new int[a.length()];      //a是c的int数组
        int[] d = new int[b.length()];      //b是d的int数组

        for (int i = 0; i < a.length(); i++) {      //c对a的赋值
            c[i] = a.charAt(i) - 48;
        }
        for (int i = 0; i < b.length(); i++) {      //d对b的赋值
            d[i] = b.charAt(i) - 48;
        }

        if (c.length > d.length) {                      //先对长度判断，
        } else if (c.length < d.length) {
            Flag = 0;
        } else {
            for (int i = 0; i < c.length; i++) {           //根据高位判断
                if (c[i] > d[i]) {
                    Flag = 1;
                    break;
                } else if (c[i] < d[i]) {
                    Flag = 0;
                    break;
                } else if (c[i] == d[i]) {
                    Flag = 2;
                }
            }
        }
        return Flag;                                 //返回判断
    }
}
