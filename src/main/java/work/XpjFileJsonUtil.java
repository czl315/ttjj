package work;

import java.io.*;

/**
 * @author
 * @date
 */
public class XpjFileJsonUtil {
    /**
     * 新品季-读取文件格式化数据
     */
    public static final StringBuffer sb = new StringBuffer();

    public static void main(String[] args) {
//        String fileName = "D:\\docWork\\JD京东JD京东JD京东\\200409-618华硕爱奇艺活动\\生产\\20200531-爱奇艺-系统异常.txt";
        String fileName = "D:\\docWork\\JD京东JD京东JD京东\\200501-618新品季\\上线\\客诉20200604-\\【附件2】用户积分记录截止2020.06.04 154252-utf8.txt";
        readFileByLines(fileName);
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(isr);
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                if(tempString.contains("{")||tempString.contains("}")){
                    tempString = tempString.substring(6);
//                    System.out.println(tempString);
                    String[] array =  tempString.split(",");
                    for (String str : array) {
//                        System.out.println(str);
                        if(str.contains("content")){
                            str = str.substring(tempString.indexOf("{\"content\":\"")+12);
                            str = str.replace("\"","");
                            System.out.print(str+"  ");
                            System.out.print("\t");
                        }
                        if(str.contains("createTime")){
                            str = str.substring(tempString.indexOf("{\"createTime\":\"")+15);
                            str = str.replace("\"","");
                            System.out.print(str+"  ");
                            System.out.print("\t");
                        }
                        if(str.contains("value")){
                            str = str.substring(tempString.indexOf("{\"value\":\"")+11);
                            str= str.replace("\"}","");
                            System.out.print(str+"  ");
                            System.out.println("\t");
                        }
                    }
                }
                sb.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
