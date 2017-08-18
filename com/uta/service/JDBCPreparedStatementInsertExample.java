package com.uta.service;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.Date;

public class JDBCPreparedStatementInsertExample {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/company";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	public static void main(String[] argv) throws ParseException, IOException, SQLException {

//			truncateAllTable();
			insertRecordIntoEmployee();
     		insertRecordIntoDepartment();
			alterTable();
			insertRecordIntoDept_Loc(); 
			insertRecordIntoProject();
			insertRecordIntoWorks_On();
	}

	
	/* Method to insert data into Employee Table */

	private static void insertRecordIntoEmployee() throws SQLException, ParseException, IOException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO EMPLOYEE"
				+ "(FNAME,MINIT,LNAME,SSN,BDATE,ADDRESS,SEX,SALARY,SUPER_SSN,DNO) VALUES" + "(?,?,?,?,?,?,?,?,?,?)";
		
		
		
		File fileName = new File("E:\\Courses\\Summer-2017\\DB2\\Project 2\\EMPLOYEE.txt");
		FileInputStream fileInputStreamObject = new FileInputStream(fileName);
		String values[] = null;

		// Construct BufferedReader from InputStreamReader
		BufferedReader bufferReaderObject = new BufferedReader(new InputStreamReader(fileInputStreamObject));

		String inputLine = null;
		while ((inputLine = bufferReaderObject.readLine()) != null) {
			 values = inputLine.replace("'","").split(", ");
			 
			 String FNAME = null;
			 String MINIT = null;
			 String LNAME = null;
			 String SSN = null;
			 String BDATE = null;
			 String ADDRESS = null;
			 String SEX = null;
			 Integer SALARY =  null;
			 String SUPER_SSN = null;
			 Integer DNO =  null;
			 
			 if(!values[0].equals("null")){
				 FNAME = values[0];
			 }
			 if(!values[1].equals("null")){
				 MINIT = values[1];
			 }
			 if(!values[2].equals("null")){
				 LNAME = values[2];
			 }
			 if(!values[3].equals("null")){
				 SSN = values[3];
			 }
			 if(!values[4].equals("null")){
				 BDATE = values[4];
			 }
			 if(!values[5].equals("null")){
				 ADDRESS = values[5];
			 }
			 if(!values[6].equals("null")){
				 SEX = values[6];
			 }
			 if(!(values[7].equals("null"))){
				  SALARY = Integer.parseInt(values[7]);
			 }
			 if(!values[8].equals("null")){
				 SUPER_SSN = values[8];
			 }
			 if(!values[9].equals("null")){
				 DNO = Integer.parseInt(values[9]);
			 }
			
				try {
					dbConnection = getDBConnection();
					preparedStatement = dbConnection.prepareStatement(insertTableSQL);
					
					preparedStatement.setString(1, FNAME);
					preparedStatement.setString(2, MINIT);
					preparedStatement.setString(3, LNAME);
					preparedStatement.setString(4, SSN);
					preparedStatement.setDate(5, getSqlDate(BDATE));
					preparedStatement.setString(6, ADDRESS);
					preparedStatement.setString(7, SEX);
					preparedStatement.setInt(8, SALARY);
					preparedStatement.setString(9, SUPER_SSN);
					preparedStatement.setInt(10, DNO);
					
					// execute insert SQL statement
					preparedStatement.executeUpdate();
					
					values = null;
					System.out.println("Record is inserted into EMPLOYEE table!");
								
				} catch (SQLException e) {
					
					System.out.println(e.getMessage());
					
				} finally {
					
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					
					if (dbConnection != null) {
						dbConnection.close();
					}
					
				}
			 
		}
		bufferReaderObject.close();
	}

	/* Method to insert data into Department Table */
	private static void insertRecordIntoDepartment() throws SQLException, NumberFormatException, IOException, ParseException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO DEPARTMENT"
				+ "(DNAME,DNUMBER,MGR_SSN,MGR_START_DATE) VALUES" + "(?,?,?,?)";
		
		File fileName = new File("E:\\Courses\\Summer-2017\\DB2\\Project 2\\DEPARTMENT.txt");
		
		FileInputStream fileInputStreamObject = new FileInputStream(fileName);
		String values[] = null;

		// Construct BufferedReader from InputStreamReader
		BufferedReader bufferReaderObject = new BufferedReader(new InputStreamReader(fileInputStreamObject));

		String inputLine = null;
		while ((inputLine = bufferReaderObject.readLine()) != null) {
			 values = inputLine.replace("'","").split(", ");
			 
			 String DNAME = null;
			 Integer DNUMBER = null;
			 String MGR_SSN = null;
			 String MGR_START_DATE = null;
			 
			 
			 if(!values[0].equals("null")){
				 DNAME = values[0];
			 }
			 if(!values[1].equals("null")){
				 DNUMBER = Integer.parseInt(values[1]);
			 }
			 if(!values[2].equals("null")){
				 MGR_SSN = values[2];
			 }
			 if(!values[3].equals("null")){
				 MGR_START_DATE = values[3];
			 }
		
			
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, DNAME);
			preparedStatement.setInt(2, DNUMBER);
			preparedStatement.setString(3, MGR_SSN);
			preparedStatement.setDate(4, getSqlDate(MGR_START_DATE));

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into Department table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		} 
		}
		bufferReaderObject.close();

	}

	/* Method to insert data into Department Location Table */
	private static void insertRecordIntoDept_Loc() throws SQLException, ParseException, IOException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO DEPT_LOCATIONS"
				+ "(DNUMBER,DLOCATION) VALUES" + "(?,?)";
		
		File fileName = new File("E:\\Courses\\Summer-2017\\DB2\\Project 2\\DEPT_LOCATIONS.txt");
		
		FileInputStream fileInputStreamObject = new FileInputStream(fileName);
		String values[] = null;

		// Construct BufferedReader from InputStreamReader
		BufferedReader bufferReaderObject = new BufferedReader(new InputStreamReader(fileInputStreamObject));

		String inputLine = null;
		while ((inputLine = bufferReaderObject.readLine()) != null) {
			 values = inputLine.replace("'","").split(", ");
			 
			 Integer DNUMBER = null;
			 String DLOCATION = null;
			 
			 
			 if(!values[0].equals("null")){
				 DNUMBER = Integer.parseInt(values[0]);
			 }
			 if(!values[1].equals("null")){
				 DLOCATION = values[1];
			 }

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, DNUMBER);
			preparedStatement.setString(2, DLOCATION);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into Department Location table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		}
		bufferReaderObject.close();

	}

	/* Method to insert data into Project Table */
	private static void insertRecordIntoProject() throws SQLException, ParseException, NumberFormatException, IOException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO PROJECT"
				+ "(PNAME,PNUMBER,PLOCATION,DNUM) VALUES" + "(?,?,?,?)";
		
	File fileName = new File("E:\\Courses\\Summer-2017\\DB2\\Project 2\\PROJECT.txt");
		
		FileInputStream fileInputStreamObject = new FileInputStream(fileName);
		String values[] = null;

		// Construct BufferedReader from InputStreamReader
		BufferedReader bufferReaderObject = new BufferedReader(new InputStreamReader(fileInputStreamObject));

		String inputLine = null;
		while ((inputLine = bufferReaderObject.readLine()) != null) {
			 values = inputLine.replace("'","").split(", ");
			 
			 String PNAME = null;
			 Integer PNUMBER = null;
			 String PLOCATION = null;
			 Integer DNUM = null;
			 
			 
			 if(!values[0].equals("null")){
				 PNAME = values[0];
			 }
			 if(!values[1].equals("null")){
				 PNUMBER = Integer.parseInt(values[1]);
			 }
			 if(!values[2].equals("null")){
				 PLOCATION = values[2];
			 }
			 if(!values[3].equals("null")){
				 DNUM = Integer.parseInt(values[3]);
			 }

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, PNAME);
			preparedStatement.setInt(2, PNUMBER);
			preparedStatement.setString(3, PLOCATION);
			preparedStatement.setInt(4, DNUM);
			

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into Project table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		}
		bufferReaderObject.close();
	}

	/* Method to insert data into WORKS_ON Table */
	private static void insertRecordIntoWorks_On() throws SQLException, ParseException, IOException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO WORKS_ON"
				+ "(ESSN,PNO,HOURS) VALUES" + "(?,?,?)";
		
		File fileName = new File("E:\\Courses\\Summer-2017\\DB2\\Project 2\\WORKS_ON.txt");
		
		FileInputStream fileInputStreamObject = new FileInputStream(fileName);
		String values[] = null;

		// Construct BufferedReader from InputStreamReader
		BufferedReader bufferReaderObject = new BufferedReader(new InputStreamReader(fileInputStreamObject));

		String inputLine = null;
		while ((inputLine = bufferReaderObject.readLine()) != null) {
			 values = inputLine.replace("'","").split(", ");
			 
			 String ESSN = null;
			 Integer PNO = null;
			 Double HOURS = null;
			 
			 
			 if(!values[0].equals("null")){
				 ESSN =  values[0];
			 }
			 if(!values[1].equals("null")){
				 PNO = Integer.parseInt(values[1]);
			 }
			 if(!values[2].equals("null")){
				 HOURS = Double.parseDouble(values[2]);
			 }

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, ESSN);
			preparedStatement.setInt(2, PNO);
			preparedStatement.setDouble(3, HOURS);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into WORKS_ON table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		
	}
		bufferReaderObject.close();

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	/* Alter table employee to remove circular reference problem between employee and department table*/
	private static void alterTable() throws SQLException{
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String alterTableSQL = "ALTER TABLE EMPLOYEE ADD FOREIGN KEY "+"("+"DNO"+")"+"REFERENCES DEPARTMENT"+"("+"DNUMBER"+")";
		

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(alterTableSQL);
			
			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Table Alter Successfully");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static java.sql.Date getSqlDate(String date) throws ParseException {
		 SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
	        Date parsed = format.parse(date);
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
			return sql;
	}
}