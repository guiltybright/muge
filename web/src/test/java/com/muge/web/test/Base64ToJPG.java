package com.muge.web.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * create by clive.xu on 2018/12/4
 * description:
 */
public class Base64ToJPG {
    public static void main(String[] args) {
        String base64 = "data:image/jpeg;base64,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";
        String path = "C:\\Users\\gravity\\Desktop\\project\\实名认证接口增加摄像头\\1.jpg";
        generateImage(base64, path);
    }

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        String prefix = "data:image/jpeg;base64,";
        if (imgStr.startsWith(prefix)) {
            imgStr = imgStr.substring(prefix.length());
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream outputStream = new FileOutputStream(path);
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
