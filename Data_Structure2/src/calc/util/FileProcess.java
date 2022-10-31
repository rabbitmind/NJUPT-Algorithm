package calc.util;

import java.io.BufferedWriter;//用于写入文件
import java.io.FileWriter;//用于写入文件
import java.io.IOException; //处理异常

public class FileProcess {

    //写文件函数
    public static void writeFile(String res, Boolean fileFlag, String Filename) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(Filename, fileFlag));
        out.write(res);
        out.close();
    }
}
