package com.food_company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.food_company.task_4.HelloApplication;
import javafx.application.Application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int cashierNumber;//Cashier number for calculate the income
    public static int countOfBurgers;//Number of required burgers for a customer
    public static String name1;//Name of the customer
    public static int servedCashier;//Queue of customer at cashier who buy burgers
    public static void main(String[] args){
        FoodQueue objFoodQueue=new FoodQueue();
        Customer objCustomer=new Customer();

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "*******************************************************************\n"+
                            "*                    FOODIES FAVE FOOD CENTER                     *\n"+
                            "*******************************************************************\n"+
                            "*                              MENU                               *\n"+
                            "*******************************************************************\n"+
                            "*      100 or VFQ:  View all Queues.                              *\n" +
                            "*      101 or VEQ:  View all Empty Queues.                        *\n" +
                            "*      102 or ACQ:  Add customer to a Queue.                      *\n" +
                            "*      103 or RCQ:  Remove a customer from a Queue.               *\n" +
                            "*      104 or PCQ:  Remove a served customer.                     *\n" +
                            "*      105 or VCS:  View Customers Sorted in alphabetical order.  *\n" +
                            "*      106 or SPD:  Store Program Data into file.                 *\n" +
                            "*      107 or LPD:  Load Program Data from file.                  *\n" +
                            "*      108 or STK:  View Remaining burgers Stock.                 *\n" +
                            "*      109 or AFS:  Add burgers to Stock.                         *\n" +
                            "*      110 or IFQ:  Print the Income of each Queue.               *\n" +
                            "*      112 or GUI:  GUI View                                      *\n" +
                            "*      999 or EXT:  Exit the Program.                             *\n"+
                            "*******************************************************************");
            try {
                System.out.println("\nEnter the option : ");
                String option = input.next();
                String option1 = option.toUpperCase();
                if (option1 instanceof String) {   //Check whether the option1 is sting or not
                    switch (option1) {
                        case "100","VFQ":
                            System.out.println(
                                    "*****************\n" +
                                            "*    Cashiers   *\n" +
                                            "*****************\n");
                            objFoodQueue.CustomerView100();          // calling addCustomer method
                            break;
                        case "101", "VEQ":
                            System.out.println(
                                    "*****************\n" +
                                            "*    Cashiers   *\n" +
                                            "*****************\n");
                            objFoodQueue.CustomerView101();   // calling removeServedCustomer method
                            break;
                        case "102", "ACQ":
                            System.out.println("****    ADD CUSTOMERS   ****");
                            try {
                                System.out.println("Enter the  First Name of Customer : ");
                                String nameF = input.next();
                                System.out.println("Enter the  Second Name of Customer : ");
                                String nameS = input.next();
                                String nameFull=nameF+" "+nameS;
                                name1 = nameFull.toUpperCase();
                                if (name1 instanceof String) {  //To check whether the name1 is string or not
                                    if (FoodQueue.count != 10 || FoodQueue.count==10) {
                                        objFoodQueue.addCustomersToArray(name1); //calling addCustomer method to add customer's name to an array
                                        break;
                                    }
                                }else {
                                    System.out.println("Enter a valid name!");
                                }
                            } catch (NullPointerException | IllegalArgumentException e) {
                                System.out.println("Invalid input,Try again");
                            }
                            System.out.println("\n");
                            break;
                        case "103", "RCQ":
                            try {
                                System.out.println("****REMOVE CUSTOMERS****");
                                System.out.println("Enter the Number of Queue : ");
                                int QUE = input.nextInt();
                                System.out.println("Enter the Number of Customer : ");
                                int CUSTOMER = input.nextInt();
                                if(FoodQueue.count>0){      // to check whether there are customers to be removed
                                    try {
                                        if (QUE == 1) {
                                            objFoodQueue.removeCustomerQueue1(CUSTOMER);
                                        }
                                        if (QUE == 2) {
                                            objFoodQueue.removeCustomerQueue2(CUSTOMER);
                                        }
                                        if (QUE == 3) {
                                            objFoodQueue.removeCustomerQueue3(CUSTOMER);
                                        }
                                    }catch (InputMismatchException e) {
                                        System.out.println("Enter valid Queue Number!");
                                    }
                                }else{
                                    System.out.println("All Three Queues are Empty!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input");
                            }
                            break;
                        case "104", "PCQ":
                            try {
                                if (Customer.burgers >=10) {
                                    System.out.println("****REMOVE A SERVED CUSTOMER****");
                                    System.out.println("Enter the Queue Number of Served Cashier : ");
                                    servedCashier = input.nextInt();
                                    System.out.println("Enter the No. of Burgers Required : ");
                                    countOfBurgers = input.nextInt();
                                    if (FoodQueue.count > 0) {
                                        if(Customer.burgers>10) {
                                            objCustomer.burgerVariation(servedCashier, countOfBurgers);     //burgerVariation method was called to change the burger count at store when customer is served
                                            objFoodQueue.removeServedCustomerQueue(servedCashier);//removeCustomerQueue1 was cas called to remove the served customer of the queue
                                        }else {
                                            System.out.println("Not Sufficient Burgers at Store! ");
                                        }
                                    }else {
                                        System.out.println("All Three Queues are Empty!");
                                    }
                                } else if (Customer.burgers == 10) {
                                    System.out.println("Burger count of store is 10. Add burgers to the store!");
                                }

                            }catch (InputMismatchException e) {
                                System.out.println("Invalid Input");
                            }
                            break;
                        case "105", "VCS":
                            System.out.println("**** Sorted Names of Customers ****");
                            objFoodQueue.alphabeticOrder();
                            System.out.println();
                            break;
                        case "106", "SPD":
                            objFoodQueue.createFile();    //to create a file
                            break;
                        case "107", "LPD":
                            objFoodQueue.readFile(); // to read the created file
                            break;
                        case "108", "STK":
                            objCustomer.remainingBurgers(); //to view the status of burgers this method was called
                            System.out.println("\n");
                            break;
                        case "109", "AFS":
                            objCustomer.addBurgers();     // to add burgers as needed this method was called
                            System.out.println("\n");
                            break;
                        case "110","IFQ":
                            if(FoodQueue.count<=10){
                                System.out.println("**** INCOME OF CASHIER ****");
                                System.out.println("Enter the Number of Cashier : ");
                                cashierNumber=input.nextInt();
                                objCustomer.incomeOfQueue(cashierNumber); // to check the income of each cashier this method was called
                            }
                            break;
                        case "112","GUI":
                            try {
                                Application.launch(HelloApplication.class, args);
                            }catch(InputMismatchException exception){
                                    System.out.println("ERROR!");
                    }
                            break;
                        case "999", "EXT":
                            if(FoodQueue.count==10){
                                System.out.println("Program Exit!");
                            }
                            System.exit(0);//exit from the program
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
            }
        }
    }
}

