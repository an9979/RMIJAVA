package server;


import shared.IPerson;
import shared.ISayHello;
import shared.Person;
import shared.SayHelloImpl;

public class Server {
    public static void main(String[] args) {
        IPerson person=new Person();
        ISayHello sayHello = new SayHelloImpl();
        RmiSekeleton rmiSekeleton = new RmiSekeleton(person);
        rmiSekeleton.start();
        System.out.println("server start...");
    }
}