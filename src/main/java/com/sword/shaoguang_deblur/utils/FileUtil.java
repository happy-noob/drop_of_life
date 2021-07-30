package com.sword.shaoguang_deblur.utils;

import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class FileUtil {

    /**
     * 验证文件格式是否正确
     * @param fileSuffix
     * @return
     */
    public static boolean fileTypeRight(String fileSuffix) {
        List<String> fileType=new ArrayList<>();
        Collections.addAll(fileType, "jpg", "jpeg", "png", "bmp");
        if (!fileType.contains(fileSuffix)) {
            return false;
        }
        return true;
    }

    /**
     * 上传文件
     * @param file
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean uploadFile(byte[] file, String filePath, String fileName) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file);
            out.flush();
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;

    }

}
