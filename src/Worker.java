// Java Program to Illustrate Worker Class
// to take Input from the Worker and related Information
import java.util.Scanner;
public class Worker {
    String workerName;
    String regNum;
    Productset pulledpros[] = new Productset[3];
    public int proCount = 0;
    Scanner input = new Scanner(System.in);
    public Worker()
    {
        System.out.println("Enter Worker Name:");
        this.workerName = input.nextLine();
        System.out.println("Enter Registration Number:");
        this.regNum = input.nextLine();
    }
}
