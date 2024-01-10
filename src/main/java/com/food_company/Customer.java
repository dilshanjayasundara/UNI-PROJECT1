package com.food_company;

import java.util.InputMismatchException;

public class Customer {
    public static int burgers = 50;
    static int addBurgers;//number of added burgers as required
    static int burgersCount2;//Excess count of burgers
    public static int servedBurgersOfQueue1;//Number of burgers purchased by customer at Cashier 1
    public static int servedBurgersOfQueue2;//Number of burgers purchased by customer at Cashier 2
    public static int servedBurgersOfQueue3;//Number of burgers purchased by customer at Cashier 3
    public static int priceOfOneBurger = 650;
    public static int incomeOfQueue1;//Income of cashier 1
    public static int incomeOfQueue2;//Income of cashier 2
    public static int incomeOfQueue3;//Income of cashier 3

    public static void burgerVariation(int servedCashier, int countOfBurgers) {  //buggerVariation method was created to remove burgers from the stock when customer was served
        if (burgers >=10) {
            if(burgers-countOfBurgers>=10){
                try {
                    if (servedCashier == 1) {
                        if (FoodQueue.count_que1 > 0) {
                            burgers -= countOfBurgers;
                            servedBurgersOfQueue1 += countOfBurgers;
                        } else {
                            System.out.println("No Burgers are reduced from the Stock!");
                        }
                    }
                    if (servedCashier == 2) {
                        if (FoodQueue.count_que2 > 0) {
                            burgers -= countOfBurgers;
                            servedBurgersOfQueue2 += countOfBurgers;
                        } else {
                            System.out.println("No Burgers are reduced from the Stock!");
                        }
                    }
                    if (servedCashier == 3) {
                        if (FoodQueue.count_que3 > 0) {
                            burgers -= countOfBurgers;
                            servedBurgersOfQueue3 += countOfBurgers;
                        } else {
                            System.out.println("No Burgers are reduced from the Stock!");
                        }
                    }
                }catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                }
            }else {
                System.out.println("Not Sufficient Burgers at Store! Only " +(burgers-10)+ " Burgers are Available!");
                System.out.println("Add Burgers to the Store!");
            }
        }if(burgers==10){
            System.out.println("Add Burgers to the Store!");
        }
    }
    public static void remainingBurgers(){  // Method was created to show the remaining burger count at the store
        System.out.println("**** REMAINING BURGER STOCK ****");
        if(burgers>=10){
            System.out.println("REMAINING BURGERS : " + burgers);
        }
        if(burgers==10){
            System.out.println("Burgers should be added to the store!");
        }
    }
    public static void incomeOfQueue(int cashierNumber) {   // Method was created to show the income of each cashier
        if (cashierNumber == 1) {
            incomeOfQueue1 = servedBurgersOfQueue1 * priceOfOneBurger;
            System.out.println("Income of Queue 1 : " + incomeOfQueue1);
        }
        if (cashierNumber == 2) {
            incomeOfQueue2 = servedBurgersOfQueue2 * priceOfOneBurger;
            System.out.println("Income of Queue 2 : " + incomeOfQueue2);
        }
        if (cashierNumber == 3) {
            incomeOfQueue3 = servedBurgersOfQueue3 * priceOfOneBurger;
            System.out.println("Income of Queue 3 : " + incomeOfQueue3);
        }
    }
    public static void addBurgers() { //This method was created to add burgers as needed
        System.out.println("**** ADD BURGERS TO THE STORE ****");
        if(burgers==10 || burgers<50) {
            final int burgersMax = 50;
            addBurgers = burgersMax - burgers;
            System.out.println(addBurgers + " Burgers were added to the Stock!");
            burgers = 50;
        }else if (burgers==50) {
            System.out.println("No need to add Burgers, Burger Stock is "+burgers);
        } else if (burgers>50) {
            burgersCount2= burgers-50; //Excess count of burgers
            burgers=50;
            System.out.println(burgersCount2+" Burgers were removed and Stock set to 50!");
        }
        System.out.println("\n");

    }
}