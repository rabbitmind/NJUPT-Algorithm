package calc.util;

public class Mul {
    //乘法函数
    public static String mul(String c, String d) {
        int[] a = new int[c.length()];                //c是a的int数组
        int[] b = new int[d.length()];                //d是b的int数组
        int[] result = new int[a.length + b.length];  //result是结果的int数组
        int[] trueRes = new int[result.length - 1];   //treRes是result的截取，去除最后一位result最后一位是0
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < c.length(); i++) {         //c对a的赋值
            a[i] = c.charAt(i) - 48;
        }
        for (int i = 0; i < d.length(); i++) {         //d对b的赋值
            b[i] = d.charAt(i) - 48;
        }
        for (int i = b.length - 1; i >= 0; i--) {      //模拟乘法，想相乘
            for (int j = a.length - 1; j >= 0; j--) {
                result[i + j] += b[i] * a[j];
            }
        }
        for (int i = result.length - 1; i > 0; i--)     //进位
        {
            int temp = result[i] / 10;
            result[i] = result[i] % 10;
            result[i - 1] += temp;
        }
        System.arraycopy(result, 0, trueRes, 0, result.length - 1);
        for (int i = 0; i < trueRes.length; i++) {       //对字符串赋值
            str.append(trueRes[i]);
        }
        return str.toString();                                      //返回结果
    }
}
