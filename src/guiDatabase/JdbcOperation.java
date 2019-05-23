package guiDatabase;

import java.sql.*;
import java.util.Arrays;
import java.util.TimeZone;

public class JdbcOperation {
	
   //Connect to database
	public Connection connectDB (){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/test"
			   + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone="
			   +TimeZone.getDefault().getID(),"root", "");  
			 System.out.println("Connect to the database successfully");	
		 } catch(SQLException e){
			 System.out.println("ERROR: Could not connect to the database");
		 }
		return conn;
	}
	
   //Count the total data
	public int rowsNo (Connection conn, String que) {
		Statement stmt = null;
		int no_of_rows = 0;
		String query = que;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery (query);
		    while (rs.next()) {
		       no_of_rows++;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			no_of_rows = 0;
			System.out.println("SQL exception occured" + e);
		}
		return no_of_rows;
	}
	
   //Search all data contents
	public String[][] retrieveAll (Connection conn) {
		Statement stmt = null;
		String student[][] = null;
		String query = "SELECT * FROM student";
		
		try {
			int rows = this.rowsNo(conn,query);
			int columns = 4;
			student = new String[rows][columns];
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery (query);
			for (int i = 0; i < student.length; i++) {
				rs.next();
				student[i][0] = rs.getString("Stu_id");
				student[i][1] = rs.getString("FName");
				student[i][2] = rs.getString("LName");
				student[i][3] = rs.getString("Email");
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		return student;
	}
	
   //Search data contents by student id
	public String[][] retrieveById (Connection conn, String stu_id) {
		Statement stmt = null;
		String student[][] = null;
		String query = "SELECT * FROM student WHERE Stu_id = '" + stu_id + "'";
		
		try {
			int columns = 4;
			int rows = this.rowsNo(conn, query);
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery (query);
			if(rows > 0) {
				student = new String[rows][columns];
				for (int i = 0; i < student.length; i++) {
					rs.next();
					student[i][0] = rs.getString("Stu_id");
					student[i][1] = rs.getString("FName");
					student[i][2] = rs.getString("LName");
					student[i][3] = rs.getString("Email");
				}
			}else {
				student = new String[1][1];
				student[0][0] = "Student not found";
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		return student;
	}
	
   //Search data contents by first name
	public String[][] retrieveByFName (Connection conn, String fName) {
		Statement stmt = null;
		String student[][] = null;
		String query = "SELECT * FROM student WHERE FName LIKE '" + fName + "%'";
		
		try {
			int columns = 4;
			int rows = this.rowsNo(conn, query);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery (query);
			if(rows > 0) {
				student = new String[rows][columns];
				for (int i = 0; i < student.length; i++) {
					rs.next();
					student[i][0] = rs.getString("Stu_id");
					student[i][1] = rs.getString("FName");
					student[i][2] = rs.getString("LName");
					student[i][3] = rs.getString("Email");
				}
			}else {
				student = new String[1][1];
				student[0][0] = "Student not found";
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		return student;
	}
	
   //Search data contents by last name
	public String[][] retrieveByLName (Connection conn, String lName) {
		Statement stmt = null;
		String student[][] = null;
		String query = "SELECT * FROM student WHERE LName LIKE '" + lName + "%'";
		
		try {
			int columns = 4;
			int rows = this.rowsNo(conn, query);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery (query);
			if(rows > 0) {
				student = new String[rows][columns];
				for (int i = 0; i < student.length; i++) {
					rs.next();
					student[i][0] = rs.getString("Stu_id");
					student[i][1] = rs.getString("FName");
					student[i][2] = rs.getString("LName");
					student[i][3] = rs.getString("Email");
				}
			}else {
				student = new String[1][1];
				student[0][0] = "Student not found";
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		return student;
	}
	
   //Search data contents by email
	public String[][] retrieveByEmail (Connection conn, String email) {
		Statement stmt = null;
		String student[][] = null;
		String query = "SELECT * FROM student WHERE Email LIKE '%" + email + "%'";
		
		try {
			int columns = 4;
			int rows = this.rowsNo(conn, query);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery (query);
			if(rows > 0) {
				student = new String[rows][columns];
				for (int i = 0; i < student.length; i++) {
					rs.next();
					student[i][0] = rs.getString("Stu_id");
					student[i][1] = rs.getString("FName");
					student[i][2] = rs.getString("LName");
					student[i][3] = rs.getString("Email");
				}
			}else {
				student = new String[1][1];
				student[0][0] = "Student not found";
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		return student;
	}
	
   //Add a student information
	public String addContent (Connection conn, String stu_id, String fName, String lName, String email) {
		String query = "INSERT INTO student VALUES (?,?,?,?)";
		
		try {
			PreparedStatement inserttemp = conn.prepareStatement(query);
			inserttemp.setString(1, stu_id);
			inserttemp.setString(2, fName);
			inserttemp.setString(3, lName);
			inserttemp.setString(4, email);
			inserttemp.executeUpdate();
			System.out.println("Add a new student");
			return "Add a new student";
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
			return "SQL exception occured error";
		}
	}
	
   //Delete a student information
	public String deleteContent (Connection conn, String stu_id) {
		Statement stmt = null;
		String query = "DELETE FROM student WHERE Stu_id = '" + stu_id + "'";
		
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			System.out.println("Delete a student");
			return "Delete a student";
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
			return "SQL exception occured error";
		}
	}
	
   //Update a student information
	public String updateContent (Connection conn, String stu_id, String fName, String lName, String email) {
		Statement stmt = null;
		String query = "UPDATE student SET FName = '"+fName+"', LName = '"+lName+"', Email = '"+email
				+"' WHERE Stu_id = '"+stu_id+"'";
		
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			System.out.println("Updata a student");
			return "Update a student";
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
			return "SQL exception occured error";
		}
	}
}
