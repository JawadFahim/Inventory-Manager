import java.io.*;
import java.util.Scanner;

public class ProInfo {
    public static int count;
    private Worker[] theWorkers = new Worker[50];
    private String filePath = "C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt";//addworker's filepath
    static Productset[] thepros = new Productset[50];
    static Scanner input = new Scanner(System.in);

    //Method 01
    //To create menu
    public void dispMenu() {

        // Displaying menu
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");

        System.out.println("Press 0 to Exit Application.");
        System.out.println("Press 1 to Add new Product.");
        System.out.println("Press 2 to Search a Product.");
        System.out.println("Press 3 to Show All Products.");
        System.out.println("Press 4 to Register Worker.");
        System.out.println("Press 5 to Show All Registered Workers.");
        System.out.println("Press 6 to Access existing product quantity ");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------");
    }


    //Method 02
    // To search Product by serial number
    public void searchBySno() {
        // Display message
        System.out.println("\t\t\t\tSEARCH BY SERIAL NUMBER\n");

        // Class data members
        int sNo;
        System.out.println("Enter Serial No of Product:");
        Scanner input = new Scanner(System.in);
        sNo = input.nextInt();

        boolean foundProduct = false;
        System.out.println("S.No\t\tName\t\tCompany\t\tAvailable Qty");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt"));
            String line = reader.readLine(); // skip header line
            while (line != null) {
                String[] tokens = line.split(",");
                if (Integer.parseInt(tokens[0]) == sNo) {
                    System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t"+ tokens[2] + "\t\t" + tokens[3]);
                    foundProduct = true;
                    break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        if (!foundProduct)
            System.out.println("No product for Serial No " + sNo + " found.");
    }



    // Method 03
    // To search product by company name
    public void searchByCompanyName() {
        // Display message
        System.out.println("\t\t\t\tSEARCH BY COMPANY NAME\n");

        // Class data members
        String comName;
        System.out.println("Enter company Name:");
        Scanner input = new Scanner(System.in);
        comName = input.nextLine();

        boolean foundProduct = false;
        System.out.println("S.No\t\tName\t\tCompany\t\tAvailable Qty");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt"));
            String line = reader.readLine(); // skip header line
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens[2].equalsIgnoreCase(comName)) {
                    System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t"+ tokens[2] + "\t\t" + tokens[3]);
                    foundProduct = true;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        if (!foundProduct)
            System.out.println("No products of " + comName + " found.");
    }


    // Method 4
    // To search product by product name
    public void searchByProductName() {
        // Display message
        System.out.println("\t\t\t\tSEARCH BY PRODUCT NAME\n");

        // Class data members
        String productName;
        System.out.println("Enter Product Name:");
        Scanner input = new Scanner(System.in);
        productName = input.nextLine();


        boolean foundProduct = false;
        System.out.println("S.No\t\tName\t\tCompany\t\tAvailable Qty");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens[1].equalsIgnoreCase(productName)) {
                    System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t"+ tokens[2] + "\t\t" + tokens[3]);
                    foundProduct = true;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        if (!foundProduct)
            System.out.println("No product with name '" + productName + "' found.");
    }


    //Method 05
    // To display all Products
    public static void showAllProducts() {
        try {
            File file = new File("C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt");
            Scanner scanner = new Scanner(file);

            // Print table headers
            System.out.println("\t\t\t\tSHOWING ALL Products\n");
            System.out.printf("%-10s%-20s%-20s%-20s%n", "S.No", "Name", "Company", "Available Qty");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] productData = line.split(",");

                int sNo = Integer.parseInt(productData[0]);
                String pName = productData[1];
                String comName = productData[2];
                int pQtyCopy = Integer.parseInt(productData[3]);


                // Print each product's details in a formatted row
                System.out.printf("%-10s%-20s%-20s%-20s%n", sNo, pName, comName, pQtyCopy);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Method 6
    // To edit the Product
    public void addProduct(Productset p) {
        for (int i = 0; i < count; i++) {
            if (p.pName.equalsIgnoreCase(thepros[i].pName)) {
                System.out.println("Product with name " + p.pName + " is already in the inventory.");
                return;
            }
        }

        if (count < 50) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
                bw.write(p.sNo + "," + p.pName + "," + p.comName + "," + p.pQuant );
                bw.newLine();
                count++;
                System.out.println("Product added to file.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Cannot add product - maximum capacity reached.");
        }
    }

}