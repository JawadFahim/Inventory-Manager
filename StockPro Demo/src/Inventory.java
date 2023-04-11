// Java Program to Illustrate Application CLass
// To Create The Menu For the Program


import java.util.Scanner;


public class Inventory {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Displaying menu
        System.out.println(
                "*******************Welcome to the Inventory!*******************");
        System.out.println(
                "				 Select From The Following Options:			 ");
        System.out.println(
                "********************************************************************");

        // Creating object of ProInfo class
        ProInfo ob = new ProInfo();
        // Creating object of Workerinfo class
        WorkerInfo obWorker = new WorkerInfo();

        int choice;
        int searchChoice;

        // Creating menu

        do {

            ob.dispMenu();
            choice = input.nextInt();


            switch (choice) {


                case 1:
                    Productset b = new Productset();
                    ob.addProduct(b);
                    break;




                case 2:

                    System.out.println(
                            " press 1 to Search with Product Serial No.");
                    System.out.println(
                            " Press 2 to Search with Product's company Name.");
                    System.out.println(
                            " Press 3 to Search with Product's Name.");
                    searchChoice = input.nextInt();

                    switch (searchChoice) {


                        case 1:
                            ob.searchBySno();
                            break;


                        case 2:
                            ob.searchByCompanyName();
                            break;
                        case 3:
                            ob.searchByProductName();
                            break;
                    }
                    break;


                case 3:
                    ProInfo.showAllProducts();
                    break;


                case 4:
                    Worker s = new Worker();
                    obWorker.addWorker(s);
                    break;


                case 5:
                    obWorker.showAllWorkers();
                    break;


                case 6:
                    obWorker.checkOutPro();
                    break;




                // Default case that will execute for sure if above cases does not match
                default:


                    System.out.println("ENTER BETWEEN 0 TO 8.");
            }

        }

        // Checking condition at last where we are
        // checking case entered value is not zero

        while (choice != 0);
    }
}