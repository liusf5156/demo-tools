package cnn.liu.demo.creatCsv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liusf on 2018/1/16.
 */
public class Demo {


    public static void main(String[] ste) {

        String path = "C:\\Users\\liusf\\Desktop\\idea git\\xx.csv";

        File file = new File(path);

        List<Dog> dogList = new ArrayList<Dog>();
        dogList.add(new Dog("小花", 10));
        dogList.add(new Dog("小白", 17));
        dogList.add(new Dog("小黑", 6));
        dogList.add(new Dog("小黄", 10));
        dogList.add(new Dog("小清", 13));


        List<String> dataList = cov(dogList);

        System.out.println(exportCsv(file, dataList));


    }


    public static List<String> cov(List<Dog> dogList) {
        List<String> dataList = new ArrayList<String>();

        dataList.add("姓名，年龄");
        for (Dog dd : dogList) {
            dataList.add(dd.name + "," + dd.age);
        }

        return dataList;
    }


    public static boolean exportCsv(File file, List<String> dataList) {
        boolean isSucess = false;

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {

                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }


}

class Dog {
    String name;
    int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

/*
说明：生成的.CSV文件中文乱码  解决办法

  将utf-8 编码的文件用 notepad++ 改成 ANSI编码  再用Excel 打开*/
