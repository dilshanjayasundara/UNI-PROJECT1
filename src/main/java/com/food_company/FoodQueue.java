package com.food_company;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.Scanner;

public class FoodQueue  {
    public static int count;//Total count of all three queues
    public static int count_que1;//count of customers at queue 1
    public static int count_que2;//count of customers at queue 2
    public static int count_que3;//count of customers at queue 3
    public static int countWaitingQueue;//customer count of waiting queue
    public static String[] cashier1array = new String[2];//Array for queue1
    public static String[] cashier2array = new String[3];//Array for queue2
    public static String[] cashier3array = new String[5];//Array for queue3

    public static String[][] cashiers = {cashier1array, cashier2array, cashier3array};// 2D array which was created to add all the customer details
    public static ArrayDeque<String> waiting=new ArrayDeque<>();// waiting queue which was created to add customers when all three queues are full of customers

    public  void addCustomersToArray(String name1){
        if (count_que1 <= cashier1array.length) {//check whether the queue of cashier 1 is not fulled
            if (((count_que1 == 0 && count_que2 == 0 && count_que3 == 0) || (count_que1 == 1 && count_que2 == 1 && count_que3 == 1))) { // check the condition of all three queues at once
                count_que1++;
                count++;
                if(countWaitingQueue==0){//check whether there are no customers in waiting queue
                    cashier1array[count_que1 - 1] = name1;
                }else if(countWaitingQueue>0){// check whether there are customers in waiting queue
                    cashier1array[count_que1 - 1] = waiting.getFirst(); //when customer is removed from the queue, it would be replaced by another customer in waiting queue
                    countWaitingQueue--;
                }
                System.out.println(name1 + " was added to the Queue of Cashier 1!");
            }else if(count_que2 <= cashier2array.length)
                if ((count_que1 == 1 && count_que2 == 0 && count_que3 == 0) | (count_que1 == 2 && count_que2 == 1 && count_que3 == 1) | (count_que1 == 2 && count_que2 == 2 && count_que3 == 2)) {
                    count_que2++;
                    count++;
                    if(countWaitingQueue==0){
                        cashier2array[count_que2 - 1] = name1;
                    }else if(countWaitingQueue>0){
                        cashier2array[count_que2 - 1] = waiting.getFirst();
                        countWaitingQueue--;
                    }
                    System.out.println(name1 + " was added to the Queue of Cashier 2!");
                }else if(count_que3 <= cashier3array.length) {
                    if ((count_que1 == 1 && count_que2 == 1 && count_que3 == 0) | (count_que1 == 2 && count_que2 == 2 && count_que3 == 1) | (count_que1 == 2 && count_que2 == 3 && count_que3 <= 4)) {
                        count_que3++;
                        count++;
                        if (countWaitingQueue == 0) {
                            cashier3array[count_que3 - 1] = name1;
                        } else if (countWaitingQueue > 0) {
                            cashier3array[count_que3 - 1] = waiting.getFirst();
                            countWaitingQueue--;
                        }
                        System.out.println(name1 + " was added to the Queue of Cashier 3!");
                    } else {
                        waiting.add(name1);                                                                 //This part was hypothetical, circular with waiting queue and other three queues,
                        countWaitingQueue++;
                        if (countWaitingQueue > 0 && count<10) {
                            if(count_que1<cashier1array.length && count_que2<=cashier2array.length && count_que3<=cashier3array.length) {  //think the customers removed suddenly at one queue like queue 2, then all other
                                cashier1array[count_que1] = waiting.getFirst();                                                             //queue 1 & 3 are fulled, then operator should be able to add customers to queue 2
                                waiting.removeFirst();
                                countWaitingQueue--;
                                count++;
                                count_que1++;
                                System.out.println(name1 + " was added to the Queue of Cashier Number 1!");
                            }else if(count_que2<cashier2array.length && count_que1==cashier1array.length && count_que3<=cashier3array.length) {
                                cashier2array[count_que2] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                count++;
                                count_que2++;
                                System.out.println(name1 + " was added to the Queue of Cashier Number 2!");
                            }else if(count_que1==cashier1array.length && count_que2==cashier2array.length) {
                                cashier3array[count_que3] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                count++;
                                count_que3++;
                                System.out.println(name1 + " was added to the Queue of Cashier Number 3!");
                            }
                        }else {
                            System.out.println(name1 + " was added to the Waiting Queue!");
                        }
                    }
                }
        }
    }
    public void removeServedCustomerQueue(int servedCashier) {     //removeCustomerQueue2 method was created to remove customers from the queue of cashier 2
        try {
            switch (servedCashier) {
                case 1:
                    if(count_que1>0 && Customer.burgers>=10) {
                        for (int i = servedCashier; i > 0; i--) {
                            var temp=cashier1array[i-1];
                            cashier1array[i - 1] = cashier1array[i];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier1array[i] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println( temp + " was removed from the Queue of Cashier Number 1 with "+Main.countOfBurgers+" Burgers!");
                                System.out.println(cashier1array[i]+ " was added from the Waiting Queue to the Queue of Cashier Number 1!");
                            }else{
                                cashier1array[i]=null;
                                count--;
                                count_que1--;
                                System.out.println( temp + " was removed from the Queue of Cashier Number 1 with "+Main.countOfBurgers+" Burgers!");
                            }
                            System.out.println();
                        }
                    }else {
                        System.out.println("Queue 1 is Empty, Select Another Queue!");
                    }
                    break;
                case 2:
                    if(count_que2>0 && Customer.burgers>=10) {
                        for (int i = (servedCashier - 1); i > 0; i--) {
                            var temp=cashier2array[i-1];
                            cashier2array[i - 1] = cashier2array[i];
                            cashier2array[i] = cashier2array[i + 1];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier2array[i+1] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp + " was removed from the Queue of Cashier Number 2 with "+Main.countOfBurgers+" Burgers!");
                                System.out.println(cashier2array[i+1]+ " was added from the Waiting Queue to the Queue of Cashier Number 2!");
                            }else {
                                cashier2array[i + 1] = null;
                                count--;
                                count_que2--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 2 with "+Main.countOfBurgers+" Burgers!");
                            }
                            System.out.println();
                        }
                    }else {
                        System.out.println("Queue 2 is Empty, Select Another Queue!");
                    }
                    break;
                case 3:
                    if(count_que3>0 && Customer.burgers>=10) {
                        for (int i = (servedCashier - 2); i > 0; i--) {
                            var temp=cashier3array[i-1];
                            cashier3array[i - 1] = cashier3array[i];
                            cashier3array[i] = cashier3array[i + 1];
                            cashier3array[i + 1] = cashier3array[i + 2];
                            cashier3array[i + 2] = cashier3array[i + 3];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier3array[i+3] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3 with "+Main.countOfBurgers+" Burgers!");
                                System.out.println(cashier3array[i+3]+ " was added from the Waiting Queue to the Queue of Cashier Number 3!");
                            }else {
                                cashier3array[i + 3] = null;
                                count--;
                                count_que3--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3 with "+Main.countOfBurgers+" Burgers!");
                            }
                            System.out.println();
                        }
                    }else {
                        System.out.println("Queue 3 is Empty, Select Another Queue!");
                    }
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter Valid Number!");
        }
    }
    public static void removeCustomerQueue1(int CUSTOMER) {     //removeCustomerQueue1 method was created to remove customers from the queue of cashier 1
        try {
            if(count_que1>0) {
                switch (CUSTOMER) {
                    case 1:
                        for (int i = CUSTOMER; i > 0; i--) {
                            var temp=cashier1array[i-1];
                            cashier1array[i - 1] = cashier1array[i];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier1array[i] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 1!");
                                System.out.println(cashier1array[i]+ " was added from the Waiting Queue to the Queue of Cashier Number 1!");
                            }else{
                                cashier1array[i]=null;
                                count--;
                                count_que1--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 1!");

                            }
                            System.out.println();
                        }
                        break;
                    case 2:
                        var temp=cashier1array[CUSTOMER-1];
                        if (countWaitingQueue>0) {// check whether there are customers in waiting queue
                            cashier1array[CUSTOMER-1] = waiting.getFirst();
                            waiting.removeFirst();
                            countWaitingQueue--;
                            System.out.println(temp+ " was removed from the Queue of Cashier Number 1!");
                            System.out.println(cashier1array[CUSTOMER-1]+ " was added from the Waiting Queue to the Queue of Cashier Number 1!");
                        }else{
                            cashier1array[CUSTOMER-1]=null;
                            count--;
                            count_que1--;
                            System.out.println(temp+ " was removed from the Queue of Cashier Number 1!");
                        }
                        System.out.println();
                        break;
                }
            }else {
                System.out.println("Queue 1 is Empty, Select Another Queue!");
            }
        }catch (InputMismatchException e){
            System.out.println("Enter Valid Number!");
        }
    }
    public static void removeCustomerQueue2(int CUSTOMER) {     //removeCustomerQueue2 method was created to remove customers from the queue of cashier 2
        try {
            if(count_que2>0) {
                switch (CUSTOMER) {
                    case 1:
                        for (int i = CUSTOMER; i > 0; i--) {
                            var temp=cashier2array[i-1];
                            cashier2array[i - 1] = cashier2array[i];
                            cashier2array[i]=cashier2array[i+1];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier2array[i+1] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 2!");
                                System.out.println(cashier2array[i+1]+ " was added from the Waiting Queue to the Queue of Cashier Number 2!");
                            }else {
                                cashier2array[i + 1] = null;
                                count--;
                                count_que2--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 2!");
                            }
                            System.out.println();
                        }
                        break;
                    case 2:
                        for (int i = (CUSTOMER-1 ); i > 0; i--) {
                            var temp=cashier2array[i];
                            cashier2array[i] = cashier2array[i+1];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier2array[i+1] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 2!");
                                System.out.println(cashier2array[i+1]+ " was added from the Waiting Queue to the Queue of Cashier Number 2!");
                            }else {
                                cashier2array[i + 1] = null;
                                count--;
                                count_que2--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 2!");
                            }
                            System.out.println();
                        }
                        break;
                    case 3:
                        var temp=cashier2array[CUSTOMER-1];
                        if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                            cashier2array[CUSTOMER-1] = waiting.getFirst();
                            waiting.removeFirst();
                            countWaitingQueue--;
                            System.out.println(temp+ " was removed from the Queue of Cashier Number 2!");
                            System.out.println(cashier2array[CUSTOMER-1]+ " was added from the Waiting Queue to the Queue of Cashier Number 2!");
                        }else {
                            cashier2array[CUSTOMER - 1] = null;
                            count--;
                            count_que2--;
                            System.out.println(temp+ " was removed from the Queue of Cashier Number 2!");
                        }
                        System.out.println();
                        break;
                }
            }else {
                System.out.println("Queue 2 is Empty, Select Another Queue!");
            }
        }catch (InputMismatchException e){
            System.out.println("Enter Valid Number!");
        }
    }
    public static void removeCustomerQueue3(int CUSTOMER) {     //removeCustomerQueue3 method was created to remove customers from the queue of cashier 3
        try {
            if(count_que3>0) {
                switch (CUSTOMER) {
                    case 1:
                        for (int i = CUSTOMER; i > 0; i--) {
                            var temp=cashier3array[i-1];
                            cashier3array[i-1] = cashier3array[i];
                            cashier3array[i]=cashier3array[i+1];
                            cashier3array[i+1] = cashier3array[i+2];
                            cashier3array[i+2] = cashier3array[i+3];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier3array[i+3] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                                System.out.println(cashier3array[i+3]+ " was added from the Waiting Queue to the Queue of Cashier Number 3!");
                            }else {
                                cashier3array[i + 3] = null;
                                count--;
                                count_que3--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                            }
                        }
                        break;
                    case 2:
                        for (int i = (CUSTOMER-1); i > 0; i--) {
                            var temp=cashier3array[i];
                            cashier3array[i]=cashier3array[i+1];
                            cashier3array[i+1] = cashier3array[i+2];
                            cashier3array[i+2] = cashier3array[i+3];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier3array[i+3] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                                System.out.println(cashier3array[i+3]+ " was added from the Waiting Queue to the Queue of Cashier Number 3!");
                            }else {
                                cashier3array[i + 3] = null;
                                count--;
                                count_que3--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                            }
                        }
                        break;
                    case 3:
                        for (int i = (CUSTOMER-2); i > 0; i--) {
                            var temp=cashier3array[i+1];
                            cashier3array[i+1] = cashier3array[i+2];
                            cashier3array[i+2] = cashier3array[i+3];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier3array[i+3] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                                System.out.println(cashier3array[i+3]+ " was added from the Waiting Queue to the Queue of Cashier Number 3!");
                            }else {
                                cashier3array[i + 3] = null;
                                count--;
                                count_que3--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                            }
                        }
                        break;
                    case 4:
                        for (int i = (CUSTOMER-3); i > 0; i--) {
                            var temp=cashier3array[i+2];
                            cashier3array[i+2] = cashier3array[i+3];
                            if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                                cashier3array[i+3] = waiting.getFirst();
                                waiting.removeFirst();
                                countWaitingQueue--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                                System.out.println(cashier3array[i+3]+ " was added from the Waiting Queue to the Queue of Cashier Number 3!");
                            }else {
                                cashier3array[i + 3] = null;
                                count--;
                                count_que3--;
                                System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                            }
                        }
                        break;
                    case 5:
                        var temp=cashier3array[CUSTOMER-1];
                        if (countWaitingQueue>0) {                      // check whether there are customers in waiting queue
                            cashier3array[CUSTOMER-1] = waiting.getFirst();
                            waiting.removeFirst();
                            countWaitingQueue--;
                            System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                            System.out.println(cashier3array[CUSTOMER-1]+ " was added from the Waiting Queue to the Queue of Cashier Number 3!");
                        }else {
                            cashier3array[CUSTOMER - 1] = null;
                            count--;
                            count_que3--;
                            System.out.println(temp+ " was removed from the Queue of Cashier Number 3!");
                        }
                        break;
                }
            }else {
                System.out.println("Queue 3 is Empty, Select Another Queue!");
            }
        }catch (InputMismatchException e){
            System.out.println("Enter Valid Number!");
        }
        System.out.println();
    }
    public static void CustomerView100(){            //This method was created to view the option 100
        for(int i = 0; i <=10; i++){
            for(var cashier : cashiers){
                try {
                    System.out.print(cashier[i] == null ? "  X  " : "  O  ");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
    }
    public static void CustomerView101(){           //This method was created to view the option 101
        for(int i = 0; i <=10; i++){
            for(var cashier : cashiers) {
                try {
                    System.out.print(cashier[i] == null ? "  X  " : "     ");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
    }
    public static void createFile() {
        File FoodiesFave ;
        try {
            File file = new File("FoodiesFave.txt");
            boolean file_created = file.createNewFile();
            if (file_created) {
                System.out.println("File created : " + file.getName());
            } else if (file.exists()) {
                System.out.println("File is not created");
            }
            FileWriter writer = new FileWriter("FoodiesFave.txt");
            writer.write("Customers of Cashier 1        : " + Arrays.toString(cashier1array) +"\nCustomers of Cashier 2        : " + Arrays.toString(cashier2array) + "\nCustomers of Cashier 3        : " + Arrays.toString(cashier3array)+"\nCustomers of Waiting Queue    : " + (waiting));
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void readFile(){
        try {
            File file = new File("FoodiesFave.txt");
            Scanner file_reader = new Scanner(file);
            while (file_reader.hasNextLine()) {
                String text = file_reader.nextLine();
                System.out.println(text);
            }
        }
        catch(IOException e){
            System.out.println("Error while reading the file ");
            e.printStackTrace();
        }
    }
    public void alphabeticOrder(){
        int length1 = cashier1array.length;
        int length2 = cashier2array.length;
        int length3 = cashier3array.length;
        Arrays.compare(cashier1array,cashier2array);
        Arrays.compare(cashier2array,cashier3array);

        String[] array_1 = new String[length1 + length2 + length3];
        for (String name: cashier1array) {
            if (name != null) {
                for (int i = 0; i < array_1.length; i++) {
                    if (array_1[i] == null) {
                        array_1[i] = name;
                        break;
                    }
                }
            }
        }
        for (String name: cashier2array) {
            if (name != null) {
                for (int i = 0; i < array_1.length; i++) {
                    if (array_1[i] == null) {
                        array_1[i] = name;
                        break;
                    }
                }
            }
        }
        for (String name: cashier3array) {
            if (name != null) {
                for (int i = 0; i < array_1.length; i++) {
                    if (array_1[i] == null) {
                        array_1[i] = name;
                        break;
                    }
                }
            }
        }
        sort(array_1);
        for (String element : array_1) {
            System.out.println(" " + element);
        }
    }
    public static void sort(String[] array_1) {
        try {
            for (int i = 0; i < count - 1; i++) {
                if (!(array_1[i].equalsIgnoreCase(""))) {
                    for (int j = 0; j < count - i - 1; j++) {
                        if (compareStrings(array_1[j], array_1[j + 1]) > 0) {
                            String word = array_1[j]; //temporarily store
                            array_1[j] = array_1[j + 1];
                            array_1[j + 1] = word;
                        }
                    }
                }
            }
        }catch(NullPointerException e){
            System.out.println(" ");
        }
    }
    public static int compareStrings(String firstOne, String secondOne) { //compare strings of array_1
        int size1 = firstOne.length();
        int size2 = secondOne.length();
        int minimumSize = Math.min(size1, size2);//**********
        for (int i = 0; i < minimumSize; i++) {
            if (firstOne.charAt(i) != secondOne.charAt(i)) {
                return firstOne.charAt(i) - secondOne.charAt(i);
            }
        }
        return size1 - size2;
    }
}

