import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Pages {
	
	//input variable
	private static int employeeId;//input variable
	
	//Method to set the employee id number.
	public static void setEmployeeId(int Id) {
		
		employeeId=Id;
		
	}
	
	//Method to access the employee id.
	public static int getEmployeeId() {
		
		return employeeId;
		
	}
	
	//Displays the main page menu which as two option.
	public void MainPage() {
		
		//Print statements 
		System.out.println("---------------------------------------------------");
		
		System.out.printf("%35s%n","Welcome to UCM");
		
		System.out.printf("%s%n%n","---------------------------------------------------");
		
		//System.out.printf("%n%n%n%n%n");
		
		System.out.printf("%30s%n","Main Menu");
		
		System.out.printf("%n%s%n%s%n", "1. Employee Payroll"
				,"2. Exit");
		
		//Scanner for user input.
		try (Scanner userInput = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter the option from main menu.
			System.out.printf("%n%n%s","Please enter the option from the above Main Menu: ");
			
			int input1=userInput.nextInt();
			
			//while statement which is used to validate the input statement.
			while(input1>2) {
				
				//Print statement that promotes the user to enter the option from main menu if wrong input is entered.
				System.out.printf("%s", "Please enter the valid input: ");
				
				input1=userInput.nextInt();
				
			}
			
			//if conditions to validate the user input.
			if(input1==1) {
				
				//if user input is 1 will navigate to Authentication method.
				Authentication();
				
			}else {
				
				//if user input is 2 the program will end.
				System.out.printf("%s", "Thank you");
				
			}//end of if
		
		}
		
	}//end of main page method
	
	//Validates the user id and password for the login. 
	public void Authentication() {
		
		    @SuppressWarnings("resource")
		    
			Scanner input = new Scanner(System.in);
		    
		    //Print statement that promotes the user to enter the user id.
		    System.out.printf("%s", "Enter 4 digit UserID: ");
		    
		   int authenticationInput1=input.nextInt();
		    
		   //Print statement that promotes the user to enter the password
		    System.out.printf("%s", "Enter Password: ");
		    
		    int authenticationInput2=input.nextInt();
		    
		    System.out.printf("%n%s%n"," ");
		    
		  
		    try
	        {
	            
		    	Class.forName("com.mysql.cj.jdbc.Driver");

			      // database variables
			    final String url = "jdbc:mysql:///PayrollProject";
			    
			      final String user = "root";
			      
			      final String password = "Nine*1=9";

			      // establish the connection
			      Connection con = DriverManager.getConnection(url, user, password);
			      
			      Statement stmt = con.createStatement();
		    	
	            // SELECT query to validate the password entered.
		    	String q1="Select E_Password from Authentication where E_ID='"+authenticationInput1+"'";
		    	
		    	//statement to execute the query.
	            ResultSet rs = stmt.executeQuery(q1);
	            
	            //if statement to validate the database results.
	            if (rs.next()){
	               
	            	//if statement to validate the password entered.
	            	if(rs.getInt(1)==authenticationInput2) {
	            		
	            		//if the password entered is correct then will set the employee id.
	            		setEmployeeId(authenticationInput1);
	            		
	            		//will navigate to payroll page.
	            		PayrollPage(authenticationInput1);
	            		
	            	}
	            	
	            	//if the password entered does not match with the database then the error message will be displayed,
	            	//and will navigate back to main page.
	            	else {
	            		
	            		System.out.printf("%n%n%s%n%n","***********Incorrect Password**********");
	            		
	            		MainPage();
	            	}

	            }
	            
	            //if the user id entered does not match with the database then the error message will be displayed,
            	//and will navigate back to main page.
	            else{
	            	
	                System.out.printf("%n%n%s%n%n","User details not found. Incorrect UserID");
	                
	                MainPage();
	                
	            }
	            
	            //close database connection
	            con.close();
	            
	        }//end try 
	        catch(Exception e){
	        	
	            System.out.println(e);
	            
	        }//end catch exception
		    
	}//end of authentication method

	//Displays the payroll page menu and promotes the user to enter an option from it.
	public void PayrollPage(int id) {
		
		//print statements
		System.out.println("---------------------------------------------------");
		
		System.out.printf("%35s%n","Welcome to UCM Employee Payroll");
		
		System.out.printf("%s%n%n","---------------------------------------------------");
		
		//System.out.printf("%n%n%n%n%n");
		System.out.printf("%s%n","1. My Dashboard");
		
		System.out.printf("%s%n","2. Search Employee");
		
		System.out.printf("%s%n%s%n%s%n%s%n%s%n", "3. Employee Advanced Search","4. Salary Details","5. Edit Personal Details"
				,"6. Change Password"
				,"7. Logout");
		
		int empId=id;
		
		try (Scanner userInput = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter the option from payroll page menu.
			System.out.printf("%n%n%s","Please select the option from the above: ");
			
			int payrollInput1=userInput.nextInt();
			
			//while statement to validate the user input.
			while(payrollInput1>7 || payrollInput1<1) {
				
				//Print statement that promotes the user if enter input is not correct.
				System.out.printf("%s", "Please enter the valid input: ");
				
				payrollInput1=userInput.nextInt();
				
			}
			
			//if statements
			if(payrollInput1==1) { //if user input is 1 will navigate to employee dashboard method.
				
				EmployeeDashboard(empId);
				
			}else if(payrollInput1==2) { //if user input is 2 will navigate to search employee method.
				
				EmployeeMethods employeeMethods1 = new EmployeeMethods();
				
				employeeMethods1.SearchEmployee();
				
			}else if(payrollInput1==3) {//if user input is 3 will navigate to advanced search method.
				
				EmployeeMethods employeeMethods2= new EmployeeMethods();
				
				employeeMethods2.AdvancedSearch();
				
			}else if(payrollInput1==4) { //if user input is 4 will navigate to employee salary method.
				
				//MainPage();
				
				EmployeeSalary(empId);
				
			}else if(payrollInput1==5) { //if user input is 5 will navigate to edit personal details method.
				
				EditPersonalDetails(empId);
				
			}else if(payrollInput1==6) {//if user input is 6 will navigate to change password method.
				ChangePassword(empId);
			}
			else { //if user input is 7 will navigate to main page
				
				MainPage();
				//System.out.printf("%s", "Thank you");
				
			}
		}
	}//end of payroll page.
	
	//Calculate and displays the employee monthly salary based on its role and employee type
	public void EmployeeSalary(int id) {
		
		//output variables 
		float basicSalary;
		
		float houseRentAllowance;
		
		float medicalAllowance;
		
		float transportAllowance;
		
		float performanceBonous;
		
		float bonous;
		
		float allowances;
		
		float totalSalary;
		
		float totalBonous;
		
		//Print Statements for the outline 
		System.out.println("-----------------------------------------------------------------");
		
		System.out.printf("%35s%n","Employee Pay Slip");
		
		System.out.printf("%s%n%n","-------------------------------------------------------------");
		
		try
        {
			
     
	    	Class.forName("com.mysql.cj.jdbc.Driver");

		      // variables
		    final String url = "jdbc:mysql:///PayrollProject";
		    
		      final String user = "root";
		      
		      final String password = "Nine*1=9";

		      // establish the connection
		      Connection con = DriverManager.getConnection(url, user, password);
		      
		      Statement stmt = con.createStatement();
	 
	    	//System.out.println(getEmployeeId());
		      
	    	 // SELECT query to extract the employee details.
	    	String q1="Select * from Employee where E_ID='"+getEmployeeId()+"'";
	    	
	    	//execute statement.
            ResultSet rs = stmt.executeQuery(q1);
            
            //if statement the to validate the database results.
	    	if(rs.next()) {
	    		
//	    		System.out.printf("%n%s%15d%n%s%15s%n%s%16s%n%s%20s%n%s%15s%n%s%16s%n%s%21d%n%s%33s%n","Employee Id: ",rs.getInt(1),
//	    		"First Name: ",rs.getString(2),"Last Name: ",rs.getString(3),"Designation: ",rs.getString(4),
//	    		"Employement Type: ",rs.getString(5),"Hourley Pay: ",rs.getString(6),"Contact No.: ",rs.getLong(7),
//	    		"E-Mail: ",rs.getString(8)); Full-Time
	    		
	    		//output print statements
	    		System.out.printf("%-15s%-10d%-15s%-10s%n","Employee Id: ",rs.getInt(1),"Designation: ",rs.getString(4) );
	    		
	    		System.out.printf("%-15s%-10s%-15s%-10d%n","Firstname: ",rs.getString(2),"Phone: ",rs.getLong(7) );
	    		
	    		System.out.printf("%-15s%-10s%-15s%-10s%n","Lastname: ",rs.getString(3),"Email: ",rs.getString(8) );
	    		
	    		System.out.printf("%s%n%n%n","--------------------------------------------------------------------" );
	    		
	    		
	    		String temp=rs.getString(5);
	    		
	    		//if condition to validate the employee type.
	    		if(temp.equals("Full-Time")) {
	    			
	    			basicSalary=rs.getInt(6)*22*6;//calculations for a period of month
	    			
	    			houseRentAllowance=(basicSalary*30)/100; //30% of basic salary
	    			
	    			medicalAllowance=(basicSalary*20)/100; //20% of basic salary
	    			
	    			transportAllowance=(basicSalary*20)/100; //20% of basic salary
	    			
	    			performanceBonous=(basicSalary*10)/100; //10% of basic salary
	    			
	    			bonous=(basicSalary*5)/100; //5% of basic salary
	    			
	    			allowances=houseRentAllowance+medicalAllowance+transportAllowance; //sum of allowances
	    			
	    			totalBonous=performanceBonous+bonous; //sum of bonus.
	    			
	    			totalSalary=allowances+totalBonous+basicSalary; //total salary sum
	    			
	    			//output print statements
	    			System.out.printf("%-25s%-20.2f%n","Basic Salary: ",basicSalary );
	    			
	    			System.out.printf("%-25s%-20.2f%n","House rent allowance: ",houseRentAllowance );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Medical Allowance: ",medicalAllowance );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Transport Allowance ",transportAllowance );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Performance Bonus: ",performanceBonous );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Bonous: ",bonous );
	    			
	    			System.out.println(" ");
	    			
	    			System.out.println("------------------------------------------------------" );
	    			
	    			System.out.println("------------------------------------------------------" );
	    			
	    			System.out.println(" ");
	    			
	    			System.out.printf("%-25s%-7.2f%n","Total Allowance: ",allowances );
	    			
	    			System.out.printf("%-25s%-7.2f%n","Total Bonous: ",totalBonous );
	    			
	    			System.out.printf("%-25s%-7.2f%n","Total Salary: ",totalSalary );
	    			
	    			System.out.println("------------------------------------------------------" );
	    			
	    			
	    		}else { //if employee type is contract.
	    			
	    			basicSalary=rs.getInt(6)*22*6; //calculations for a period of month
	    			
	    			houseRentAllowance=(basicSalary*30)/100; //30% of basic salary
	    			
	    			medicalAllowance=(basicSalary*20)/100;//20% of basic salary
	    			
	    			transportAllowance=(basicSalary*20)/100;//20% of basic salary
	    			
	    			performanceBonous=(basicSalary*0);//0% of basic salary
	    			
	    			bonous=(basicSalary*0);//0% of basic salary
	    			
	    			allowances=houseRentAllowance+medicalAllowance+transportAllowance; //sum of allowances
	    			
	    			totalBonous=performanceBonous+bonous; //sum of bonus
	    			
	    			totalSalary=allowances+totalBonous+basicSalary; //total salary
	    			
	    			//output print statements
	    			System.out.printf("%-25s%-20.2f%n","Basic Salary: ",basicSalary );
	    			
	    			System.out.printf("%-25s%-20.2f%n","House rent allowance: ",houseRentAllowance );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Medical Allowance: ",medicalAllowance );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Transport Allowance ",transportAllowance );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Performance Bonus: ",performanceBonous );
	    			
	    			System.out.printf("%-25s%-20.2f%n","Bonous: ",bonous );
	    			
	    			System.out.println(" ");
	    			
	    			System.out.println("------------------------------------------------------" );
	    			
	    			System.out.println("------------------------------------------------------" );
	    			
	    			System.out.println(" ");
	    			
	    			System.out.printf("%-25s%-7.2f%n","Total Allowance: ",allowances );
	    			
	    			System.out.printf("%-25s%-7.2f%n","Total Bonous: ",totalBonous );
	    			
	    			System.out.printf("%-25s%-7.2f%n","Total Salary: ",totalSalary );
	    			
	    			System.out.println("------------------------------------------------------" );
	    			
	    		}
	    			
	    		
	    	}else {
	    		
	    		System.out.println("Database error occured");
	    		
	    	}
	    	
	        con.close(); //close database connection
	        
	        }
		
	        catch(Exception e)
		
	        {
	        	
	            System.out.println(e);
	            
	        }
		
		
		try (Scanner input = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter the option to navigate to payroll page or logout.
			System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
			
			int input1=input.nextInt();
			
			//while loop tp validate the input value.
			while(input1>1) {
				
				//Print statement that promotes the user to enter the correct option.
				System.out.print("Please enter a valid input for Payroll page '0' and '1' for Logout");
				
				//System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
				
				input1=input.nextInt();
				
			}
			
			if(input1==0) { //if input value is 0 will navigate to payroll page.
				
				PayrollPage(getEmployeeId());
				
			}else { //if input is 1 will logout and navigate to main page.
				
				MainPage();
				
			}
			
		}
		
		
	}// end of employee salary
	
	//Promotes the user to change the password.
	public void ChangePassword(int id) {
		
		try
        {		
     
	    	Class.forName("com.mysql.cj.jdbc.Driver");

		      // variables
		    final String url = "jdbc:mysql:///PayrollProject";
		    
		      final String user = "root";
		      
		      final String password = "Nine*1=9";

		      // establish the connection
		      Connection con = DriverManager.getConnection(url, user, password);
		      
		      Statement stmt = con.createStatement();
		     	
	    	//System.out.println(getEmployeeId());
	    	 // SELECT query to validate the password.
	    	String q1="Select E_Password from Authentication where E_ID='"+getEmployeeId()+"'";
	    	
	    	//statement to execute the query.
            ResultSet rs = stmt.executeQuery(q1);
            
            @SuppressWarnings("resource")
			Scanner inputPassword = new Scanner(System.in);
            	
            	//Print statement that promotes the user to enter the old password.
				System.out.print("Please enter the old password: ");
				
				int oldPassword=inputPassword.nextInt();
				
				System.out.println(" ");
				
				if(rs.next()) {
					
					//if condition to validate the old password. 
					if(oldPassword==rs.getInt(1)) {
						
						//Print statement that promotes the user to enter the new password.
						System.out.print("Please enter the new password: ");
						
						int newPassword=inputPassword.nextInt();
				        
				        System.out.println(" ");
				        
				        //update query to update the new password.
				        q1="UPDATE Authentication SET E_Password='"+newPassword+"' where E_ID='"+getEmployeeId()+"'";
				    	
				        //update statement
				        int rs1 = stmt.executeUpdate(q1);
				        
				        //Password change success message
				        System.out.println("Password changed Succesfully");
				        
						
					}else {
						
						//error message for incorrect password.
						System.out.println("Incorrect Password");
							
					}
					
					
				}else {
					
					System.out.println("Database error occured");
					
				}
			
            
            con.close(); //close database connection
	        
	        }
		
	        catch(Exception e)
		
	        {
	        	
	            System.out.println(e);
	            
	        }
		
		
		try (Scanner input = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter the option.
			System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
		
			int input1=input.nextInt();
			
			//while statement to validate input
			while(input1>1) {
				
				////Print statement that promotes the user to enter correct value.
				System.out.print("Please enter a valid input for Payroll page '0' and '1' for Logout");
				
				//System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
				
				input1=input.nextInt();
				
			}
			
			if(input1==0) { //if input value is 0 navigate to payroll page 
				
				PayrollPage(getEmployeeId());
				
			}else { //if input value is 1 navigate to main page.
				
				MainPage();
				
			}
			
		}	      
		
	}//end of change password.
	
	//Promotes the user to edit the personal details.
	public void EditPersonalDetails(int id) {
		
		try
        {
			
     
	    	Class.forName("com.mysql.cj.jdbc.Driver");

		      // variables
		    final String url = "jdbc:mysql:///PayrollProject";
		    
		      final String user = "root";
		      
		      final String password = "Nine*1=9";

		      // establish the connection
		      Connection con = DriverManager.getConnection(url, user, password);
		      
		      Statement stmt = con.createStatement();
		      
	    	//System.out.println(getEmployeeId());
	    	 // SELECT query to extract the employee details.
	    	String q1="Select * from Employee where E_ID='"+getEmployeeId()+"'";
	    	
	    	//execute statement
            ResultSet rs = stmt.executeQuery(q1);
            
            int rs1; //output variable for query results.
            
            //if statement to validate the query results
	    	if(rs.next()) {
	    		
	    		//output print statements.
	    		System.out.println("-----------------------------------------------------------------" );
	    		
	    		System.out.printf("%40s%n","Personal Details" );
	    		
	    		System.out.println("-----------------------------------------------------------------" );
	    		
	    		System.out.printf("%-25s%-25s%n%-25s%-25s%n","1. Firstname: ",rs.getString(2),"2. Lastname: ",rs.getString(3) );
	    		
	    		System.out.printf("%-25s%-25d%n%-25s%-25s%n","3. Contact no. : ",rs.getLong(7),"4. E-Mail: ",rs.getString(8) );
	    		
	    		@SuppressWarnings("resource")
				Scanner editInput=new Scanner(System.in);
	    		
	    		@SuppressWarnings("resource")
				Scanner inputFeilds= new Scanner(System.in);
	    		
	    		@SuppressWarnings("resource")
				Scanner inputFeilds1= new Scanner(System.in);
	    		
	    		System.out.println("");
	    		
	    		//Print statement that promotes the user to enter the option from above.
	    		System.out.println("Please select an feild from which wants to be changed ");
	    		
	    		System.out.println("-----------------------OR--------------------------- ");
	    		
	    		System.out.println("Enter 0 to go back");
    			
    			
	    		int editInput1=editInput.nextInt();
	    		
	    		System.out.println("");
	    		
	    		//while loop to validate the input value.
	    		while(editInput1!=0) {
	    			
	    			if(editInput1==1) { //if input is 1 will edit first name.
	    				
	    				//Print statement that promotes the user to enter first name.
	    				System.out.print("Enter the Firstname: ");
	    				
	    				String firstname=inputFeilds.nextLine();
	    				
	    				//SQl query to update the first name
	    				q1="UPDATE Employee SET E_FirstName='"+firstname+"' where E_ID='"+getEmployeeId()+"'";
	    		    	
	    				//query update statement
	    	            rs1 = stmt.executeUpdate(q1);
	    		
	    					
	    			}else if(editInput1==2){ //if input is 2 will edit last name.
	    				
	    				//Print statement that promotes the user to enter last name.
	    				System.out.print("Enter the Lastname: ");
	    				
	    				String lastname=inputFeilds.nextLine();
	    				
	    				//SQL query to update the last name.
	    				q1="UPDATE Employee SET E_LastName='"+lastname+"' where E_ID='"+getEmployeeId()+"'";
	    		    	
	    				//update query function
	    				rs1 = stmt.executeUpdate(q1);
	    				 
	    			}else if(editInput1==3) { //if input is 3 will edit contact.
	    				
	    				//Print statement that promotes the user to enter contact.
	    				System.out.print("Enter the Contact: ");
	    				
	    				Long contact=inputFeilds1.nextLong();
	    				
	    				//SQl query to update the contact 
	    				q1="UPDATE Employee SET E_Phone='"+contact+"' where E_ID='"+getEmployeeId()+"'";
	    		    	
	    				//update query function.
	    				rs1 = stmt.executeUpdate(q1);
	    				 
	    				
	    			}else if(editInput1==4){ //if input is 4 will edit e-mail.
	    				
	    				//Print statement that promotes the user to enter e-mail.
	    				System.out.print("Enter the Email: ");
	    				
	    				String email=inputFeilds.nextLine();
	    				
	    				//SQL query to update the email address.
	    				q1="UPDATE Employee SET E_Email='"+email+"' where E_ID='"+getEmployeeId()+"'";
	    		    	
	    				rs1 = stmt.executeUpdate(q1);
	    				
	    			}else { //else gives an error message.
	    				
	    				System.out.println("Please enter an valid input ");
		    			
		    			
	    			}
	    	         
	    			//Print statement that promotes the user to enter the option .
	    			System.out.print("Enter 0 to Continue OR Select any other feild to edit: ");
	    			
	    			editInput1=editInput.nextInt();	
	    			
	    			System.out.println("");
	    			
	    			
	    		}
	    		
	    		System.out.println(" ");
	    		
	    		System.out.println(" ");
	    		
	    		//success message.
	    		System.out.println("-------------------Updated Dashboard--------------------------- ");
	    		
	    		//EmployeeDashboard(getEmployeeId());
	    		
	    		//SQl select query to extract employee new details.
	    		q1="Select * from Employee where E_ID='"+getEmployeeId()+"'";
		    	
	            rs = stmt.executeQuery(q1);
	            
	            //validate the database results.
		    	if(rs.next()) {
		    		
		    		//output print statements.
		    		System.out.printf("%n%-25s%-25d%n%-25s%-25s%n%-25s%-25s%n%-25s%-25s%n%-25s%-25s%n%-25s%-25s%n%-25s%-25d%n%-25s%-25s%n","Employee Id: ",rs.getInt(1),
		    		"First Name: ",rs.getString(2),"Last Name: ",rs.getString(3),"Designation: ",rs.getString(4),
		    		"Employement Type: ",rs.getString(5),"Hourley Pay: ",rs.getString(6),"Contact No.: ",rs.getLong(7),
		    		"E-Mail: ",rs.getString(8));
		    		
		    	}else {
		    		
		    		System.out.println("Database error occured");
		    		
		    	}
	    		
	    	}else {
	    		
	    		System.out.println("Database error Occured");
	    		
	    	}
	    	
	        con.close(); //close database connection
	        
	        }
		
	        catch(Exception e)
		
	        {
	        	
	            System.out.println(e);
	            
	        }
		
		
		try (Scanner input = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter the options.
			System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
			
			int input1=input.nextInt();
			
			//while statement to validate the input 
			while(input1>1) {
				
				//Print statement that promotes the user to enter the correct value.
				System.out.print("Please enter a valid input for Payroll page '0' and '1' for Logout");
				
				//System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
				
				input1=input.nextInt();
				
			}
			
			if(input1==0) { //if value is 0 navigate to payroll page.
				
				PayrollPage(getEmployeeId());
				
			
			}else {//else navigate to main page.
				
				MainPage();
				
			}
			
		}
		
	}//end of edit personal details
	
	// Displays the employee details.
	public void EmployeeDashboard(int id) {
		
		try
        {
			
	    	Class.forName("com.mysql.cj.jdbc.Driver");

		      // variables
		    final String url = "jdbc:mysql:///PayrollProject";
		    
		      final String user = "root";
		      
		      final String password = "Nine*1=9";

		      // establish the connection
		      Connection con = DriverManager.getConnection(url, user, password);
		      
		      Statement stmt = con.createStatement();
		      
		      
		      
	    	//System.out.println(getEmployeeId());
	    	 // SELECT query to extract the employee details from employee table.
	    	String q1="Select * from Employee where E_ID='"+getEmployeeId()+"'";
	    	
            ResultSet rs = stmt.executeQuery(q1);
            
            //validates the database results.
	    	if(rs.next()) {
	    		
	    		//output print statement.
	    		System.out.printf("%n%-25s%-25d%n%-25s%-25s%n%-25s%-25s%n%-25s%-25s%n%-25s%-25s%n%-25s%-25s%n%-25s%-25d%n%-25s%-25s%n","Employee Id: ",rs.getInt(1),
	    		"First Name: ",rs.getString(2),"Last Name: ",rs.getString(3),"Designation: ",rs.getString(4),
	    		"Employement Type: ",rs.getString(5),"Hourley Pay: ",rs.getString(6),"Contact No.: ",rs.getLong(7),
	    		"E-Mail: ",rs.getString(8));
	    		
	    	}else {
	    		
	    		System.out.println("Database error occured");
	    		
	    	}
	    	
	        con.close();
	        
	        }
		
	        catch(Exception e)
		
	        {
	        	
	            System.out.println(e);
	            
	        }
		
		
		try (Scanner input = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter the option.
			System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
			
			int input1=input.nextInt();
			
			//while statement to validate input
			while(input1>1) {
				
				////Print statement that promotes the user to enter correct value.
				System.out.print("Please enter a valid input for Payroll page '0' and '1' for Logout");
				
				//System.out.printf("%n%n%s%n%20s%n%s%n","Enter 0 to go back to Payroll page", "OR","Enter 1 to logout");
				
				input1=input.nextInt();
				
			}
			
			if(input1==0) { //if input value is 0 navigate to payroll page 
				
				PayrollPage(getEmployeeId());
				
			}else { //if input value is 1 navigate to main page.
				
				MainPage();
				
			}
			
		}
		

	}//end of employee dashboard.	
		
}//end of pages class.
