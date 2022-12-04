package client;


import shared.ISayHello;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class RmiStub implements ISayHello {

    private Socket socket;

    public RmiStub() throws IOException {
        this.socket = new Socket("127.0.0.1", 8888);
    }

    @Override
    public String test() {
        return null;
    }

    @Override
    public String sayHello(String interFaceName,String methodeName) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            String url = interFaceName+"/"+methodeName;
            System.out.println(url);
            while (true){
                objectOutputStream.writeObject(url);
                objectOutputStream.flush();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String message = (String) objectInputStream.readObject();
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

