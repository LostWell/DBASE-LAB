package studentrecords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBController {
	private Connection connection;
	private Statement statement;
	private PreparedStatement ps;
	private String sql;

	public void dbaseConnect(String url, String user, String pass) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, user, pass);
		statement = connection.createStatement();
	}

	public void createAccount(int idno, 
			String lastname, 
			String firstname, 
			String midInitial, 
			String gender, 
			String contactno, 
			String email) throws Exception {

		sql = "INSERT INTO students "
				+ "(idno, lastname, firstname, midInitial, gender, contactno, email) "
				+ "values (?,?,?,?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, idno);
		ps.setString(2, lastname);
		ps.setString(3, firstname);
		ps.setString(4, midInitial);
		ps.setString(5, gender);
		ps.setString(6, contactno);
		ps.setString(7, email);
		ps.executeUpdate();
	}

	public void createSubject(String subjid,
			String title,
			int units, 
			String prerequisite, 
			int year, 
			int semester) throws Exception {

		sql = "INSERT INTO subject "
				+ "(subjid, title, units, prerequisite, year, semester) "
				+ "values (?,?,?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, subjid);
		ps.setString(2, title);
		ps.setInt(3, units);
		ps.setString(4, prerequisite);
		ps.setInt(5, year);
		ps.setInt(6, semester);
		ps.executeUpdate();
	}

	public void createClass(String classcode, 
			String time,
			String day,
			String subjid) throws Exception {
		sql = "INSERT INTO class (classcode, subjid, time, day) VALUES ('"+classcode+"', '"+subjid+"', '"+time+"', '"+day+"');";
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public void createEnroll(String classcode,
			int idno, String datesubmitted, String status) throws Exception {

		sql = "INSERT INTO enroll "
				+ "(classcode, idno, datesubmitted, status) "
				+ "values (?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, classcode);
		ps.setInt(2, idno);
		ps.setString(3, datesubmitted);
		ps.setString(4, status);
		ps.executeUpdate();
	}


	//-------------------READ FUNCTIONALITY-----------------------------------------
	public ResultSet getSubjects() throws Exception {
		statement = connection.createStatement();
		sql = "SELECT * FROM subject ORDER BY YEAR, SEMESTER, SUBJID;";
		return statement.executeQuery(sql);
	}

	public ResultSet getStudents() throws Exception {
		statement = connection.createStatement();
		sql = "SELECT * FROM students ORDER BY lastname, firstname;";
		return statement.executeQuery(sql);
	}
	public ResultSet getClasses() throws Exception {
		statement = connection.createStatement();
		sql = "SELECT * FROM class ORDER BY classcode;";
		return statement.executeQuery(sql);
	}
	public ResultSet getEnroll() throws Exception {
		statement = connection.createStatement();
		sql = "SELECT * FROM enroll ORDER BY idno, classcode, datesubmitted;";
		return statement.executeQuery(sql);
	}

	//---------------------------UPDATE FUNCTIONALITY---------------------------------------------------
	// Gerichelle
	public void updateAccount(int idno,
			String lname,
			String fname,
			String midInitial,
			String gender,
			String contactNumber,
			String email) throws Exception {

		//execute a query (update preparedStatement)
		sql = "UPDATE students SET "
			+ "lastname = '"+ lname +"', "
			+ "firstname = '"+ fname +"', "
			+ "midInitial = '"+ midInitial +"', "
			+ "gender = '"+ gender +"', "
			+ "contactno = '"+ contactNumber +"', "
			+ "email = '"+ email +"' "
			+ "WHERE idno = "+ idno;
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	}
	// Gerichelle
	public void updateSubject(String subjid,
			String title,
			int units, 
			String prerequisite, 
			int year, 
			int semester) throws Exception {
		sql = "UPDATE subject SET "
				+ "title = '"+ title +"', "
				+ "prerequisite = '"+ prerequisite +"', "
				+ "year = '"+ year +"', "
				+ "semester = '"+ semester +"', "
				+ "units = "+ units +" "
				+ "WHERE subjid = '"+ subjid + "'";
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	}  
	//Gerichelle, Shaira
	public void updateClass(String classcode, 
			String time,
			String day,
			String subjid) throws Exception {
		sql = "UPDATE class SET "
				+ "time = '"+ time +"', "
				+ "day = '"+ day +"', "
				+ "subjid = '"+ subjid +"' "
				+ "WHERE classcode = '"+ classcode +"'";
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	}
	// Gerichelle
	
	public void updateEnroll(String classcode1, 
			String classcode2, 
			int idno, 
			String date, String status) throws Exception {
		sql = "UPDATE enroll SET "
				+ "classcode = '"+ classcode2 +"', "
				+ "status = '"+ status +"', "
				+ "datesubmitted = '"+ date +"' "
				+ "WHERE classcode = '"+ classcode1 +"' AND idno = "+ idno +"";
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	//-------------------------------------------Delete------------------------------------------------//
	public void deleteAccount(int idno) throws Exception { // Albert did this part
		sql = "delete FROM students WHERE idno = "+idno;
		statement = connection.createStatement();
		if(statement.executeUpdate(sql) == 0) {
			System.out.println("-----Row does not exists-----");
		} else {
			System.out.println("-----Account successfully deleted-----");
		}
	}

	public void deleteSubject(String subjid)throws Exception { // Mitz Did this part
		sql = "delete FROM subject WHERE subjid = '"+ subjid +"'";
		statement = connection.createStatement();
		if(statement.executeUpdate(sql) == 0) {
			System.out.println("-----Row does not exists-----");
		} else {
			System.out.println("-----Successfully deleted "+ subjid +"-----");
		}
	}

	public void deleteClass(String classcode) throws Exception { // Albert did this part
		sql = "delete FROM class where classcode = '"+ classcode +"'";
		statement = connection.createStatement();
		if(statement.executeUpdate(sql) == 0) {
			System.out.println("-----Row does not exists-----");
		} else {
			System.out.println("-----Successfully deleted " + classcode + "-----");
		}
	}
	
	public void unenroll(String classcode, int idno) throws Exception { // Louel did this part
		sql = "delete FROM enroll where classcode = '"+ classcode +"'" + "AND idno = '"+ idno +"'";
		statement = connection.createStatement();
		if(statement.executeUpdate(sql) == 0) {
			System.out.println("-----Row does not exists-----");
		} else {

			System.out.println("-----Unenrolled "+ idno +" from " + classcode + "-----");
		}
	}

	//-------------------------Extra Feature---------------------------------------------//
	//retrieves the information of each student enrolled in a given class (Henry)
	public ResultSet getClassStudent(String classCode) throws Exception {
		statement = connection.createStatement(); //creates a connection to the database in the server
		sql = "select idno, lastname, firstname, midInitial, gender, contactno, email from students inner join enroll using(idno) where classcode = "+classCode+" AND status = \"In Progress\";"; //sql statement for query
		return statement.executeQuery(sql); //executes the given sql statement to the database
	}
	//retrieves the information of each class in a given subject (Henry)
	public ResultSet getSubjClass(String subjid) throws Exception{
		statement = connection.createStatement(); //creates a connection to the database in the server
		sql = "select classcode, time, day from class inner join subject using(subjid) where subjid = "+subjid+";";//sql statement for query
		return statement.executeQuery(sql); //executes the given sql statement to the database
	}
	
	public ResultSet getSubjectInfo(String classcode) throws Exception{
		statement = connection.createStatement(); //creates a connection to the database in the server
		sql = "select subjid, title, prerequisite from subject inner join class using (subjid) where classcode ="+classcode+";";
		return statement.executeQuery(sql); //executes the given sql statement to the database
	}
	
	public boolean checkList(int idno, String classcode) {
		try {
			statement = connection.createStatement();
			sql = "select prerequisite from subject where subjid = "
					+ "(select subjid from class where classcode = '"+classcode+"')";
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.first();
			resultSet.getString(1);
			if (resultSet.wasNull()) {
				return true;
			} else {
				sql = "select status from enroll where idno = '"+idno+"' and classcode = "
						+ "(select classcode from class where subjid = "
						+ "(select prerequisite from subject where subjid = "
						+ "(select subjid from class where classcode = '"+classcode+"')))";
				resultSet = statement.executeQuery(sql);
				if (getResTotal(resultSet) == 0) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getClass() + "\n" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public void enrollPerYear(int idno, int year, int semester){
		try {
			sql = "select idno from students where idno = " + idno + ";";
			ResultSet resultSet = statement.executeQuery(sql);
			if (getResTotal(resultSet) == 0) {
				System.out.println("-----Student does not exist-----");
			} else {
				sql = "select classcode from class where subjid in (select subjid from subject where year < "+year+" ORDER BY YEAR, SEMESTER, SUBJID);";
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
						createEnroll(resultSet.getString("classcode"), idno, StudentRecords.dateFormat.format(StudentRecords.date), "Taken");
				}	
				
				sql = "select classcode, semester from class inner join (select subject.subjid, semester from subject where year = "+year+" and semester <= "+semester+") a using(subjid);";
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {	
					if (resultSet.getInt("semester") < semester) {
						createEnroll(resultSet.getString("classcode"), idno, StudentRecords.dateFormat.format(StudentRecords.date), "Taken");
					} else {
						createEnroll(resultSet.getString("classcode"), idno, StudentRecords.dateFormat.format(StudentRecords.date), "In Progress");
					}
				}
				System.out.println("-------Process Finished-------");
				System.out.println("Press enter to continue...");
				StudentRecords.kb.nextLine();
			}
		} catch(Exception e){
			System.out.println("-----Error-----");
			e.printStackTrace();
		}
	}
	
	public ResultSet displayEnrolled(int idno, int choice) throws Exception{
		statement = connection.createStatement();
		if (choice == 5) {
			sql = "SELECT classcode, subjid, title, time, day FROM enroll INNER JOIN class USING (classcode) INNER JOIN subject USING (subjid) WHERE idno = " + idno +" AND status = \"In Progress\";";
			
		} else {
			sql = "SELECT classcode, subjid, title, time, day FROM enroll INNER JOIN class USING (classcode) INNER JOIN subject USING (subjid) WHERE idno = " + idno +" AND status = \"Taken\";";
		}
		return statement.executeQuery(sql);
	}
	
	public static int getResTotal(ResultSet rs) throws Exception {
		int count = 0;
		rs.last();
		count = rs.getRow();
		rs.beforeFirst();
		return count;    	
	}
	
	public void close() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
