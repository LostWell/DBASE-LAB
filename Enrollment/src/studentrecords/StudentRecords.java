package studentrecords;

import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentRecords {
     static Scanner scn = new Scanner(System.in); //--- we (Mitz) added this---//
     static DBController control; //---we (Mitz) added this---//

    // DBController Object
    static DBController controller = new DBController(); 
	
    // Database URL
    static final String DBASE_URL = "jdbc:mysql://localhost/records?autoReconnect=true&useSSL=false";
    
    // Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    
    //Scanner Object
	static Scanner kb = new Scanner(System.in);
	
	//Date Objects
	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	static Date date = new Date();
	
	public static void main(String[] args){
		control = new DBController(); //---we (Mitz) added this----//
		try {
		controller.dbaseConnect(DBASE_URL,USERNAME,PASSWORD);
		mainMenu();
		controller.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void mainMenu() {
		int choice = 0;
		do {
			System.out.println();
			System.out.println("----------"+dateFormat.format(date)+"----------");
			System.out.println("+----------------------------+");
	        System.out.println("|   M E N U  O P T I O N S   |");
	        System.out.println("+----------------------------+");
	        System.out.println("| 1. Accounts                |");
	        System.out.println("| 2. Subjects                |");
	        System.out.println("| 3. Classes                 |");
	        System.out.println("| 4. Enrollment              |");
	        System.out.println("| 5. Exit Program            |");
	        System.out.println("+----------------------------+");
	        do {
	        	System.out.print("Enter your choice: ");
		        try {
		        	choice = Integer.parseInt(kb.nextLine());
		        } catch (Exception e) {
		        	System.out.println("error: input a valid value...");
		        	System.out.print("Press enter key to continue...");
		        	kb.nextLine();
		        }
	        } while(choice < 1 || choice > 5);
	        
	        switch (choice){
	        	case 1:
	        		accountsMenu(choice);
	        		break;
	        		
	        	case 2:
	        		subjectsMenu(choice);
	        		break;
	        		
	        	case 3:
	        		classesMenu(choice);
	        		break;
	        		
	        	case 4:
	        		enrollmentMenu(choice);
	        		break;
	        }
	        
		} while(choice != 5);
        kb.close();
	}
	
	public static void accountsMenu(int choice){
		do {
			System.out.println();
			System.out.println("+----------------------------+");
	        System.out.println("|   M E N U  O P T I O N S   |");
	        System.out.println("+----------------------------+");
	        System.out.println("| 0. Back                    |");
	        System.out.println("| 1. Add Account             |");
	        System.out.println("| 2. Check Account           |");
	        System.out.println("| 3. Update Account          |");
	        System.out.println("| 4. Delete Account          |");
	        System.out.println("+----------------------------+");
	        do {
	        	System.out.print("Enter your choice: ");
		        try {
		        	choice = Integer.parseInt(kb.nextLine());
		        } catch (Exception e) {
		        	System.out.println("error: input a valid value...");
		        	System.out.print("Press enter key to continue...");
		        	kb.nextLine();
		        }
	        } while(choice < 0 || choice > 4);
	        
	        try {
	        	switch (choice){
		        	case 1:
		    	        //Prompt for input data
		    	        System.out.println("+----------------------------+");
		    	        System.out.println("|         Add Account        |");
		    	        System.out.println("+----------------------------+");
		    	        System.out.print("Enter ID number: ");
		    	        int idno = Integer.parseInt(kb.nextLine());
		    	        System.out.print("Enter last name: ");
		    	        String lname = kb.nextLine();
		    	        System.out.print("Enter first name: ");
		    	        String fname = kb.nextLine();
		    	        System.out.print("Enter middle initial: ");
		    	        String midInitial = kb.nextLine();
		    	        System.out.print("Enter gender: ");
		    	        String gender = kb.nextLine();
		    	        System.out.print("Enter contact number: ");
		    	        String contactNumber = kb.nextLine();
		    	        System.out.print("Enter email: ");
		    	        String email = kb.nextLine();
		    	        
		    	        //account creation
		        		controller.createAccount(idno, lname, fname, midInitial, gender, contactNumber, email);
		        		
		        		//prompt for finished process
		    	        System.out.println("-------Process Finished-------");
		    	        System.out.println("Press enter to continue...");
		    	        kb.nextLine();
		        		break;
		        		
		        	case 2:
		        		printStudents();
		        		break;
		        		
		        	case 3:
		        		//To-Do
		        		break;
		        		
		        	case 4:
		        		//To-Do
		        		break;
	        	}
	        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
	        	System.out.println("ID number already exists");
	        } catch (Exception e2) {
	        	e2.printStackTrace();
	        }
	        
		} while (choice != 0);
	}
	
	public static void subjectsMenu(int choice){
		do {
			System.out.println();
			System.out.println("+----------------------------+");
	        System.out.println("|   M E N U  O P T I O N S   |");
	        System.out.println("+----------------------------+");
	        System.out.println("| 0. Back                    |");
	        System.out.println("| 1. Add Subject             |");
	        System.out.println("| 2. Check Subject           |");
	        System.out.println("| 3. Update Subject          |");
	        System.out.println("| 4. Delete Subject          |");
	        System.out.println("+----------------------------+");
	        do {
	        	System.out.print("Enter your choice: ");
		        try {
		        	choice = Integer.parseInt(kb.nextLine());
		        } catch (Exception e) {
		        	System.out.println("error: input a valid value...");
		        	System.out.print("Press enter key to continue...");
		        	kb.nextLine();
		        }
	        } while(choice < 0 || choice > 4);
	        
	        try {
	        	switch (choice){
		        	case 1:
		        		//Prompt for input data
		    	        System.out.println("+----------------------------+");
		    	        System.out.println("|         Add Subject        |");
		    	        System.out.println("+----------------------------+");
		    	        System.out.print("Enter subject ID: ");
		    	        String id = kb.nextLine();
		    	        System.out.print("Enter subject title: ");
		    	        String title = kb.nextLine();
		    	        System.out.print("Enter number of units: ");
		    	        int units = Integer.parseInt(kb.nextLine());
		    	        
		    	        //subject creation
		        		controller.createSubject(id, title, units);
		        		
		        		//prompt for finished process
		    	        System.out.println("-------Process Finished-------");
		    	        System.out.println("Press enter to continue...");
		    	        kb.nextLine();
		        		break;
		        		
		        	case 2:
		        		printSubjects();
		        		break;
		        		
		        	case 3:
		        		//To-Do
		        		break;
		        		
		        	case 4:
		        		//To-Do
		        		break;
	        	}
	        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
	        	System.out.println("Subject ID already exists");
	        } catch (Exception e2) {
	        	e2.printStackTrace();
	        }
		} while (choice != 0);
		
	}
	
	public static void classesMenu(int choice){
		do {
		System.out.println();
		System.out.println("+----------------------------+");
	        System.out.println("|   M E N U  O P T I O N S   |");
	        System.out.println("+----------------------------+");
	        System.out.println("| 0. Back                    |");
	        System.out.println("| 1. Add Class               |");
	        System.out.println("| 2. Check Class             |");
	        System.out.println("| 3. Update Class            |");
	        System.out.println("| 4. Delete Class            |");
	        System.out.println("+----------------------------+");
	        do {
	        	System.out.print("Enter your choice: ");
		        try {
		        	choice = Integer.parseInt(kb.nextLine());
		        } catch (Exception e) {
		        	System.out.println("error: input a valid value...");
		        	System.out.print("Press enter key to continue...");
		        	kb.nextLine();
		        }
	        } while(choice < 0 || choice > 4);
	        try {
	        	switch (choice){
		        	case 1:
		        		//Prompt for input data
		    	        System.out.println("+----------------------------+");
		    	        System.out.println("|          Add Class         |");
		    	        System.out.println("+----------------------------+");
		    	        System.out.print("Enter class code: ");
		    	        String code = kb.nextLine();
		    	        System.out.print("Enter scheduled time: ");
		    	        String time = kb.nextLine();
		    	        System.out.print("Enter scheduled days: ");
		    	        String days = kb.nextLine();
		    	        System.out.print("Enter subject ID: ");
		    	        String id = kb.nextLine();
		    	        
		    	        //subject creation
		        		controller.createClass(code, time, days, id);
		        		
		        		//prompt for finished process
		    	        System.out.println("-------Process Finished-------");
		    	        System.out.println("Press enter to continue...");
		    	        kb.nextLine();
		        		break;
		        		
		        	case 2:
		        		printClass();
		        		break;
		        		
		        	case 3:
		        		//To-Do
		        		break;
		        		
		        	case 4:
		        		//To-Do
		        		break;
	        	}
	        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
	        	System.out.println("Class Code already exists");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} while (choice != 0);
	}
	
	public static void enrollmentMenu(int choice){
		do {
		System.out.println();
		System.out.println("+----------------------------+");
	        System.out.println("|   M E N U  O P T I O N S   |");
	        System.out.println("+----------------------------+");
	        System.out.println("| 0. Back                    |");
	        System.out.println("| 1. Enroll                  |");
	        System.out.println("| 2. Check Enrollment        |");
	        System.out.println("| 3. Update Info             |");
	        System.out.println("| 4. Unenroll                |");
	        System.out.println("+----------------------------+");
	        do {
	        	System.out.print("Enter your choice: ");
		        try {
		        	choice = Integer.parseInt(kb.nextLine());
		        } catch (Exception e) {
		        	System.out.println("error: input a valid value...");
		        	System.out.print("Press enter key to continue...");
		        	kb.nextLine();
		        }
	        } while(choice < 0 || choice > 4);
	        try {
	        	switch (choice){
		        	case 1:
		        		//Prompt for input data
		    	        System.out.println("+----------------------------+");
		    	        System.out.println("|           Enroll           |");
		    	        System.out.println("+----------------------------+");
		    	        System.out.print("Enter class code: ");
		    	        String code = kb.nextLine();
		    	        System.out.print("Enter ID number: ");
		    	        int idno = Integer.parseInt(kb.nextLine());
		    			date = new Date();
		    	        
		    	        //subject creation
		        		controller.createEnroll(code, idno, dateFormat.format(date));
		        		
		        		//prompt for finished process
		    	        System.out.println("-------Process Finished-------");
		    	        System.out.println("Press enter to continue...");
		    	        kb.nextLine();
		        		break;
		        		
		        	case 2:
		        		printEnroll();
		        		break;
		        		
		        	case 3:
		        		//To-Do
		        		break;
		        		
		        	case 4:
		        		//To-Do
		        		break;
	        	}
	        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
	        	System.out.println("Entered information does not exists");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} while (choice != 0);
	}
	//--------------------EXTRA FEATURE--------------------//
	//asks for class code and retrieves students - Henry
	public String classStudent(){
	System.out.print("Enter Class Code: ");
	int code= Integer.parseInt(kb.nextLine());
	return getClassStudent(code);
	}
	//asks for subjectID and retrives class Schedules -Henry
	public String SubjectClass(){
	System.out.print("Enter Subject Code: ");
	int code = Integer.parseInt(kb.nextLine());
	return getSubjClass(code);
	}
        //----------------------READ FUNCTIONALITY------------------------//
            private static int getResTotal(ResultSet rs) throws Exception {
                int count = 0;
                rs.last();
                count = rs.getRow();
                rs.beforeFirst();
                return count;    	
            }
        
            public static void printStudents(){
                try {
                    ResultSet rs = controller.getStudents();
                    if (getResTotal(rs) == 0) {
                            System.out.println("Error: no records found!!!");
                    } else {      			
                        System.out.printf("     %-15s %-20s %-15s %-10s %-15s %-10s %-25s%n",
                                        "IDNo","LastName","FirstName","MidInit", "Gender", "ContactNo","Email");
                        int row = 1;
                        while (rs.next()) {          
                            String idno = rs.getString(1);
                            String lastname = rs.getString(2);
                            String firstname = rs.getString("firstname");
                            String midInitial = rs.getString("midInitial");
                            String gender = filterNull(rs.getString("gender"));
                            String contactno = rs.getString("contactno");
                            String email = rs.getString("email");
                            System.out.printf("%-4d %-15s %-20s %-15s %-10s %-15s %-10s %-25s%n",
                                        row++, idno, lastname, firstname, midInitial, gender, contactno, email);
                        }
                    }		
                    printDivider();
                    System.out.println();
                } catch (Exception e) {
                        System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
                }
            }
       
            public static void printSubjects(){
                try {
                    ResultSet rs = controller.getSubject();
                    if (getResTotal(rs) == 0) {
                            System.out.println("Error: no records found!!!");
                    } else {   		
                        System.out.printf("     %-15s %-20s %-15s %n",
                                        "SubjID","Title","Units");
                        int row = 1;
                        while (rs.next()) {          
                            String subjid = filterNull(rs.getString("subjid"));
                            String title = filterNull(rs.getString("title"));
                            String units = filterNull(rs.getString("units"));
                            System.out.printf("%-4d %-15s %-20s %-15s %n",
                                        row++, subjid, title, units);
                        }
                    }		
                    printDivider();
                    System.out.println();
                } catch (Exception e) {
                        System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
                }
            }
        
            public static void printClass(){
                try {
                    ResultSet rs = controller.getClass();
                    if (getResTotal(rs) == 0) {
                            System.out.println("Error: no records found!!!");
                    } else {   		
                        System.out.printf("     %-15s %-20s %-15s %-10s %n",
                                        "Classcode","Time","Day","SubjId");
                        int row = 1;
                        while (rs.next()) {          
                            String classcode = filterNull(rs.getString("classocde"));
                            String time = filterNull(rs.getString("time"));
                            String day = filterNull(rs.getString("day"));
							String time = filterNull(rs.getString("time"));
                            System.out.printf("%-4d %-15s %-20s %-15s %n",
                                        row++, classcode, time, day, subjid);
                        }
                    }		
                    printDivider();
                    System.out.println();
                } catch (Exception e) {
                        System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
                }
            }
      
            public static void printEnroll(){
                try {
                    ResultSet rs = controller.getEnroll();
                    if (getResTotal(rs) == 0) {
                            System.out.println("Error: no records found!!!");
                    } else {   		
                        System.out.printf("     %-15s %-20s %-15s %n",
                                        "Classcode","IDNo","Date Submitted");
                        int row = 1;
                        while (rs.next()) {          
                            String classcode = filterNull(rs.getString("classocde"));
                            String idno = filterNull(rs.getString("idno"));
                            String datesubmitted = filterNull(rs.getString("datesubmitted"));
                            System.out.printf("%-4d %-15s %-20s %-15s %n",
                                        row++, classcode, idno, datesubmitted);
                        }
                    }		
                    printDivider();
                    System.out.println();
                } catch (Exception e) {
                        System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
                }
            }
        //--------------------------------------------------------------------//
}
