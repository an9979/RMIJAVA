package server;


import shared.IPerson;
import shared.ISayHello;
import shared.Person;
import shared.SayHelloImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("server start...");
        while(true){
        Socket accept = serverSocket.accept();
            new Thread(new RmiSekeleton(accept)).start();
        }
    }
}