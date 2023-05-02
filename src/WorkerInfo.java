// Java Program to Illustrate Workerinfo Class
// To Do all the Operations related to Workers:

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerInfo implements DemoWork {
    Scanner input = new Scanner(System.in);
    Worker theWorkers[] = new Worker[50];
    private String filePath="C:\\Users\\suve5\\Downloads\\StockPro Demo\\worker.txt";
    public static int count = 0;

    public void addWorker(Worker s) {
      /* for (int i = 0; i < count; i++) {
            if (s.regNum.equalsIgnoreCase(theWorkers[i].regNum)) {
                // Print statement
                System.out.println("Worker of Reg Num " + s.regNum + " is Already Registered.");
                return;
            }
        }*/

        if (count < 50) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
                bw.write(s.regNum+","+s.workerName);
                bw.newLine();
                count++;
                System.out.println("Worker added to file.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Cannot add worker - maximum capacity reached.");
        }
    }


    public void showAllWorkers() {
        // Display message
        System.out.println("\t\t\t\tSHOW ALL WORKERS\n");

        // Class data members
        Path filePath = Paths.get("C:\\Users\\suve5\\Downloads\\StockPro Demo\\worker.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            System.out.println("Reg Number\tWorker Name");
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length >= 2) {
                    System.out.printf("%s\t\t%s%n", fields[0], fields[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }



    public int isWorker() {
        System.out.println("Enter your registration number:");
        String regNum = input.nextLine();

        try {
            // Read data from file
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\suve5\\Downloads\\StockPro Demo\\worker.txt"));
            String line;
            List<String[]> records = new ArrayList<String[]>();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                records.add(fields);
            }
            reader.close();

            // Search for worker in records
            for (int i = 0; i < records.size(); i++) {
                String[] fields = records.get(i);
                if (fields[0].equalsIgnoreCase(regNum)) {
                    return i;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print statements
        System.out.println("Worker is not Registered.");
        System.out.println("Get Registered First.");
        return -1;
    }


    public void checkInPro() {
        int index = isWorker();
        if (index != -1) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter registration number of the item to update:");
            String regNum = input.nextLine();
    
            try {
                // Open the file for reading
                File file = new File( "C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt");
                Scanner scanner = new Scanner(file);
    
                // Create an ArrayList to store the items
                ArrayList<String[]> items = new ArrayList<>();

                // Read the data from the file into the ArrayList
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");
                    items.add(tokens);
                }
                scanner.close();
    
                // Find the item with the matching registration number and update the quantity
                boolean found = false;
                for (String[] item : items) {
                    if (item[0].equals(regNum)) {
                        found = true;
                        System.out.println("Enter quantity to check in for " + item[1] + ":");
                        String qtyStr = input.nextLine();
                        int qty = Integer.parseInt(qtyStr);
                        int existingQty = Integer.parseInt(item[3]);
                        item[3] = Integer.toString(existingQty + qty);
                        break;
                    }
                }
    
                if (found) {
                    // Write the updated data back to the file
                    FileWriter writer = new FileWriter(file);
                    for (String[] item : items) {
                        writer.write(item[0] + "," + item[1] + "," + item[2] + "," + item[3] + "\n");
                    }
                    writer.close();
    
                    System.out.println("Quantity updated successfully!");
                } else {
                    System.out.println("Item not found!");
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity entered. Please enter a valid integer.");
            }
        }
    }
    public void checkOutPro() {
        int index = isWorker();
        if (index != -1) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter registration number of the item to update:");
            String regNum = input.nextLine();
    
            try {
                // Open the file for reading
                File file = new File( "C:\\Users\\suve5\\Downloads\\StockPro Demo\\inventory.txt");
                Scanner scanner = new Scanner(file);
    
                // Create an ArrayList to store the items
                ArrayList<String[]> items = new ArrayList<>();
    
                // Read the data from the file into the ArrayList
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");
                    items.add(tokens);
                }
                scanner.close();
    
                // Find the item with the matching registration number and update the quantity
                boolean found = false;
                for (String[] item : items) {
                    if (item[0].equals(regNum)) {
                        found = true;
                        System.out.println("Enter quantity to checkout for " + item[1] + ":");
                        String qtyStr = input.nextLine();
                        int qty = 0;
                        try {
                            qty = Integer.parseInt(qtyStr);
                            if (qty < 0) {
                                throw new IllegalArgumentException("Quantity cannot be negative");
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Invalid quantity: " + qtyStr);
                        }
                        int currentQty = Integer.parseInt(item[3]);
                        if (qty > currentQty) {
                            System.out.println("Insufficient quantity");
                        } else {
                            item[3] = Integer.toString(currentQty - qty);
                            // Write the updated data back to the file
                            FileWriter writer = new FileWriter(file);
                            for (String[] it : items) {
                                writer.write(it[0] + "," + it[1] + "," + it[2] + "," + it[3] + "\n");
                            }
                            writer.close();
    
                            System.out.println("Checkout successful");
                        }
                        break;
                    }
                }
    
                if (!found) {
                    System.out.println("Item not found!");
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
     
    
    
    }

    
    
