/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miles
 */
public class Travel_Project_driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("WARNING: LIST IS AN ESTIMATION. CHANGES MAY BE REQUIRED!");
        System.out.println("*************************************************");
        System.out.println("\nhow many days will you be traveling?(whole number)");
        int travelDays = scan.nextInt();
        System.out.println("How many Miles of driving?(whole number)");
        int miles = scan.nextInt();
        
         try {
            
            
            BufferedReader reader;
            reader = new BufferedReader(new FileReader("C:\\Users\\miles\\OneDrive\\Documents\\NetBeansProjects\\Travel_Project\\src\\travel_project\\items"));
            String nextLine = reader.readLine();
            double totalCost = 0;
            System.out.printf("%-15s%-8s%-6s","Item","Amount","Cost");
            while (nextLine != null){
                //System.out.println(nextLine);
                String[] itemComp = nextLine.split(",");
                
                String name = itemComp[0];
                
                double cost = Double.parseDouble(itemComp[2]);
                
                double amount = Double.parseDouble(itemComp[1]);
                if (amount==0){
                    System.out.printf("\n%-15s%-8s",name,"1"); //items that youve puchased and you need only one
                }
                else{
                    
                    if (cost == 0.00){
                        System.out.printf("\n%-15s%-8.0f",name,travelDays*amount);  //items that youve puchased and you need multiple
                    }
                    else{
                        System.out.printf("\n%-15s%-8.0f%-6.0f",name,travelDays*amount,travelDays*cost);  //everything else
                    }
                    
                }
                
             
                
                nextLine = reader.readLine();
                totalCost = travelDays*cost + totalCost;
            }
            double fuelCost = 2.00; // 
             System.out.printf("\n%-15s%-8s%-6s","Gasoline",miles/20,fuelCost*(miles/20));
             totalCost = fuelCost*(miles/20) + totalCost;
             System.out.println("\nCost Estimate: "+ totalCost+"$");
             System.out.println("cash: "+ ((miles/20)*fuelCost+travelDays*20) );
             System.out.println("Safe Travels!");
             
            reader.close();
           

             

             
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("didnt work");
            Logger.getLogger(Travel_Project_driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
