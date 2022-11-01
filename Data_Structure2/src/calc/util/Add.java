package calc.util;

public class Add {
    //加法函数
    public static String add(String a, String b) {
        char[] c = a.toCharArray();      //c是a的char数组
        char[] d = b.toCharArray();      //d是b的char数组
        int[] e = new int[100];          //e是c的int数组
        int[] f = new int[100];          //f是d的int数组
        int[] res = new int[100];        //res运算结果int数组形式
        boolean flag = false;            //用于判断res数组是否到了非0处
        StringBuilder str = new StringBuilder();                 //返回的字符串

        for (int i = 0; i < c.length; i++) {        //c对e的赋值
            e[100 - c.length + i] = (int) c[i] - 48;
        }
        for (int i = 0; i < d.length; i++) {        //d对f的赋值
            f[100 - d.length + i] = (int) d[i] - 48;
        }
        for (int i = 0; i < 100; i++) {             //计算res,大于10,低位-10，高位加1
            res[i] = e[i] + f[i];

            if (res[i] >=10) {
                res[i] -= 10;
                res[i - 1] += 1;
                for(int j=1;j<i;j++){
                    if(res[i - j]==10){
                        res[i-j]-=10;
                        res[i-j-1]+=1;
                        break;
                    }
                }

            }

        }
        for (int i = 0; i < 100; i++) {         //对返回字符串赋值
            if (res[i] != 0) flag = true;
            if (flag) {
                str.append(res[i]);
            }
        }
        return str.toString();                             //返回结果
    }

    public static void main(String[] args) {
        String res= add("986452536767867867897895345782345678","6572156212456123467234671235675631267123");
        System.out.println(res);
    }
}
