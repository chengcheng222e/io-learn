package com.cyblogs.io.learn.biocase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with io-learn
 * Description：
 * User：chenyuan
 * Date：2018/12/6
 * Time：8:42 AM
 */
public class MoreConnectionCanPrintInfoBIOServerCase {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket socket = new ServerSocket(port);
        System.out.println("OnlyOneCanPrintInfoBIOServer has start, listening on port:" + port);
        while (true) {
            Socket clientSocket = socket.accept();
            System.out.println("Connection form" + clientSocket.getRemoteSocketAddress());
            Scanner input = new Scanner(clientSocket.getInputStream());
            while (true) {
                if (input.hasNext()) {
                    String request = input.nextLine();
                    if ("quit".equals(request)) {
                        break;
                    }
                    System.out.println(clientSocket.getRemoteSocketAddress() + " → " + request);
                    String response = "From BIOServer response ： " + request + "\n";
                    clientSocket.getOutputStream().write(response.getBytes());
                }
            }
            clientSocket.close();
        }
    }
}
