package main;

import java.io.IOException;
import java.util.Scanner;           //用于控制台输入:正则表达式,任意地对字符串和基本类型进行分析

public class figure {
    //图变量
    static int[][] edge = new int[100][100];     //定义边数组edge
    static int[] vertex = new int[100];         //定义顶点数组vertex
    static int n, m;                            //定义n顶点数，m边数

    //max变量
    static int max = 0;         //最大值变量
    static int numTempArrMax =1;                       //最短路径存储数组临时变量
    static int[][] tempArrMax = new int[100][100];     //最长路径存储数组临时变量

    //min变量
    static int min = Integer.MAX_VALUE;         //min初始化为int最大值:2147483647
    static int numTempArr =1;                       //最短路径存储数组临时变量
    static int[][] tempArr = new int[100][100];     //定义边数组edge

    //最短路径条数
    static int number =0;

    //实例化Scanner类，对象input,获取按字节输入
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {            //主函数
        System.out.println("欢迎来到本程序");
        System.out.println("=================================================");
        System.out.println("功能简介:");
        System.out.println("本程序可以计算有向图任意两点a,b两点之间最短路径和最长路径及其中间的所有简单路径条数");
        System.out.println("起始点设为1号点，目的点是最后一个点，中间的无所谓顺序");
        System.out.println("请愉快的开始吧！！！");
        System.out.println("=================================================");
        System.out.println("这是测试数据");
        System.out.println("顶点数5，边数8");
        System.out.println(
                "1 2 2 \n" +
                "1 5 10 \n" +
                "2 3 3 \n" +
                "2 5 7 \n" +
                "3 1 4 \n" +
                "3 4 4 \n" +
                "4 5 5 \n" +
                "5 3 3 ");
        System.out.println("=================================================");
        System.out.print("请输入顶点数:");              //提醒输入顶点数
        n = input.nextInt();                            //获取输入n
        System.out.print("请输入边数:");                 //提醒输入边数
        m = input.nextInt();                             //获取输入m
        for (int i = 1; i <= n; i++) {                  //左对角线元素为权值0
            for (int j = 1; j <= m; j++) {
                if (i == j) {
                    edge[i][j] = 0;
                } else {
                    edge[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        System.out.println("请输入点 点 权值，以空格隔开，换不换行无所谓");
        for (int i = 1; i <= m; i++) {                  //将输入权值对应填入edge数组
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            edge[a][b] = c;
        }

        vertex[1] = 1;

        System.out.println();                           //获取用户输入之后换行

        dfs(1, 0);                          //运行最短路径函数
        System.out.println("简单路径条数=="+number);
        System.out.println("=================================================");
        System.out.print("最短路径:");
        for (int i=1;i<=n;i++){
            if(tempArr[numTempArr-1][i]==1){
                System.out.print(i+"点"+"→");
            }
        }
        System.out.println("5点");
        System.out.print("最短路径长度==");
        System.out.println(min);


        dfsMax(1, 0);           //运行最长路径函数
        System.out.print("最长路径:");
        for (int i=1;i<=n;i++){
            if(tempArrMax[numTempArrMax-1][i]==1){
                System.out.print(i+"点"+"→");
            }
        }
        System.out.println("5点");
        System.out.print("最长路径长度==");
        System.out.println(max);

        System.out.println("=================================================");
        System.out.println("按q退出");
        if(System.in.read() == 'q'){
            System.exit(0);
        }
    }


    public static void dfsMax(int cur, int dis) {
        //判断是否达到最后一个结点，更新最小值，返回
        if (cur == n) {
            if (dis>max) {
                max = dis;
                if (n - 1 >= 0) System.arraycopy(vertex, 1, tempArrMax[numTempArrMax], 1, n - 1);
                numTempArrMax++;
            }
            return;
        }

        //递归
        for (int i = 1; i <= n; i++) {
            if (edge[cur][i] != Integer.MAX_VALUE && vertex[i] == 0) {
                vertex[i] = 1;
                dfsMax(i, dis + edge[cur][i]);
                //回溯
                vertex[i] = 0;
            }
        }
    }





    public static void dfs(int cur, int dis) {

        //如果当前路径小于之前找到的最大值，可直接返回
        if (dis < max) {
            return;
        }
        //判断是否达到最后一个结点，更新最小值，返回
        if (cur == n) {
            number++;
            if (dis < min) {
                min = dis;
                if (n - 1 >= 0) System.arraycopy(vertex, 1, tempArr[numTempArr], 1, n - 1);
                numTempArr++;
            }
            return;
        }

        //当前点到其他各点之间可连通但是还未添加进来时，遍历执行
        for (int i = 1; i <= n; i++) {
            if (edge[cur][i] != Integer.MAX_VALUE && vertex[i] == 0) {
                vertex[i] = 1;
                dfs(i, dis + edge[cur][i]);

                //回溯
                vertex[i] = 0;
            }
        }
    }
}