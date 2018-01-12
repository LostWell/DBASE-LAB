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
    private ResultSet resultSet;
    private String sql;
    
    public void dbaseConnect(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }
    
    public void createAccount(int idno, 
    		                  String lastname, 
    		                  String firstname, 
    		                  String midInitial, 
    		                  String gender, 
    		                  String contactno, 
    		                  String email) throws Exception {
    	
        sql = "insert into students "
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
    		                  int units) throws Exception {
    	
    	sql = "insert into subject "
    		+ "(subjid, title, units) "
    	    + "values (?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, subjid);
    	ps.setString(2, title);
    	ps.setInt(3, units);
    	ps.executeUpdate();
    }
    
    public void createClass(String classcode,
    		                String time,
                            String day,
                            String subjid) throws Exception {
    	sql = "insert into class "
            + "(classcode, time, day, subjid) "
    		+ "values (?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.setString(2, time);
    	ps.setString(3, day);
    	ps.setString(4, subjid);
    	ps.executeUpdate();
    }
    
    public void createEnroll(String classcode,
    		                 int idno, String datesubmitted) throws Exception {
    	
    	sql = "insert into enroll "
            + "(classcode, idno, datesubmitted) "
    		+ "values (?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.setInt(2, idno);
    	ps.setString(3, datesubmitted);
    	ps.executeUpdate();
    }
    
    //-------------------------------------------Delete------------------------------------------------//
    public void deleteAccount(int idno) throws Exception { // Albert did this part
    	sql = "delete FROM account WHERE idno = ?; ";
    	ps = connection.prepareStatement(sql);
    	 ps.setInt(1, idno);
         ps.executeUpdate();
    }
    		    		
    public void deleteSubject(String subjid)throws Exception { // Mitz Did this part
    	sql = "delete FROM subjects WHERE subjid = ?; ";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, subjid);
    	ps.executeUpdate(sql);
    }
    
    public void deleteClass(String classcode) throws Exception { // Albert did this part
    	sql = "delete FROM class where classcode = ?; ";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.executeUpdate();
    }
    //-------------------READ FUNCTIONALITY-----------------------------------------
    public ResultSet getSubjects() throws Exception {
        statement = connection.createStatement();
        sql = "SELECT * FROM subject;";
        return statement.executeQuery(sql);
    }
    
    public ResultSet getStudents() throws Exception {
        statement = connection.createStatement();
        sql = "SELECT * FROM students;";
        return statement.executeQuery(sql);
    }
    public ResultSet getClass() throws Exception {
        statement = connection.createStatement();
        sql = "SELECT * FROM class;";
        return statement.executeQuery(sql);
    }
    public ResultSet getEnroll() throws Exception {
        statement = connection.createStatement();
        sql = "SELECT * FROM enroll;";
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
        sql = "UPDATE account"
            + "(idno, lname, fname, midInitial, gender, contactNumber, email) "
            + "values (?,?,?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, idno);
        ps.setString(2, lname);
        ps.setString(3, fname);
        ps.setString(4, midInitial);
        ps.setString(5, gender);
        ps.setString(6, contactNumber);
        ps.setString(7, email);
        ps.executeUpdate(sql);
    }
    // Gerichelle
    public void updateSubject(String id,
                              String title,
                              int units) throws Exception {
        sql = "UPDATE subject"
            + "(id, title, units)"
            + "values (?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, title);
        ps.setInt(3, units);
        ps.executeUpdate(sql);
    }  
     //Gerichelle, Shaira
    public void updateClass(String code, 
                            String time,
                            String days,
                            String id) throws Exception {
        sql = "UPDATE class"
            + "(code, time, days,  id)"
            + "values (?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, code);
        ps.setString(2, time);
        ps.setString(3, days);
        ps.setString(4, id);
        ps.executeUpdate(sql);
    }
    // Gerichelle
    public void updateInfo(String code,
                           int idno,
                           Date date) throws Exception {
        sql = "UPDATE info"
            + "(code, idno, date)"
            + "values (?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, code);
        ps.setInt(2, idno);
        ps.setDate(3, date);
        ps.executeUpdate(sql);
    }
	
	//-------------------------Extra Feature---------------------------------------------//
    //retrieves the information of each student enrolled in a given class (Henry)
    public ResultSet getClassStudent(int classCode) throws Exception {
        statement = connection.createStatement();//creates a connection to the database in the server
        sql = "select * from students inner join enroll using(idno) where classcode="+(classCode)+";";//sql statement for query
        return statement.executeQuery(sql);//executes the given sql statement to the database
   }
    //retrieves the information of each class in a given subject (Henry)
    public ResultSet getSubjClass(int classCode) throws Exception{
	statement = connection.createStatement();//creates a connection to the database in the server
	sql = "select * from class inner join subject using(subjid) where subjid="+(classCode)+";";//sql statement for query
	return statement.executeQuery(sql);//executes the given sql statement to the database
   }
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
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
