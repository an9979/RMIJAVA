package client;


import shared.IPerson;
import shared.ISayHello;
import shared.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class RmiStub implements IPerson {
    private Socket socket;
    public RmiStub() throws IOException {
        this.socket = new Socket("127.0.0.1", 8888);
    }
    @Override
    public void addUser(Person person) {
        try {
            System.out.println("adduser");
            System.out.println("Person> "+person);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String url = "Person/addUser";
            objectOutputStream.writeObject(url);
            objectOutputStream.flush();
            String confirmRecived =(String) objectInputStream.readObject();
            if (confirmRecived.equals("addUser")){
            ObjectOutputStream personRecive = new ObjectOutputStream(socket.getOutputStream());
                personRecive.writeObject(person);
                personRecive.flush();
                System.out.println("person sent!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(int personNumber) {
    }
    @Override
    public void showUsers() {
        try {
            System.out.println("showUsers");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String url = "Person/showUsers";
            objectOutputStream.writeObject(url);
            objectOutputStream.flush();
            System.out.println("URL SENT!");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String confirmRecived =(String) objectInputStream.readObject();
            int listSize=(int) objectInputStream.readObject();
            if (confirmRecived.equals("showUsers")){
                if (listSize==0){
                    System.out.println("Nothing found");
                }
                else {
                    do {
//                        ObjectInputStream objectInputStreamPerson = new ObjectInputStream(socket.getInputStream());
                        Person recivedPersonal=(Person) objectInputStream.readObject();
                        System.out.println(recivedPersonal.getPersonName());
                        System.out.println(recivedPersonal.getPersonNumber());
                        listSize--;
                    }while(listSize>0);
                }
//                while(!objectInputStream.readUTF().equals("EOF")){
//                }
//                System.out.println("EOF");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

