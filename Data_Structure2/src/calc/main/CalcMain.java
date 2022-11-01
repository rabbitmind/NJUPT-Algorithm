//主程序
package calc.main;

import calc.util.*;

import java.io.IOException;             //处理异常
import java.math.BigDecimal;            //大整数

import javax.swing.*;                       //导入java.swing包
import java.awt.*;                          //JFrame要用到
import java.awt.event.*;                    //监听

//建立类CalcMain,JFrame
public class CalcMain extends JFrame implements ActionListener {

    //北部控件
    private final JPanel JP_North = new JPanel();                 //北部控件实例化
    private final JTextArea Input_Text = new JTextArea(2, 1);       //添加输入框
    private final JButton C_Btn = new JButton("AC");         //添加清空键

    //南部控件
    private final JPanel JP_South = new JPanel();                 //北部控件实例化
    private final JTextArea outputText = new JTextArea(5, 1);    //输出框

    //中间控件
    private final JPanel JP_Center = new JPanel();                //中间区域；类实例化

    //字符
    public static final String regexOpe = "[\\+\\-X/%^ ]";       //运算符

    //运算器构建主函数
    public CalcMain() {
        init();                         //初始化设置
        AddNorthComponent();            //北部函数
        AddCenterComponent();           //中间函数
        AddSouthComponent();            //南部函数
    }

    //初始化设置
    public void init() {
        int Frame_width = 400;                   //程序宽
        int Frame_height = 400;                  //程序高
        int Screen_Width = Toolkit.getDefaultToolkit().getScreenSize().width;       //获取屏幕宽度
        int Screen_Height = Toolkit.getDefaultToolkit().getScreenSize().height;      //获取屏幕高度
        int Frame_X = (Screen_Width - Frame_width) / 2;                 //设置中心点X坐标
        int Frame_Y = (Screen_Height - Frame_height) / 2;               //设置中心点Y坐标
        String TitleName = "大整数运算器-------By B20031110";                //设置运算器标题

        this.setTitle(TitleName);                               //设置标题
        this.setSize(Frame_width, Frame_height);          //设置宽度，高度
        this.setLayout(new BorderLayout());                           //BorderLayout边框布局
        this.setResizable(false);                                     //不可拉伸
        this.setLocation(Frame_X, Frame_Y);               //设置中心点位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          //退出窗口就退出程序
    }

    //北部控件设置函数
    public void AddNorthComponent() {
        this.Input_Text.setPreferredSize(new Dimension(300, 30));      //设置输入框大小
        this.Input_Text.setSelectedTextColor(Color.BLUE);                           //设置输入框选中文字颜色
        this.Input_Text.setForeground(new Color(173, 186, 199));           //设置输入框字体颜色
        this.Input_Text.setBackground(new Color(34, 39, 46));                 //设置输入框背景颜色
        this.Input_Text.setBorder(BorderFactory.createLineBorder(Color.white, 0));//设置文本框边框为0
        this.Input_Text.setLineWrap(true);                                          //自动换行
        this.Input_Text.setWrapStyleWord(true);
        this.Input_Text.setLineWrap(true);                                          //自动换行
        this.Input_Text.setFont(new Font("粗体", Font.BOLD, 25));         //设置输入框字体大小样式

        this.C_Btn.setPreferredSize(new Dimension(60, 60));      //设置输入框大小
        this.C_Btn.setBackground(new Color(173, 108, 121));                //设置清空键背景rgb
        this.C_Btn.setForeground(new Color(28, 33, 40));                      //设置清空健字体rgb
        this.C_Btn.setBorder(BorderFactory.createLineBorder(Color.white, 0));//设置文本框边框为0
        C_Btn.setFont(new Font("粗体", Font.BOLD, 16));   //清空按钮字体粗体，字号16
        C_Btn.addActionListener((ActionEvent e) -> {
            Input_Text.setText("");                             //清空输出Input_Text
            outputText.setText("");                             //清空输出Output_Text
        });

        //添加到面板
        JP_North.setPreferredSize(new Dimension(400, 80));      //北部大小
        JP_North.setBackground(new Color(34, 39, 46));                   //设置北部背景颜色
        JP_North.setBorder(BorderFactory.createLineBorder(Color.white, 0));    //设置北部边框为0
        JP_North.add(Input_Text);                             //北部区域添加输入框
        JP_North.add(C_Btn);                                  //北部区域添加清空按钮
        this.add(JP_North, BorderLayout.NORTH);               //将北部添加到BorderLayout布局
    }

    //南部控件设置函数
    public void AddSouthComponent() {
        this.outputText.setPreferredSize(new Dimension(360, 170));      //设置输入框大小
        this.outputText.setSelectedTextColor(Color.BLUE);                           //设置输入框选中文字颜色
        this.outputText.setForeground(new Color(173, 186, 199));           //设置输入框字体颜色
        this.outputText.setBackground(new Color(34, 39, 46));                 //设置输入框背景颜色
        this.outputText.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));//设置输出框边框为0
        this.outputText.setFont(new Font("粗体", Font.BOLD, 25));          //设置输出框字体大小样式
        this.outputText.setEditable(false);                                         //禁止用户手动输入
        this.outputText.setLineWrap(true);                                          //自动换行
        this.outputText.setWrapStyleWord(true);                                     //激活断行续写

        JP_South.setPreferredSize(new Dimension(400, 170));      //设置输入框大小
        this.JP_South.setBackground(new Color(34, 39, 46));                   //设置南部控件背景颜色
        this.JP_South.setBorder(BorderFactory.createLineBorder(Color.white, 0));//设置南部边框为0
        JP_South.add(outputText);                                                   //南部区域添加清空按钮，流式布局
        this.add(JP_South, BorderLayout.SOUTH);                                     //将南部添加到BorderLayout
    }

    //中间控件设置函数
    public void AddCenterComponent() {
        String btn_text = "+-X/%^=D";                           //按钮文字
        this.JP_Center.setLayout(new GridLayout(2, 4, 0, 0));     //设置网格
        for (int i = 0; i < 8; i++) {                                       //遍历设置按钮字体
            JButton btn = new JButton();                                     //实例化按钮
            String temp = btn_text.substring(i, i + 1);                      //每次循环的临时变量
            btn.setText(temp);                                               //设置字体内容
            btn.setBackground(new Color(51, 51, 51));               //背景rgb灰色
            btn.setForeground(new Color(173, 186, 199));            //字体rgb
            if (temp.matches(regexOpe)) {                              //正则匹配字符
                btn.setFont(new Font("粗体", Font.BOLD, 16));      //运算符加粗，字号16
                btn.setBackground(new Color(238, 178, 17));         //设置背景rgb黄色
                btn.setForeground(new Color(28, 33, 40));        //设置字体rgb
            }
            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));  //设置按钮边框为1
            btn.addActionListener(this);              //添加监听器
            JP_Center.add(btn);                         //将组件添加中间区域
        }
        this.add(JP_Center, BorderLayout.CENTER);       //将中间添加到BorderLayout
    }

    private String FirstInput = "";                   //设置第一个输入值
    private String SecondInput = "1";                    //设置第二个输入值

    private String Operate = "=";                      //设置操作符

    //按键监听函数
    @Override
    public void actionPerformed(ActionEvent e) {
        String clickStr = e.getActionCommand();          //获取点击
        String lastFile = "./historyLast.txt";              //用户最后输入的表达式存储文件位置

        //输入框
        if (clickStr.matches(regexOpe)) {
            if (!Operate.matches(regexOpe)) {
                FirstInput = Input_Text.getText();                   //运算的第一个值
                try {                                                      //写文件
                    FileProcess.writeFile(FirstInput, true, lastFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            Operate = clickStr;                             //更新操作符
            try {                                                      //写文件
                if(Operate.equals("X")){
                    FileProcess.writeFile("*", true, lastFile);
                } else {
                    FileProcess.writeFile(Operate, true, lastFile);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Input_Text.setText("");                 //清空
        } else if (clickStr.matches("D")) {         //删除健
            Input_Text.setText(Input_Text.getText().substring(0, Input_Text.getText().length() - 1));
        } else if (clickStr.equals("=")) {            //等于功能
            if (Operate == "=") {
                FirstInput = Input_Text.getText();
            }
            SecondInput = Input_Text.getText();
            try {                                                      //写文件
                FileProcess.writeFile(SecondInput, true, lastFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Input_Text.setText("");                     //清空输入框
            String strA = FirstInput;                          //第一个输入字符类型
            String strB = SecondInput;           //第二个输入字符类型
            String strResult = null;            //字符类型

            BigDecimal a = new BigDecimal(FirstInput);      //大整数类型第一个值
            BigDecimal b = new BigDecimal(SecondInput);      //大整数类型第二个值

            switch (Operate) {                                               //运算+-*/%^=
                case "+":
                    strResult = Add.add(strA, strB);
                    break;
                case "-":
                    strResult = Sub.sub(strA, strB);
                    break;
                case "X":
                    strResult = Mul.mul(strA, strB);
                    break;
                case "/":
                    strResult = "商：" + a.divideAndRemainder(b)[0] + "----" + "余数" + a.divideAndRemainder(b)[1] + "    ";
                    break;
                case "%":
                    strResult = a.divideAndRemainder(b)[1] + "";
                    break;
                case "^":
                    strResult = Pow.pow(strA, strB);
                    break;
                case "=":
                    strResult = FirstInput;
                    break;
            }
            this.Input_Text.setText(strResult);              //将result转换成字符并输出结果到输入框
            this.outputText.setText(strResult);       //将result转换成字符并输出结果到输出框
            try {                                                      //写文件
                FileProcess.writeFile("==" + strResult + "\n", true, lastFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Operate = "=";                         //操作符置==
        }
        //输出框
        if (clickStr == "=") {
            outputText.setText(Input_Text.getText());
        }
    }

    //主函数
    public static void main(String[] args) {
        CalcMain calcMain = new CalcMain();         //实例化类CalcMain,建立对象calcMain
        calcMain.setVisible(true);                  //运算器可视化
    }
}

