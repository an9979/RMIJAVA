package server;


import shared.IPerson;
import shared.ISayHello;
import shared.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static shared.IPerson.personalPool;

public class RmiSekeleton extends Thread{

    private IPerson person;

    public RmiSekeleton(IPerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket accept = serverSocket.accept();
            while (accept != null){
                ObjectInputStream readUrl = new ObjectInputStream(accept.getInputStream());
                String url = (String) readUrl.readObject();
                String interfaceName = url.split("/")[0];
                String methodName = url.split("/")[1];
                if (interfaceName.equals("Person")){
                    switch (methodName) {
                        case "addUser":
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                            objectOutputStream.writeObject("addUser");
                            objectOutputStream.flush();

                            ObjectInputStream readPerson = new ObjectInputStream(accept.getInputStream());
                            Person personNew = (Person) readPerson.readObject();
                            System.out.println(personNew);
                            person.addUser(personNew);
                            if (personalPool.isEmpty()){
                                System.out.println("is Empty");
                            }
                            break;
                    }

                }

//                if(interfaceName.equals("ISayHello")){
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
//                    switch (methodName){
//                        case "sayHello":
//                            Person p1=new Person();
//                            person.addUser(p1);
////                            String message = sayHello.sayHello();
////                            objectOutputStream.writeObject(message);
//                            objectOutputStream.flush();
//                            break;
//                        case "test":
////                            String messege =sayHello.test();
//                            objectOutputStream.writeObject("message");
//                            objectOutputStream.flush();
//                    }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

