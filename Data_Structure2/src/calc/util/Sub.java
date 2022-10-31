package calc.util;

public class Sub {
    public static String sub(String tempA, String tempB) {
        String a = null;                       //真正的a，比较大小之后赋值，a较大值
        String b = null;                       //真正的b，比较大小之后赋值，b较小值
        int[] e = new int[100];                //e是c的int数组
        int[] f = new int[100];                //f是d的int数组
        int[] res = new int[100];              //res是结果的int数组
        StringBuilder str = new StringBuilder();                       //返回的字符串
        boolean flag = false;                  //用于判断res数组是否到了非0处

        switch (Comp.comp(tempA, tempB)) {     //对a,b赋值
            case 1:
                a = tempA;
                b = tempB;
                break;
            case 0:
                a = tempB;
                b = tempA;
                break;
            case 2:
                return "0";
        }
        assert a != null;
        char[] c = a.toCharArray();            //c是a的char数组
        char[] d = b.toCharArray();            //d是b的char数组

        for (int i = 0; i < c.length; i++) {            //c对e的赋值
            e[100 - c.length + i] = (int) c[i] - 48;
        }
        for (int i = 0; i < d.length; i++) {            //d对f的赋值
            f[100 - d.length + i] = (int) d[i] - 48;
        }
        for (int i = 0; i < 100; i++) {                 //计算res,低位小于0，低位加10。高位减1
            res[i] = e[i] - f[i];
            if (res[i] < 0) {
                res[i - 1] -= 1;
                res[i] += 10;
            }
        }
        for (int i = 0; i < 100; i++) {        //对返回的字符串赋值
            if (res[i] != 0) flag = true;
            if (flag) {
                str.append(res[i]);
            }
        }
        if (Comp.comp(tempA, tempB) == 1) {
            return str.toString();                            //返回结果
        } else {
            return "-" + str;
        }
    }

    public static void main(String[] args) {
        System.out.println(sub("45", "9"));
    }
}
