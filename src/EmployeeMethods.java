import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeMethods {
	
	Pages pages1 = new Pages();
	
	//input variables.
	private int employeeId;
	
	private String firstname;
	
	private String lastname;
	
	private String designation;
	
	private String employeeType;
	
	private Long phoneNumber;
	
	private String email;
	
	//getter and setter methods for input variables.
	public void SetEmployeeId(int id) {
		
		this.employeeId=id;
		
	}
	
	public void SetFirstname(String fname) {
		
		this.firstname=fname;
		
	}
	
	
	public void SetLastname(String lname) {
		
		this.lastname=lname;
		
	}
	
	
	public void SetDesignation(String role) {
		
		this.designation=role;
		
	}
	
	
	public void SetEmployeeType(String type) {
		
		this.employeeType=type;
		
	}
	
	
	public void SetPhoneNumber(long num) {
		
		this.phoneNumber=num;
		
	}
	
	
	public void setemail(String email) {
		
		this.email=email;
		
	}
	
	
	public int GetEmployeeId() {
		
		return employeeId;
		
	}
	
	
	public String GetFirstname() {
		
		return firstname;
		
	}
	
	
	public String GetLastname() {
		
		return lastname;
		
	}
	
	
	public String GetDesignation() {
		
		return designation;
		
	}
	
	
	public String GetEmployeeType() {
		
		return employeeType;
		
	}
	
	
	public long GetPhoneNumber() {
		
		return phoneNumber;
		
	}
	
	
	public String Getemail() {
		
		return email;
		
	}
	
	//employee search by the means of employee id.
	public void SearchEmployee(){
		
		try (Scanner input = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter employee id.
			System.out.print("Enter the Employee ID :"); 
			
			int input1=input.nextInt();
			
			SetEmployeeId(input1);
			
			
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
				String q1="Select * from Employee where E_ID='"+GetEmployeeId()+"'";
				
			    ResultSet rs = stmt.executeQuery(q1);
			    
			    int count=1;
			    
			    //if condition to validated the database results.
				if(rs.next()) {
					
					//output print statements.
					System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","S.No","Employee Id","First Name","Last Name","Desigantion"
	    					  ,"Employee Type","Contact No","Email");
					
					System.out.printf("%-20d%-20d%-20s%-20s%-20s%-20s%-20d%-20s%n",count,rs.getInt(1),rs.getString(2),rs.getString(3),
	    					  rs.getString(4),rs.getString(5),rs.getLong(7),rs.getString(8));
					
//					System.out.printf("%n%s%15d%n%s%15s%n%s%16s%n%s%20s%n%s%15s%n%s%21d%n%s%33s%n","Employee Id: ",rs.getInt(1),
//					"First Name: ",rs.getString(2),"Last Name: ",rs.getString(3),"Designation: ",rs.getString(4),
//					"Employement Type: ",rs.getString(5),"Contact No.: ",rs.getLong(7),
//					"E-Mail: ",rs.getString(8));
					
				}else {
					
					//output error messages.
					System.out.println("Employee Details Not Found");
					
					//navigate to payroll page
					pages1.PayrollPage(Pages.getEmployeeId());
					
				}
				
			    con.close();//close database connection
			    
			    }
			
			    catch(Exception e)
			
			    {
			    	
			        System.out.println(e);
			        
			    }
			
			//Print statement that promotes the user to enter option.
			System.out.print("Enter 0 to go back: "); 
			
			int input2=input.nextInt();
			
			//while statement to validate the input option.
			while(input2!=0) {
				
				//Print statement that promotes the user to enter correct option.
				System.out.print("Enter 0 to go back: ");
				
				input2=input.nextInt();
				
			}
			//navigates to payroll page.
			pages1.PayrollPage(Pages.getEmployeeId());
			
		}
		
		
	}//end of employee search.
	
	//Employee advanced search
	public void AdvancedSearch() {
		
		Pages pages1 = new Pages();
		
		//print statements to display menu.
		System.out.printf("%20s","Advanced search Options");
		
		System.out.printf("%n%n%s%n", "1. Search By Firstname");
		
		System.out.printf("%s%n", "2. Search By Lastname");
		
		System.out.printf("%s%n", "3. Search By Designation");
		
		System.out.printf("%s%n", "4. Search By Employee Type");
		
		System.out.printf("%s%n", "5. Back");
		
		try (Scanner input = new Scanner(System.in)) {
			
			//Print statement that promotes the user to enter option from menu.
			System.out.print("Please select the feild to search : "); 
			
			int input1=input.nextInt();
			
			//while loop to validate input.
			while (input1 > 5 || input1<0) {
				
				//Print statement that promotes the user to enter correct option.
				System.out.print("Please select the option from the above menu: "); 
				
				input1=input.nextInt();
				
			}
			
			try
			{
			    
				Class.forName("com.mysql.cj.jdbc.Driver");

			      // database input variables
			    final String url = "jdbc:mysql:///PayrollProject";
			    
			      final String user = "root";
			      
			      final String password = "Nine*1=9";

			      // establish the connection
			      Connection con = DriverManager.getConnection(url, user, password);
			      
			      Statement stmt = con.createStatement();
			
			
			      if(input1==1) { //if input is 1 search by employee first name.
		    	
			    	  //Print statement that promotes the user to enter first name of employee.
			    	  System.out.print("Please enter the firstname of the employee: "); 
		    	
			    	  String input2=input.next();
			    	  
			    	  SetFirstname(input2);
			    	  
			    	  //SQl select query to extract details of employee by first name.
			    	  String q1 ="Select * from Employee where E_FirstName='"+GetFirstname()+"'";
			    	  
			    	  ResultSet rs = stmt.executeQuery(q1);
			    	  
			    	  //Output print statement
			    	  System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","S.No","Employee Id","First Name","Last Name","Desigantion"
	    					  ,"Employee Type","Contact No","Email");
			    	  
			    	  int count=1;
		    		  
			    	  //while statement to validate database results
		    		  while(rs.next()){
		    			  
		    			  //output statements
		    			  System.out.printf("%-20d%-20d%-20s%-20s%-20s%-20s%-20d%-20s%n",count,rs.getInt(1),rs.getString(2),rs.getString(3),
		    					  rs.getString(4),rs.getString(5),rs.getLong(7),rs.getString(8));
		    			  
		    			  
//			    	System.out.printf("%n%s%15d%n%s%15s%n%s%16s%n%s%20s%n%s%15s%n%s%21d%n%s%33s%n%n%n%n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"Contact No.: ",rs.getLong(7),rs.getString(8));
			    	  		
			    	  	count++;
			    	  	}
			    		  
			    		  
			    	if(count>1) { 
			    		
			    	  }else {
			    		  
			    		  //output message if details not found
			    		  System.out.println("Employee Details Not Found");
						
			    		  //navigate to payroll page 
						  pages1.PayrollPage(Pages.getEmployeeId());
			    		  
			    	  }
			    	  		    	
			      }else if (input1==2) { //input is 2 search by employee last name.
			    	  
			    	///Print statement that promotes the user to enter last name of employee.
			    	  System.out.print("Please enter the lastname of the employee: "); 
				    	
			    	  String input2=input.next();
			    	  
			    	  SetLastname(input2);
			    	  
			    	  //SQl select query to extract details of employee by last name.
			    	  String q1 ="Select * from Employee where E_LastName='"+GetLastname()+"'";
			    	  
			    	  ResultSet rs = stmt.executeQuery(q1);
			    	  
			    	  //Output print statement
			    	  System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","S.No","Employee Id","First Name","Last Name","Desigantion"
	    					  ,"Employee Type","Contact No","Email");
			    	  
			    	  int count=1;
		    		  
			    	//while statement to validate database results
		    		  while(rs.next()){
		    			  
		    			//output statements
		    			  System.out.printf("%-20d%-20d%-20s%-20s%-20s%-20s%-20d%-20s%n",count,rs.getInt(1),rs.getString(2),rs.getString(3),
		    					  rs.getString(4),rs.getString(5),rs.getLong(7),rs.getString(8));
		    			  
		    			  
//			    	System.out.printf("%n%s%15d%n%s%15s%n%s%16s%n%s%20s%n%s%15s%n%s%21d%n%s%33s%n%n%n%n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"Contact No.: ",rs.getLong(7),rs.getString(8));
			    	  		
			    	  	count++;
			    	  	}
			    		  
			    		  
			    	if(count>1) {
			    		
			    	  }else {
			    		  
			    		  //output message if details not found
			    		  System.out.println("Employee Details Not Found");
							
			    		  //navigate to payroll page 
						  pages1.PayrollPage(Pages.getEmployeeId());
			    		  			    		  
			    	  }
			    	  
			      }else if(input1==3) {
			    	  
			    	  //Print statement that promotes the user to enter designation of employee.
			    	  System.out.print("Please enter the designation of the employee: "); 
				    	
			    	  String input2=input.next();
			    	  
			    	  SetDesignation(input2);
			    	  
			    	//SQl select query to extract details of employee by designation.
			    	  String q1 ="Select * from Employee where E_Designation='"+GetDesignation()+"'";
			    	  
			    	  ResultSet rs = stmt.executeQuery(q1);
			    	  
			    	  System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","S.No","Employee Id","First Name","Last Name","Desigantion"
	    					  ,"Employee Type","Contact No","Email");
			    	  
			    	  int count=1;
		    		  
		    		  while(rs.next()){
		    			  
		    			  System.out.printf("%-20d%-20d%-20s%-20s%-20s%-20s%-20d%-20s%n",count,rs.getInt(1),rs.getString(2),rs.getString(3),
		    					  rs.getString(4),rs.getString(5),rs.getLong(7),rs.getString(8));
		    			  
		    			  
//			    	System.out.printf("%n%s%15d%n%s%15s%n%s%16s%n%s%20s%n%s%15s%n%s%21d%n%s%33s%n%n%n%n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"Contact No.: ",rs.getLong(7),rs.getString(8));
			    	  		
			    	  	count++;
			    	  	}
			    		  
			    		  
			    	if(count>1) {
			    		
			    	  }else {
			    		  
			    		  System.out.println("Employee Details Not Found");
							
						  pages1.PayrollPage(Pages.getEmployeeId());
			    		  
			    		  
			    	  }
			    	  
			      }else if(input1==4) {
			    	  
			    	  //Print statement that promotes the user to enter employee type.
			    	  System.out.print("Please enter employee type: "); 
				    	
			    	  String input2=input.next();
			    	  
			    	  SetEmployeeType(input2);
			    	  
			    	  String q1 ="Select * from Employee where E_Type='"+GetEmployeeType()+"'";
			    	  
			    	  ResultSet rs = stmt.executeQuery(q1);
			    	  
			    	  System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","S.No","Employee Id","First Name","Last Name","Desigantion"
	    					  ,"Employee Type","Contact No","Email");
			    	  
			    	  int count=1;
		    		  
		    		  while(rs.next()){
		    			  
		    			  System.out.printf("%-20d%-20d%-20s%-20s%-20s%-20s%-20d%-20s%n",count,rs.getInt(1),rs.getString(2),rs.getString(3),
		    					  rs.getString(4),rs.getString(5),rs.getLong(7),rs.getString(8));
		    			  
		    			  
//			    	System.out.printf("%n%s%15d%n%s%15s%n%s%16s%n%s%20s%n%s%15s%n%s%21d%n%s%33s%n%n%n%n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),"Contact No.: ",rs.getLong(7),rs.getString(8));
			    	  		
			    	  	count++;
			    	  	}
			    		  
			    		  
			    	if(count>1) {
			    		
			    	  }else {
			    		  
			    		  System.out.println("Employee Details Not Found");
							
						  pages1.PayrollPage(Pages.getEmployeeId());
			    		  
			    		  
			    	  }

			      }else {
			    	  
			    	  pages1.PayrollPage(Pages.getEmployeeId());
			    	  
			    	  
			      }
			      
			      con.close();
				    
		    }
		
		    catch(Exception e)
		
		    {
		    	
		        System.out.println(e);
		        
		    }
			
			System.out.print("Enter 0 to go back: "); 
			
			int input2=input.nextInt();
			
			
			while(input2!=0) {
				
				System.out.print("Enter 0 to go back: ");
				
				input2=input.nextInt();
				
			}
			
			pages1.PayrollPage(Pages.getEmployeeId());
				
			
		}
		
	}

}
