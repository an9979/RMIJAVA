package client;


import org.w3c.dom.ls.LSOutput;
import shared.IPerson;
import shared.ISayHello;
import shared.Person;

import java.util.Scanner;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
            System.out.println("***************"+"\n"+"*****START*****"+"\n"+"***************");
        Scanner scanner=new Scanner(System.in);
        String scan;
        IPerson RMI = new RmiStub();
        do {
            System.out.println("Select action");
            System.out.println("1-add user  2-remove user   3- write exit to shut down");
            System.out.print("Input>");
            scan= scanner.next();
            switch (scan){
                case "1":
                    System.out.print("Input userNumber> ");
                    int addUserNumber= scanner.nextInt();
                    System.out.print("Input userName> ");
                    String adduserName=scanner.next();
                    Person person=new Person(adduserName,addUserNumber);
                    RMI.addUser(person);
                    System.out.println("-----//END OF OPERATION//--------");
                    break;
                case "2":
                    RMI.showUsers();
            }
        }
        while (!scan.equalsIgnoreCase("exit"));
        }
//        sayHello.sayHello()
    }

