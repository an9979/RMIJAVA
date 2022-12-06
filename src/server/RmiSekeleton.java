package server;


import shared.IPerson;
import shared.ISayHello;
import shared.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//import static shared.IPerson.personalPool;

public class RmiSekeleton implements Runnable{
    private Person person=new Person();
    private Socket socket;
//private List<Person> personalList=new ArrayList<Person>();
    public RmiSekeleton(Socket socket ) {
        this.socket = socket;
    }



    @Override
    public void run() {
        try {
//            ServerSocket serverSocket = new ServerSocket(8888);
//            Socket accept = serverSocket.accept();
            while (true) {
                ObjectInputStream readUrl = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                String url = (String) readUrl.readObject();
                String interfaceName = url.split("/")[0];
                String methodName = url.split("/")[1];
                if (interfaceName.equals("Person")) {
                    switch (methodName) {
                        case "addUser":
                            objectOutputStream.writeObject("addUser");
                            objectOutputStream.flush();
                            ObjectInputStream readPerson = new ObjectInputStream(socket.getInputStream());
                            Person personNew = (Person) readPerson.readObject();
                            System.out.println(personNew);
                            System.out.print("personName>");
                            System.out.println(personNew.getPersonName());
                            person.addUser(personNew);
                            for (Person peson: person.personalPool){
                                System.out.println(peson.getPersonName());
                                System.out.println(peson.getPersonNumber());
                            }
                            break;
                        case "showUsers":
                            objectOutputStream.writeObject("showUsers");
                            objectOutputStream.flush();
                            objectOutputStream.writeObject(person.personalPool.size());
                            objectOutputStream.flush();
                            for (Person peson: person.personalPool){
                                System.out.println("person> "+peson);
                                objectOutputStream.writeObject(peson);
                                objectOutputStream.flush();
                            }
                                objectOutputStream.writeObject("EOF");
                                objectOutputStream.flush();
                            break;
                    }

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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

