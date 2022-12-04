package client;


import shared.IPerson;
import shared.ISayHello;
import shared.Person;

import java.util.Scanner;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        IPerson RMI = new RmiStub();
        Scanner scanner=new Scanner(System.in);
        System.out.println("***************"+"\n"+"*****START*****"+"\n"+"***************");
        System.out.println("Select action");
        System.out.println("1-add user  2-remove user");
        System.out.println("Input>");
        String scan= scanner.nextLine();
//        while (!scan.equals("exit")){
            switch (scan){
                case "1":
                    System.out.println("Input userNumber> ");
                    int addUserNumber= scanner.nextInt();
                    System.out.print("Input userName> ");
                    String adduserName=scanner.next();
//                    System.out.println("creating");
                    Person person=new Person(adduserName,addUserNumber);
//                    System.out.println(person);
                    RMI.addUser(person);
//                    System.out.println(message);
                    break;

//            }
        }
//        sayHello.sayHello()
    }
}
