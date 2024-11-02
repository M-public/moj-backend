package com.meng.moj.utils;

/**
 * @DESCRIPTION: description
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/9/28 22:06
 **/
import java.io.File;

public class FileRenameExample {
    public static void main(String[] args) {

//        System.out.println(10^6^6);
        // 指定目录
        String directory = "D:\\CRTubeGet Downloaded\\铁根游戏解说\\已剪";
        String directory1 = "D:\\CRTubeGet Downloaded\\小勤团战大赏（高端局抽象整活娱乐解说）\\已剪";
        String directory2 = "D:\\CRTubeGet Downloaded\\美式篮球\\已剪";

        File dir = new File(directory1);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    System.out.println(fileName.substring(0, fileName.lastIndexOf(".")));

//                    if (fileName.indexOf("#")!=-1) {
//                        String newFileName = fileName.substring(0,fileName.indexOf("#"))+".mp4";
//                        String lastName = fileName.substring(fileName.lastIndexOf("."));
//                        File newFile = new File(directory + File.separator + newFileName);
//                        file.renameTo(newFile);
//                        System.out.println("Renamed: " + fileName + " -> " + newFileName);
//                    }
                }
            }
        }
    }
}
