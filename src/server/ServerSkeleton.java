package server;


import shared.ISayHello;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RmiSekeleton extends Thread{

    private ISayHello sayHello;

    public RmiSekeleton(ISayHello sayHello) {
        this.sayHello = sayHello;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket accept = serverSocket.accept();
            ObjectInputStream read = new ObjectInputStream(accept.getInputStream());
            while (accept != null){


                String url = (String) read.readObject();
                String interfaceName = url.split("/")[0];
                String methodName = url.split("/")[1];
                if(interfaceName.equals("ISayHello")){
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                    switch (methodName){
                        case "sayHello":
//                            String message = sayHello.sayHello();
//                            objectOutputStream.writeObject(message);
                            objectOutputStream.flush();
                            break;
                        case "test":
                            String messege =sayHello.test();
                            objectOutputStream.writeObject("message");
                            objectOutputStream.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

