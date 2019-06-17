package com.tahoecn.xkc.common.utils;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class UpLoadUtils {




    //链接url下载图片到服务器 返回数据库地址
    public static String downloadPicture(String urlList,String activityId,String webPath,String path) {
        URL url = null;
        //截图图片名称
        String name=urlList.substring(urlList.lastIndexOf("/")+1);
        System.out.println(name);
        String img=path+activityId+"/"+name;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            File file = new File(webPath+activityId+"/"+name);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(webPath+activityId);
        System.out.println(img);
        return img;
    }

}
