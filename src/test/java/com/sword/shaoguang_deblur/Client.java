package com.sword.shaoguang_deblur;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 20000;
        try {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            outToServer.write("3.png".getBytes(StandardCharsets.UTF_8));
            System.out.println("RemoteSocketAddress:" + client.getRemoteSocketAddress());

            InputStream inFromServer = client.getInputStream();
            BufferedInputStream bufferIn = new BufferedInputStream(inFromServer);
            byte[] buf = new byte[1024];
            int len;
            len = bufferIn.read(buf);
            System.out.print(new String(buf, 0, len, StandardCharsets.UTF_8));
            outToServer.write("exit".getBytes(StandardCharsets.UTF_8));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
