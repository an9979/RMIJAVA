package server;


import shared.ISayHello;
import shared.SayHelloImpl;

public class Server {
    public static void main(String[] args) {
        ISayHello sayHello = new SayHelloImpl();
        RmiSekeleton rmiSekeleton = new RmiSekeleton(sayHello);
        rmiSekeleton.start();
        System.out.println("server start...");
    }
}