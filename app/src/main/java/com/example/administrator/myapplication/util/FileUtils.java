package com.example.administrator.myapplication.util;

import android.content.Context;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by ASUS on 2015/3/18.
 * 创建文件
 */
public class FileUtils {
    public static File createFile(String sFile) throws IOException {
        File file = new File(sFile);
        File filePrt = file.getParentFile();
        if (filePrt != null &&!filePrt.exists()) {
            filePrt.mkdirs();
        }
        file.createNewFile();
        return file;
    }

    /**
     * Created by ASUS on 2015/3/18.
     * 删除文件
     */
    public static boolean deleteFile(String filePath){
        boolean result = false;
        if (StringUtils.isNotEmpty(filePath)){
            File targetFile = new File(filePath);
            if (targetFile.exists()){
                if (targetFile.isDirectory()){
                    File[] targetFiles = targetFile.listFiles();
                    for (File temp:targetFiles){
                        deleteFile(temp.getAbsolutePath());
                    }
                    DebugLog.e("delete:\t"+targetFile.getAbsolutePath());
                    targetFile.delete();
                }else if (targetFile.isFile()){
                    DebugLog.e("delete:\t"+targetFile.getAbsolutePath());
                    targetFile.delete();
                }
                result = true;
            }
        }
        return result;
    }

    public static String readJsonFile(Context context, String fileName) {

        StringBuilder builder = new StringBuilder();
        int id = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
        InputStream inputStream = context.getResources().openRawResource(id);
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                builder.append(content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();


//        try {
//            int length = 0;
//            while (length == 0) {
//                length = inputStream.available();
//            }
//            byte[] bytes = new byte[length];
//            int key = inputStream.read(bytes);
//            content = new String(bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return content;
    }


    /* 判断文件MimeType的method */
    public static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
		/* 取得扩展名 */
        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length())
                .toLowerCase();

		/* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
                || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg") || end.equals("bmp")) {
            type = "image";
        } else if (end.equals("apk")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "application";
        } else if (end.equals("ppt") || end.equals("pptx")) {
			/* ppt */
            type = "application";
        } else if (end.equals("xls") || end.equals("xlsx")) {
			/* excel */
            type = "application";
        } else if (end.equals("doc") || end.equals("docx")) {
			/* word */
            type = "application";
        } else if (end.equals("chm")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "application";
        } else if (end.equals("pdf")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "application";
        } else if (end.equals("txt") || end.equals("log")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "text";
        } else {
            type = "*";
        }
		/* 如果无法直接打开，就跳出软件列表给用户选择 */
        if (end.equals("apk")) {
            type += "/vnd.android.package-archive";
        } else if (end.equals("ppt") || end.equals("pptx")) {
			/* ppt */
            type += "/vnd.ms-powerpoint";
        } else if (end.equals("xls") || end.equals("xlsx")) {
			/* excel */
            type += "/vnd.ms-excel";
        } else if (end.equals("doc") || end.equals("docx")) {
			/* word */
            type += "/msword";
        } else if (end.equals("chm")) {
			/* android.permission.INSTALL_PACKAGES */
            type += "/x-chm";
        } else if (end.equals("pdf")) {
			/* android.permission.INSTALL_PACKAGES */
            type += "/pdf";
        } else if (end.equals("txt") || end.equals("log")) {
            type += "/plain";
        } else {
            type += "/*";
        }
        return type;
    }
}
