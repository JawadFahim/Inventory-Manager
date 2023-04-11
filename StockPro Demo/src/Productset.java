// Java Program to Illustrate Product Class that
// takes Input from the Products and related information


import java.util.Scanner;


public class Productset {


    public int sNo;
    public String pName;
    public String comName;
    public int pQuant;


    Scanner input = new Scanner(System.in);


    public Productset() {

        System.out.println("Enter Serial No of product:");
        this.sNo = input.nextInt();
        input.nextLine();

        System.out.println("Enter product name:");
        this.pName = input.nextLine();

        System.out.println("Enter company name:");
        this.comName = input.nextLine();

        System.out.println("Enter Quantity of " + pName + "-");
        this.pQuant = input.nextInt();

    }



}
