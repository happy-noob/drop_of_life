package com.sword.shaoguang_deblur.service.impl;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import com.sword.shaoguang_deblur.entity.vo.resp.DeblurImg;
import com.sword.shaoguang_deblur.service.ITShaoGuangImgService;
import com.sword.shaoguang_deblur.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class TShaoGuangImgServiceImpl implements ITShaoGuangImgService {
    @Override
    public DeblurImg base64ToImg(String base64str, String suffix) {
        String outputImgPath = null;
        String imgFileName = System.currentTimeMillis() + suffix;
        System.out.println(imgFileName);
        try {
            FileUtils.writeByteArrayToFile(new File("/predeblur/" + imgFileName), Base64Util.base64_dec(base64str));
            // socket编程
            String serverName = "localhost";
            int port = 8087;
            try {
                System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
                Socket client = new Socket(serverName, port);
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

                OutputStream outToServer = client.getOutputStream();
//                发送
                outToServer.write(imgFileName.getBytes(StandardCharsets.UTF_8));
                System.out.println("RemoteSocketAddress:" + client.getRemoteSocketAddress());

                InputStream inFromServer = client.getInputStream();
                BufferedInputStream bufferIn = new BufferedInputStream(inFromServer);
                byte[] buf = new byte[1024];
                int len;
                len = bufferIn.read(buf);
                outputImgPath = new String(buf, 0, len, StandardCharsets.UTF_8);
                System.out.print(outputImgPath);
                outToServer.write("exit".getBytes(StandardCharsets.UTF_8));
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 返回字符串(文件路径)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DeblurImg(outputImgPath);
    }
}
