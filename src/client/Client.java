package client;


import shared.ISayHello;
import java.util.Scanner;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        ISayHello RMI = new RmiStub();
        Scanner scanner=new Scanner(System.in);
        System.out.println("***************"+"\n"+"*****START*****"+"\n"+"***************");
        System.out.println("Select action");
        System.out.println("1-add user  2-remove user");
        System.out.println("Input>");
        String scan= scanner.nextLine();
        while (!scan.equals("exit")){
            switch (scan){
                case "1":
                    System.out.println("->Input userNumber");
                    String addUserNumber= scanner.nextLine();
                    System.out.println("->Input userName");
                    String adduserName=scanner.nextLine();
                    String message = RMI.sayHello("","");
                    System.out.println(message);
                    break;
                case "2":
//                String message = sayHello.sayHello();
//                System.out.println(message);
                    break;
            }
        }
//        sayHello.sayHello()
    }
}
