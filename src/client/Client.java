package client;


import org.w3c.dom.ls.LSOutput;
import shared.IPerson;
import shared.ISayHello;
import shared.Person;

import java.util.Scanner;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("***************"+"\n"+"*****START*****"+"\n"+"***************"+"\n----->/////You can Write Exit to close to program when ever you want/////<-----");
        Scanner scanner=new Scanner(System.in);
        String scan;
        IPerson RMI = new RmiStub();
        outerloop:
        {
            do {
                System.out.println("Select action");
                System.out.println("1-add user  2-show users   3- write exit to shut down");
                System.out.print("Input>");
                scan = scanner.next();
                switch (scan) {
                    case "1":
                        System.out.print("Input userNumber> ");
                        int addUserNumber = scanner.nextInt();
                        System.out.print("Input userName> ");
                        String adduserName = scanner.next();
                        Person person = new Person(adduserName, addUserNumber);
                        RMI.addUser(person);
                        System.out.println("-----//END OF OPERATION//--------");
                        break;
                    case "2":
                        RMI.showUsers();
                        String optionUserMode;
                        innerloop:
                            {
                                do {
                        System.out.println("Select Action");
                        System.out.println("1-Edit User     2-Delete User   3-Exit user select mode");
                                    optionUserMode = scanner.next();
                                            int rowNumber;
                                    switch (optionUserMode) {
                                        case "1":
                                            System.out.println("edit mode enabled");
                                            System.out.print("Enter the Row of User>");
                                            rowNumber=scanner.nextInt();
                                            RMI.showUser(rowNumber);
                                            String userEditMode;
                                            editModeLoop:
                                            {
                                                do {
                                                    System.out.println("what do you want to change?   1-Name  2-ID  3-Abort");
                                                    userEditMode=scanner.next();
                                                    switch (userEditMode){
                                                        case "1":
                                                            System.out.print("Input userName> ");
                                                            String userEditModeNameChangeParam=scanner.next();
                                                            RMI.editUser(rowNumber,"nameChange",userEditModeNameChangeParam);
                                                            RMI.showUser(rowNumber);
                                                            break editModeLoop;
                                                        case "2":
                                                            System.out.print("Input userNumber> ");
                                                            String userEditModeNumberChangeParam=scanner.next();
                                                            RMI.editUser(rowNumber,"nameChange",userEditModeNumberChangeParam);
                                                            RMI.showUser(rowNumber);
                                                            break editModeLoop;
                                                        case "3":
                                                            break editModeLoop;
                                                        default:
                                                            System.out.println("Not Valid Input");
                                                            break ;
                                                    }
                                                } while (!userEditMode.equalsIgnoreCase("exit"));
                                                if (optionUserMode.equalsIgnoreCase("Exit")) {
                                                    break outerloop;
                                                }
                                            }
                                            break;
                                        case "2":
                                            System.out.println("delete mode enabled \n You can exit the delete mode by typing abort");
                                            deleteModeLoop:
                                            {
                                                System.out.print("Enter the Row of User you want to delete>");
                                                rowNumber=scanner.nextInt();
                                                String deleteModeRowNumber=scanner.next();
                                                RMI.showUser(Integer.parseInt(deleteModeRowNumber));
                                                System.out.println("Are You Sure? Y/N");
                                                String deleteConfirmation=scanner.next();
                                                switch (deleteConfirmation.toLowerCase()){
                                                    case "y":
                                                            RMI.deleteUser(rowNumber);

                                                        break deleteModeLoop;
                                                    case "n":
                                                        System.out.println("Delete Canceled! \n Exiting Delete Mode");
                                                        break deleteModeLoop;
                                                    case "abort":
                                                        break deleteModeLoop;
                                                }
                                                if (optionUserMode.equalsIgnoreCase("Exit")) {
                                                    break outerloop;
                                                }
                                            }
                                            break;
                                        case "3":
                                            System.out.println("exit user select mode");
                                            break innerloop;
                                    }
                                } while (!optionUserMode.equalsIgnoreCase("Exit"));
                        }
                        if (optionUserMode.equalsIgnoreCase("Exit")) {
                            break outerloop;
                        }
                }
            }
            while (!scan.equalsIgnoreCase("exit"));
        }
        System.out.println("Thanks for Using RMI implementation with socket programming  ");
        System.out.println("Developed By: Alireza Nasoodi  ");
    }
    }

