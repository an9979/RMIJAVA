package client;
import shared.IPerson;
import shared.Person;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RmiStub implements IPerson {
    private final Socket socket;
    public RmiStub() throws IOException {
        this.socket = new Socket("127.0.0.1", 8888);
    }
    @Override
    public void addUser(Person person) {
        try {
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
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(int rowNumber) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String url = "Person/deleteUser";
            objectOutputStream.writeObject(url);
            objectOutputStream.flush();
            String confirmRecived =(String) objectInputStream.readObject();
            if (confirmRecived.equals("editUser")){
                objectOutputStream.writeObject(rowNumber);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void showUsers() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String url = "Person/showUsers";
            objectOutputStream.writeObject(url);
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String confirmRecived =(String) objectInputStream.readObject();
            int listSize=(int) objectInputStream.readObject();
            if (confirmRecived.equals("showUsers")){
                if (listSize==0){
                    System.out.println("Nothing found");
                }
                else {
                    int iterator=1;
                    do {
//                        ObjectInputStream objectInputStreamPerson = new ObjectInputStream(socket.getInputStream());
                        Person recivedPersonal=(Person) objectInputStream.readObject();
                        System.out.print(iterator+"> ");
                        System.out.println("Name: "+recivedPersonal.getPersonName());
                        System.out.print(" > ");
                        System.out.println("ID: "+recivedPersonal.getPersonNumber());
                        System.out.println("----------");
                        iterator++;
                        listSize--;
                    }while(listSize>0);
                }
//                while(!objectInputStream.readUTF().equals("EOF")){
//                }
//                System.out.println("EOF");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showUser(int rowNumber) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String url = "Person/showUser";
            objectOutputStream.writeObject(url);
            objectOutputStream.flush();
            String confirmRecived =(String) objectInputStream.readObject();
            if (confirmRecived.equals("showUser")){
                objectOutputStream.writeObject(rowNumber);
            objectOutputStream.flush();
                Person receivedPersonal=(Person) objectInputStream.readObject();
                System.out.println("----------Selected User----------");
                System.out.println("Name: "+receivedPersonal.getPersonName());
                System.out.println("ID: "+receivedPersonal.getPersonNumber());
                System.out.println("----------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(int rowNumber, String changingParam, String userEditModeChangedParam) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String url = "Person/editUser";
            objectOutputStream.writeObject(url);
            objectOutputStream.flush();
            String confirmRecived =(String) objectInputStream.readObject();
            if (confirmRecived.equals("editUser")){
                objectOutputStream.writeObject(rowNumber+"/"+changingParam+"/"+userEditModeChangedParam);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

