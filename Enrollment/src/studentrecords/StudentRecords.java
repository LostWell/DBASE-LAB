package studentrecords;

import java.util.Scanner;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentRecords {
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
					System.out.println("Error: input a valid value...");
					System.out.print("Press enter key to continue...");
					kb.nextLine();
					choice = 0;
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
			case 5:
				System.out.println("+---------------------------------+");
				System.out.println(" Thank you for using this program");
				System.out.println("+---------------------------------+");
				break;
			}

		} while(choice != 5);
		kb.close();
		controller.close();
	}

	public static void accountsMenu(int choice){
		do {
			System.out.println();
			System.out.println("+------------------------------+");
			System.out.println("|    M E N U  O P T I O N S    |");
			System.out.println("+------------------------------+");
			System.out.println("| 1. Back                      |");
			System.out.println("| 2. Add Account               |");
			System.out.println("| 3. Display All Accounts      |");
			System.out.println("| 4. Update Account            |");
			System.out.println("| 5. Delete Account            |");
			System.out.println("| 6. Display Enrolled Subjects |");
			System.out.println("| 7. Display Finished Subjects |");
			System.out.println("+------------------------------+");
			do {
				System.out.print("Enter your choice: ");
				try {
					choice = Integer.parseInt(kb.nextLine());
				} catch (Exception e) {
					System.out.println("Error: input a valid value...");
					System.out.print("Press enter key to continue...");
					kb.nextLine();
					choice = 0;
				}
				System.out.println("+----------------------------+");
				System.out.println();
			} while(choice < 1 || choice > 7);

			try {
				switch (choice){
				case 2:
					addAccount();
					break;

				case 3:
					printStudents();
					break;

				case 4:
					updateAccount();
					break;

				case 5:
					deleteAccount();
					break;
				case 6:
					printSchedule(6);
					break;
				case 7:
					printSchedule(7);
					break;
				}
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
				System.out.println("------Invalid value------");
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} while (choice != 1);
	}

//-------------------Account Methods--------------------------
	
	private static void addAccount() throws Exception {
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
	}

	public static void printStudents(){
		try {
			ResultSet rs = controller.getStudents();
			if (DBController.getResTotal(rs) == 0) {
				System.out.println("Error: no records found!!!");
			} else {      			
				System.out.println();
				System.out.printf("     %-15s %-20s %-15s %-10s %-15s %-10s %-25s%n",
						"IDNo","LastName","FirstName","MidInit", "Gender", "ContactNo","Email");
				int row = 1;
				while (rs.next()) {
					String idno = rs.getString(1);
					String lastname = rs.getString(2);
					String firstname = rs.getString("firstname");
					String midInitial = rs.getString("midInitial");
					String gender = rs.getString("gender");
					String contactno = rs.getString("contactno");
					String email = rs.getString("email");
					System.out.printf("%-4d %-15s %-20s %-15s %-10s %-15s %-10s %-25s%n",
							row++, idno, lastname, firstname, midInitial, gender, contactno, email);
				}
			}		
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
		}
	}

	private static void updateAccount() throws Exception {
		System.out.println("+----------------------------+");
		System.out.println("|       Update Account       |");
		System.out.println("+----------------------------+");
		System.out.print("Enter ID number: ");
		int idno = Integer.parseInt(kb.nextLine());
		System.out.println("+-----ENTER UPDATED INFO-----+");
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
		controller.updateAccount(idno, lname, fname, midInitial, gender, contactNumber, email);

		//prompt for finished process
		System.out.println("-------Updated-------");
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}

	private static void deleteAccount() throws Exception {
		System.out.println("+----------------------------+");
		System.out.println("|       Delete Account       |");
		System.out.println("+----------------------------+");
		System.out.print("Enter ID number: ");
		int idno = Integer.parseInt(kb.nextLine());
		controller.deleteAccount(idno);
		
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}

	public static void printSchedule(int choice){
		try {
			if (choice == 6) {
				System.out.println("+----------------------------+");
				System.out.println("|       Display Schedule     |");
				System.out.println("+----------------------------+");
			} else if (choice == 7) {
				System.out.println("+----------------------------+");
				System.out.println("|  Display Finished Subjects |");
				System.out.println("+----------------------------+");
			}
			System.out.print("Enter ID number: ");
			int idno = Integer.parseInt(kb.nextLine());
			ResultSet rs = controller.displayEnrolled(idno, choice);
			if (DBController.getResTotal(rs) == 0) {
				System.out.println();
				System.out.println("Error: no records found!!!");
			} else {   		
				System.out.println();
				System.out.printf("        %-15s%-15s%-80s%-20s%-15s%n",
						"Class Code","Subject ID","title", "Time", "Days");
				int row = 1;
				while (rs.next()) {   
					String classcode = rs.getString("classcode");
					String subjid = rs.getString("subjid");
					String title = rs.getString("title");
					String time = rs.getString("time");
					String day = rs.getString("time");
					System.out.printf("%-8d%-15s%-15s%-80s%-20s%-15s%n",
							row++, classcode, subjid, title, time, day);
				}
			}
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("Error: " + e.getClass() + "\n" + e.getMessage());
		}
	}

	public static void subjectsMenu(int choice){
		do {
			System.out.println();
			System.out.println("+----------------------------+");
			System.out.println("|   M E N U  O P T I O N S   |");
			System.out.println("+----------------------------+");
			System.out.println("| 1. Back                    |");
			System.out.println("| 2. Add Subject             |");
			System.out.println("| 3. Check Subject           |");
			System.out.println("| 4. Update Subject          |");
			System.out.println("| 5. Delete Subject          |");
			System.out.println("+----------------------------+");
			do {
				System.out.print("Enter your choice: ");
				try {
					choice = Integer.parseInt(kb.nextLine());
				} catch (Exception e) {
					System.out.println("Error: input a valid value...");
					System.out.print("Press enter key to continue...");
					kb.nextLine();
					choice = 0;
				}
				System.out.println("+----------------------------+");
				System.out.println();
			} while(choice < 1 || choice > 5);

			try {
				switch (choice){
				case 2:
					addSubject();
					break;

				case 3:
					printSubjects();
					break;

				case 4:
					updateSubject();
					break;

				case 5:
					deleteSubject();
					break;
				}
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
				System.out.println("------Invalid value------");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} while (choice != 1);

	}

	//-------------------Subject Methods--------------------------
	
	private static void addSubject() throws Exception {
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
		System.out.print("Enter prerequisite subject ID: ");
		String prerequisite = kb.nextLine();
		System.out.print("Enter year: ");
		int year = Integer.parseInt(kb.nextLine());
		System.out.print("Enter semester: ");
		int semester = Integer.parseInt(kb.nextLine());

		//subject creation
		controller.createSubject(id, title, units, prerequisite, year, semester);

		//prompt for finished process
		System.out.println("-------Process Finished-------");
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}
	
	public static void printSubjects(){
		try {
			ResultSet rs = controller.getSubjects();
			if (DBController.getResTotal(rs) == 0) {
				System.out.println("Error: no records found!!!");
			} else {   		
				System.out.println();
				System.out.printf("        %-15s%-80s%-8s%-15s%-10s%-10s%n",
						"SubjID","Title","Units", "Prerequisite", "Year", "Semester");
				int row = 1;
				while (rs.next()) {          
					String subjid = rs.getString("subjid");
					String title = rs.getString("title");
					String units = rs.getString("units");
					String prerequisite = rs.getString("prerequisite");
					int year = rs.getInt("year");
					int semester = rs.getInt("semester");
					System.out.printf("%-8d%-15s%-80s%-8s%-15s%-10d%-10d%n",
							row++, subjid, title, units, prerequisite, year, semester);
				}
			}
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
		}
	}
	
	private static void updateSubject() throws Exception {
		System.out.println("+----------------------------+");
		System.out.println("|       Update Subject       |");
		System.out.println("+----------------------------+");
		System.out.print("Enter subject ID: ");
		String id = kb.nextLine();
		System.out.println("+-----ENTER UPDATED INFO-----+");
		System.out.print("Enter subject title: ");
		String title = kb.nextLine();
		System.out.print("Enter number of units: ");
		int units = Integer.parseInt(kb.nextLine());
		System.out.print("Enter prerequisite subject ID: ");
		String prerequisite = kb.nextLine();
		System.out.print("Enter year: ");
		int year = Integer.parseInt(kb.nextLine());
		System.out.print("Enter semester: ");
		int semester = Integer.parseInt(kb.nextLine());

		//subject update
		controller.updateSubject(id, title, units, prerequisite, year, semester);

		//prompt for finished process
		System.out.println("-------Updated-------");
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}

	private static void deleteSubject() throws Exception {
		System.out.println("+----------------------------+");
		System.out.println("|       Delete Subject       |");
		System.out.println("+----------------------------+");
		System.out.print("Enter subject ID: ");
		String subjid = kb.nextLine();
		controller.deleteSubject(subjid);

		System.out.println("Press enter to continue...");
		kb.nextLine();
	}

	public static void classesMenu(int choice){
		do {
			System.out.println();
			System.out.println("+--------------------------------------------+");
			System.out.println("|           M E N U  O P T I O N S           |");
			System.out.println("+--------------------------------------------+");
			System.out.println("| 1. Back                                    |");
			System.out.println("| 2. Add Class                               |");
			System.out.println("| 3. Display All Classes                     |");
			System.out.println("| 4. Display Enrolled Students in a Class    |");
			System.out.println("| 5. Update Class                            |");
			System.out.println("| 6. Delete Class                            |");
			System.out.println("+--------------------------------------------+");
			do {
				System.out.print("Enter your choice: ");
				try {
					choice = Integer.parseInt(kb.nextLine());
				} catch (Exception e) {
					System.out.println("Error: input a valid value...");
					System.out.print("Press enter key to continue...");
					kb.nextLine();
					choice = 0;
				}
				System.out.println("+----------------------------+");
				System.out.println();
			} while(choice < 1 || choice > 6);
			try {
				switch (choice){
				case 2:
					addClass();
					break;

				case 3:
					printClass();
					break;
					
				case 4:
					classStudent();
					break; 

				case 5:
					updateClass();
					break; 

				case 6:
					deleteClass();
					break;
				}
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
				System.out.println("------Invalid value------");
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (choice != 1);
	}
	
	//-------------------Class Methods--------------------------
	
	private static void addClass() throws Exception {
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

		//class creation
		controller.createClass(code, time, days, id);

		//prompt for finished process
		System.out.println("-------Process Finished-------");
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}
	
	public static void printClass(){
		try {
			ResultSet rs = controller.getClasses();
			if (DBController.getResTotal(rs) == 0) {
				System.out.println("Error: no records found!!!");
			} else {   
				System.out.println();
				System.out.printf("     %-12s %-20s %-10s %-15s %n",
						"Classcode","Time","Day","SubjId");
				int row = 1;
				while (rs.next()) {          
					String classcode = rs.getString("classcode");
					String time = rs.getString("time");
					String day = rs.getString("day");
                    String subjid = rs.getString("subjid");
					System.out.printf("%-4d %-12s %-15s %-20s %-10s %n",
							row++, classcode, subjid, time, day);
				}
			}
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
		}
	}
	
	public static void classStudent(){
		try {
			System.out.println("+----------------------------------------+");
			System.out.println("|  Display Enrolled Students in a Class  |");
			System.out.println("+----------------------------------------+");
			System.out.print("Enter Class Code: ");
			String code= kb.nextLine();
			ResultSet rs = controller.getClassStudent(code);
			if (DBController.getResTotal(rs) == 0) {
				System.out.println("Error: no records found!!!");
			} else {
				System.out.println();
				System.out.printf("     %-15s %-20s %-15s %-10s %-15s %-10s %-25s%n",
						"IDNo","LastName","FirstName","Middle Initial", "Gender", "ContactNo","Email");
				int row = 1;
				while (rs.next()) {          
					String idno = rs.getString(1);
					String lastname = rs.getString(2);
					String firstname = rs.getString("firstname");
					String midInitial = rs.getString("midInitial");
					String gender = rs.getString("gender");
					String contactno = rs.getString("contactno");
					String email = rs.getString("email");
					System.out.printf("%-4d %-15s %-20s %-15s %-10s %-15s %-10s %-25s%n",
							row++, idno, lastname, firstname, midInitial, gender, contactno, email);
				}
			}		
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
		}
	}
	
	private static void updateClass() throws Exception {
		//updateClass();
		System.out.println("+----------------------------+");
		System.out.println("|         Update Class       |");
		System.out.println("+----------------------------+");
		System.out.print("Enter class code: ");
		String code = kb.nextLine();
		System.out.println("+-----ENTER UPDATED INFO-----+");
		System.out.print("Enter scheduled time: ");
		String time = kb.nextLine();
		System.out.print("Enter scheduled days: ");
		String days = kb.nextLine();
		System.out.print("Enter subject ID: ");
		String id = kb.nextLine();

		//class update
		controller.updateClass(code, time, days, id);

		//prompt for finished process
		System.out.println("-------Updated-------");
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}

	private static void deleteClass() throws Exception {
		System.out.println("+----------------------------+");
		System.out.println("|        Delete Class        |");
		System.out.println("+----------------------------+");
		System.out.print("Enter classcode: ");
		String classcode = kb.nextLine();
		controller.deleteClass(classcode);

		System.out.println("Press enter to continue...");
		kb.nextLine();
	}

	public static void enrollmentMenu(int choice){
		do {
			System.out.println();
			System.out.println("+--------------------------------------------+");
			System.out.println("|            M E N U  O P T I O N S          |");
			System.out.println("+--------------------------------------------+");
			System.out.println("| 1. Back                                    |");
			System.out.println("| 2. Enroll Per Class                        |");
			System.out.println("| 3. Enroll Per Year                         |");
			System.out.println("| 4. Display Enrollment Data                 |");
			System.out.println("| 5. Display Available Classes for a Subject |");
			System.out.println("| 6. Update Info                             |");
			System.out.println("| 7. Unenroll                                |");
			System.out.println("+--------------------------------------------+");
			do {
				System.out.print("Enter your choice: ");
				try {
					choice = Integer.parseInt(kb.nextLine());
				} catch (Exception e) {
					System.out.println("Error: input a valid value...");
					System.out.print("Press enter key to continue...");
					kb.nextLine();
					choice = 0;
				}
				System.out.println("+----------------------------+");
				System.out.println();
			} while(choice < 1 || choice > 7);
			try {
				switch (choice){
				case 2:
					enroll();
					break;

				case 3:
					enrollYear();
					break;
					
				case 4:
					printEnroll();
					break;

				case 5:
					subjectClass();
					break;
					
				case 6:
					updateInfo();
					break;

				case 7:
					unenroll();
					break;
				}
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
				System.out.println("------Invalid value------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (choice != 1);
	}

	//-------------------Enroll Methods--------------------------
	
	private static void enroll() throws Exception {
		//Prompt for input data
		System.out.println("+----------------------------+");
		System.out.println("|        Enroll Class        |");
		System.out.println("+----------------------------+");
		System.out.print("Enter ID number: ");
		int idno = Integer.parseInt(kb.nextLine());
		System.out.print("Enter class code: ");
		String code = kb.nextLine();
		if (controller.checkList(idno, code)) {
			date = new Date();
			//enroll
			controller.createEnroll(code, idno, dateFormat.format(date), "In Progress");
			System.out.println("-------Process Finished-------");
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} else {
			System.out.println("-------Prerequisite not completed-------");
			System.out.println("Press enter to continue...");
			kb.nextLine();
		}
		
	}
	
	private static void enrollYear() throws Exception {
		//Prompt for input data
		System.out.println("+----------------------------+");
		System.out.println("|       Enroll Per Year      |");
		System.out.println("+----------------------------+");
		System.out.print("Enter ID number: ");
		int idno = Integer.parseInt(kb.nextLine());
		System.out.print("Enter year: ");
		int year = Integer.parseInt(kb.nextLine());
		System.out.print("Enter semester: ");
		int semester = Integer.parseInt(kb.nextLine());
		
		controller.enrollPerYear(idno, year, semester);
	}
	
	public static void printEnroll(){
		try {
			ResultSet rs = controller.getEnroll();
			if (DBController.getResTotal(rs) == 0) {
				System.out.println("Error: no records found!!!");
			} else {   		
				System.out.println();
				System.out.printf("     %-15s %-20s %-15s %-15s %n",
						"Classcode","ID No","Date Submitted", "Status");
				int row = 1;
				while (rs.next()) {          
					String classcode = rs.getString("classcode");
					String idno = rs.getString("idno");
					String datesubmitted = rs.getString("datesubmitted");
					String status = rs.getString("status");
					System.out.printf("%-4d %-15s %-20s %-15s %-15s %n",
							row++, classcode, idno, datesubmitted, status);
				}
			}		
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
		}
	}
	
	public static void subjectClass(){
		try {
			System.out.println("+--------------------------------------------+");
			System.out.println("|   Display Available Classes for a Subject  |");
			System.out.println("+--------------------------------------------+");
			System.out.print("Enter Subject ID: ");
			String subjid = kb.nextLine();
			ResultSet rs = controller.getSubjClass(subjid);
			if (DBController.getResTotal(rs) == 0) {
				System.out.println("Error: no records found!!!");
			} else {
				ResultSet result = controller.getSubjectInfo(subjid);
				System.out.println();
				System.out.printf("%-15s %-100s %n",
						result.getString("subjid"),result.getString("title"));
				System.out.println("+---------------------------------------------------------+");
				System.out.printf("     %-12s %-20s %-10s %-15s %n",
						"Classcode","Time","Day","SubjId");
				int row = 1;
				while (rs.next()) {          
					String classcode = rs.getString("classcode");
					String time = rs.getString("time");
					String day = rs.getString("day");
					System.out.printf("%-4d %-12s %-15s %-20s %-10s %n",
							row++, classcode, subjid, time, day);
				}
			}
			System.out.println();
			System.out.println("Press enter to continue...");
			kb.nextLine();
		} catch (Exception e) {
			System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
		}
	}
	
	private static void updateInfo() throws Exception {
		//update Info
		System.out.println("+----------------------------+");
		System.out.println("|     Update Enroll Info     |");
		System.out.println("+----------------------------+");
		System.out.print("Enter old class code: ");
		String code1 = kb.nextLine();
		System.out.print("Enter new class code: ");
		String code2 = kb.nextLine();
		System.out.print("Enter new status: ");
		String status = kb.nextLine();
		System.out.print("Enter ID Number: ");
		int idno = Integer.parseInt(kb.nextLine());
		date = new Date();

		//class update
		controller.updateEnroll(code1, code2, idno, dateFormat.format(date), status);

		//prompt for finished process
		System.out.println("-------Updated-------");
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}
	
	private static void unenroll() throws Exception {
		//Prompt for input data
		System.out.println("+----------------------------+");
		System.out.println("|          Unenroll          |");
		System.out.println("+----------------------------+");
		System.out.print("Enter ID number: ");
		int idno = Integer.parseInt(kb.nextLine());
		System.out.print("Enter class code: ");
		String code = kb.nextLine();

		//subject deletion
		controller.unenroll(code, idno);

		//prompt for finished process
		System.out.println("Press enter to continue...");
		kb.nextLine();
	}
	
}
