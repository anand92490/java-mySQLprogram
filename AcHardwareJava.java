//import java.util.Formatter;  
import java.sql.*;
import java.util.Scanner;

public class AcHardwareJava {

   public static void main(String[] args) {
   
   
   	// insertToCustomerDb();
   	// printCustomerDb();
   
   	// insertToOrdersDb();
   	// printOrdersDb();
   
   	// insertProductsDb();
   	// printCustomersDb();
   
   	// printCustomerOrderView();
   	// printOrderSummaryView();

   
      Scanner scan = new Scanner(System.in);
      int dbSelectionVal = 0;

      String userInput1 = null;
   
      while (dbSelectionVal != 9){
         System.out.println("\n*******************************************");
         System.out.println("-------------------------------------------");
         System.out.println("Enter 1 to SELECT Customer Table" + "\nEnter 2 to SELECT Orders Table"
            	+ "\nEnter 3 to SELECT Products Table" + "\nEnter 4 to VIEW AS Customer's Order Table"
            	+ " \nEnter 5 to VIEW AS Order Summary Table" + "\nEnter 6 to INSERT data into Customer Table"
            	+ "\nEnter 7 to INSERT data into Orders Table" + "\nEnter 8 to INSERT data into Products Table"
            	+ "\n\n--  Enter 9 to EXIT anytime  --");
         System.out.println("-------------------------------------------");
         System.out.println("*******************************************");
      
         System.out.println("\nPlease select an option from above:");
      
         dbSelectionVal = scan.nextInt();
         scan.nextLine();
      
         if (dbSelectionVal == 1) {
            printCustomerDb();
         
         } else if (dbSelectionVal == 2) {
            printOrdersDb();
         
         } else if (dbSelectionVal == 3) {
            printProductsDb();
         
         } else if (dbSelectionVal == 4) {
            printCustomerOrderView();
         
         } else if (dbSelectionVal == 5) {
            printOrderSummaryView();
         
         } else if (dbSelectionVal == 6) {
            do {
            
               System.out.println(
                  	"would you like to Insert Customer information into Customer Table?. Enter (Y/N).");
            
               userInput1 = scan.next();
               if (userInput1.equalsIgnoreCase("Y")) {
                  insertToCustomerDb();
               
               } else if (userInput1.equalsIgnoreCase("N")) {
                  System.out.println("Got it! What would you like to do next");
                  break;
               
               } else {
                  System.out.println("Try again with (Y/N) only");
               }
            } while (true);
         
         } else if (dbSelectionVal == 7) {
            do {
            
               System.out.println("Would you like to Insert Order information into Orders Table?. Enter (Y/N).");
            
               userInput1 = scan.next();
               if (userInput1.equalsIgnoreCase("Y")) {
                  insertToOrdersDb();
               
               } else if (userInput1.equalsIgnoreCase("N")) {
                  System.out.println("Got it! What would you like to do next");
                  break;
               
               } else {
                  System.out.println("Try again with (Y/N) only");
               }
            } while (true);
         
         } else if (dbSelectionVal == 8) {
            do {
               System.out
                  	.println("would you like to Insert another Product into the Products Table?. Enter (Y/N).");
            
               userInput1 = scan.next();
               if (userInput1.equalsIgnoreCase("Y")) {
                  insertProductsDb();
               
               } else if (userInput1.equalsIgnoreCase("N")) {
                  System.out.println("Got it! What would you like to do next");
                  break;
               
               } else {
                  System.out.println("Try again with (Y/N) only");
               }
            } while (true);
         
         } else if (dbSelectionVal >= 10) {
            System.out.println("\nPlease enter the values between 1 and 8 only and 9 to EXIT and\n");
         
         } else {
            System.out.println(
               	"\nGood bye now!! Thank you for visiting AC Hardware. Customer satisfaction is always guaranteed.");
            scan.close();
         }
      
      } // end of user selection while loop
   
   }// end main

   public static void insertToCustomerDb() {
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
         Scanner userInput = new Scanner(System.in);
      
         System.out.println("Welcome to AC Hardwares! Please enter the Customer's information below as prompted");
      
         System.out.print("Customer ID: ");
         int customerID = userInput.nextInt();
         userInput.nextLine();
      
      
         System.out.print("First Name: ");
         String fName = userInput.nextLine();
      
         System.out.print("Last Name: ");
         String lName = userInput.nextLine();
      
         System.out.print("Phone number (10 numbers required): ");
         long phone = userInput.nextLong();
         userInput.nextLine();
      
         System.out.print("Email: ");
         String email = userInput.nextLine();
      
         System.out.print("Address: ");
         String address = userInput.nextLine();
      	
      
         String sqlInsert = "INSERT INTO Customer VALUES(" + customerID + ",'" + fName + "', '" + lName + "',"
            	+ phone + ",'" + email + "', '" + address + "')";
         stmt.executeUpdate(sqlInsert);
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of insertToCustomerDb

   public static void printCustomerDb() {
   
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
      
         String selectDB = "Select * from Customer";
         ResultSet rs = stmt.executeQuery(selectDB);
         System.out.println("______________________________________________________________________________________________________________________________");
         System.out.println("						 	CUSTOMER TABLE");
         System.out.println("______________________________________________________________________________________________________________________________");
      	
         System.out.printf(" %-11s | %-10s | %-22s | %-11s | %-30s | %-30s " , "customerID","fName","lName","phone","email","address");
         System.out.println();
         System.out.println("______________________________________________________________________________________________________________________________");
      	// retrieving all the values from the table
         while (rs.next()) {
            int customerID = rs.getInt("customerID");
            String fName = rs.getString("fName");
            String lName = rs.getString("lName");
            long phone = rs.getLong("phone");
            String email = rs.getString("email");
            String address = rs.getString("address");
         	
            System.out.printf(" %-11s | %-10s | %-22s | %-11d | %-30s | %-30s " , customerID,fName,lName,phone,email,address);
            System.out.println();
         //				System.out.println(
         //						customerID + ":   " + fName + ",  " + lName + ",  " + phone + ",  " + email + ",  " + address);
         }
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of printCustomerDb

   public static void insertToOrdersDb() {
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
         Scanner userInput = new Scanner(System.in);
      
         System.out.println("Please enter the Order details below as prompted.\n");
      
         System.out.print("Enter a unique order ID: ");
         int orderID = userInput.nextInt();
         userInput.nextLine();
      
         System.out.print("Enter a unique customer ID: ");
         int customerID = userInput.nextInt();
         userInput.nextLine();
      
         System.out.print("What is the oder total? ");
         double orderTotal = userInput.nextDouble();
         userInput.nextLine();
      
         System.out.print("What is the status of the order: ");
         String orderStatus = userInput.nextLine();
      
         String sqlInsertIntoOrders = "INSERT INTO Orders (orderID, customerID, orderTotal,orderStatus) VALUES("
            	+ orderID + "," + customerID + ", " + orderTotal + ",'" + orderStatus + "')";
         stmt.executeUpdate(sqlInsertIntoOrders);
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of insertToOrdersDb

   public static void printOrdersDb() {
   
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
      
         String selectDB = "Select * from Orders";
         ResultSet rs = stmt.executeQuery(selectDB);
      	
         System.out.println("____________________________________________________________________________________");
         System.out.println("				ORDERS TABLE");
         System.out.println("____________________________________________________________________________________");
      		
         System.out.printf(" %-9s | %-11s | %-12s | %-15s | %-23s " , "orderID","customerID","orderTotal","orderStatus","purchaseDate");
         System.out.println();
         System.out.println("____________________________________________________________________________________");
      	
      	
      //			
      	
      	// retrieving all the values from the table
         while (rs.next()) {
            int orderID = rs.getInt("orderID");
            int customerID = rs.getInt("customerID");
            double orderTotal = rs.getDouble("orderTotal");
            String orderStatus = rs.getString("orderStatus");
            Timestamp purchaseDate = rs.getTimestamp("purchaseDate");
            java.util.Date date = purchaseDate;
         	
            System.out.printf(" %-9s | %-11s | %-12s | %-15s | %-23s " , orderID,customerID,orderTotal,orderStatus,purchaseDate,"\n");
            System.out.println();
         
         }
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of printOrdersDb

   public static void insertProductsDb() {
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
         Scanner userInput = new Scanner(System.in);
      
         System.out.println("Please enter the Product details below as prompted.\n");
      
         System.out.print("Enter a unique product ID: ");
         int productID = userInput.nextInt();
         userInput.nextLine();
      
         System.out.print("Enter a unique order ID: ");
         int orderID = userInput.nextInt();
         userInput.nextLine();
      
         System.out.print("Enter product Category: ");
         String pCategory = userInput.nextLine();
      
         System.out.print("Name of the product: ");
         String pName = userInput.nextLine();
      
         System.out.print("Enter the product price?: ");
         double price = userInput.nextDouble();
         userInput.nextLine();
      
         System.out.print("Add product desctiption: ");
         String pDescription = userInput.nextLine();
      
         System.out.print("How many product quantity?: ");
         int pQty = userInput.nextInt();
         userInput.nextLine();
      
         String sqlInsertIntoProducts = "INSERT INTO Products VALUES(" + productID + ", " + orderID + ", '"
            	+ pCategory + "' , '" + pName + "', " + price + ", '" + pDescription + "', " + pQty + " )";
         stmt.executeUpdate(sqlInsertIntoProducts);
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of insertToOrdersDb

   public static void printProductsDb() {
   
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
      
         String selectDB = "Select * from Products";
         ResultSet rs = stmt.executeQuery(selectDB);
      	
         System.out.println("_______________________________________________________________________________________________________________________________________");
         System.out.println("							Products TABLE");
         System.out.println("_______________________________________________________________________________________________________________________________________");
      	
         System.out.printf(" %-10s | %-8s | %-14s | %-22s | %-7s | %-48s | %-4s" ,"productID", "orderID","pCategory","pName","price","pDescription","pQty");
         System.out.println();
         System.out.println("_______________________________________________________________________________________________________________________________________");
      
      	// retrieving all the values from the table
         while (rs.next()) {
            int productID = rs.getInt("productID");
            int orderID = rs.getInt("orderID");
            String pCategory = rs.getString("pCategory");
            String pName = rs.getString("pName");
            double price = rs.getDouble("price");
            String pDescription = rs.getString("pDescription");
            int pQty = rs.getInt("pQty");
         
         	
            System.out.printf(" %-10s | %-8s | %-14s | %-22s | %-7s | %-48s | %-4s" ,productID, orderID,pCategory,pName,price,pDescription,pQty);
            System.out.println();
         //				System.out.println(productID + ":   " + orderID + ",  " + pCategory + ",  " + pName + ",  " + price
         //						+ ",  " + pDescription + ",  " + pQty);
         }
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of printProductsDb

   public static void printCustomerOrderView() {
   
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
      
         String selectDB = "Select * from customerorder LIMIT 15";
         ResultSet rs = stmt.executeQuery(selectDB);
      	
         System.out.println("________________________________________________________________________________________________________________________");
         System.out.println("						CUSTOMER'S ORDER VIEW TABLE");
         System.out.println("________________________________________________________________________________________________________________________");		
      	
         System.out.printf(" %-11s | %-22s | %-11s | %-24s | %-21s | %-12s |" ,"fName", "lName","orderTotal","address","purchaseDate","orderStatus");
         System.out.println();
         System.out.println("________________________________________________________________________________________________________________________");
      						
      	// retrieving all the values from the table
         while (rs.next()) {
         
            String fName = rs.getString("fName");
            String lName = rs.getString("lName");
            double orderTotal = rs.getDouble("orderTotal");
            String address = rs.getString("address");
            Timestamp purchaseDate = rs.getTimestamp("purchaseDate");
            java.util.Date date = purchaseDate;
            String orderStatus = rs.getString("orderStatus");
         	
         	
            System.out.printf(" %-11s | %-22s | %-11s | %-24s | %-21s | %-12s |" ,fName, lName,orderTotal,address,purchaseDate,orderStatus);
            System.out.println();
         
         
         }
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of printCustomerOrderView

   public static void printOrderSummaryView() {
   
      Connection conn = null;
   
      try {
         conn = DriverManager.getConnection(
            	"jdbc:mysql://localhost:8889/AcHardware?serverTimezone=UTC&" + "user=root&password=root");
      
         Statement stmt = conn.createStatement();
      
      	// pardon my typo on the odersummary missed the r in order.
         String selectDB = "Select * from odersummary LIMIT 15";
         ResultSet rs = stmt.executeQuery(selectDB);
      	
         System.out.println("____________________________________________________________________________________________________________________________________________________________");
         System.out.println("							ORDER SUMMARY VIEW TABLE");
         System.out.println("____________________________________________________________________________________________________________________________________________________________");		
      	
         System.out.printf(" %-11s | %-22s | %-21s | %-24s | %-24s | %-22s | %-11s|" ,"fName", "lName","pName","price","address","purchaseDate","orderStatus");
         System.out.println();
         System.out.println("____________________________________________________________________________________________________________________________________________________________");
      						
      
      
      	// retrieving all the values from the table
         while (rs.next()) {
         
            String fName = rs.getString("fName");
            String lName = rs.getString("lName");
            String pName = rs.getString("pName");
            double price = rs.getDouble("price");
            String pQty = rs.getString("pName");
            String address = rs.getString("address");
            Timestamp purchaseDate = rs.getTimestamp("purchaseDate");
            java.util.Date date = purchaseDate;
            String orderStatus = rs.getString("orderStatus");
         
         	
            System.out.printf(" %-11s | %-22s | %-21s | %-24s | %-24s | %-22s | %-11s|" ,fName, lName,pName,price,address,purchaseDate,orderStatus);
            System.out.println();
         	
         }
      
      } catch (SQLException ex) {
      	// handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      
      }
   
   } // end of printOrderSummaryView

} // end class