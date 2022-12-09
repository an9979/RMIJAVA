package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("server start...");
            while(true){
            Socket accept = serverSocket.accept();
                new Thread(new RmiSekeleton(accept)).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}