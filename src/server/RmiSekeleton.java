package server;


import shared.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//import static shared.IPerson.personalPool;

public class RmiSekeleton implements Runnable{
    private Person person;
    private final Socket socket;
    public RmiSekeleton(Socket socket ) {
        this.socket = socket;
    }



    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream readUrl = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                String url = (String) readUrl.readObject();
                String interfaceName = url.split("/")[0];
                String methodName = url.split("/")[1];
                int rowNumber;
                if (interfaceName.equals("Person")) {
                    switch (methodName) {
                        case "addUser":
                            objectOutputStream.writeObject("addUser");
                            objectOutputStream.flush();
                            ObjectInputStream readPerson = new ObjectInputStream(socket.getInputStream());
                            Person personNew = (Person) readPerson.readObject();
                            person.addUser(personNew);
                            break;
                        case "showUsers":
                            objectOutputStream.writeObject("showUsers");
                            objectOutputStream.flush();
                            objectOutputStream.writeObject(person.personalPool.size());
                            objectOutputStream.flush();
                            for (Person peson: person.personalPool){
                                objectOutputStream.writeObject(peson);
                                objectOutputStream.flush();
                            }
                            break;
                        case "showUser":
                            objectOutputStream.writeObject("showUser");
                            objectOutputStream.flush();
                            rowNumber=(int) readUrl.readObject();
                            objectOutputStream.writeObject(person.personalPool.get(rowNumber-1));
                            objectOutputStream.flush();
                            break;
                        case "editUser":
                            objectOutputStream.writeObject("editUser");
                            objectOutputStream.flush();
                            String editedInfo=(String) readUrl.readObject();
                            int rowNumberEdited=Integer.parseInt(editedInfo.split("/")[0]);
                            String changingParam=editedInfo.split("/")[1];
                            String userEditModeChangedParam=editedInfo.split("/")[2];
                            person.editUser(rowNumberEdited-1, changingParam,userEditModeChangedParam);
                            break;
                    }

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

