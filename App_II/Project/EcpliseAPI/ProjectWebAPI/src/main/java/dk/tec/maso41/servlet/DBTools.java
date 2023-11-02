package dk.tec.maso41.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dk.tec.maso41.Person;

public class DBTools 
{
	//private String conStr = "jdbc:sqlserver://localhost;databaseName=ProjectAPI;encrypt=true;trustServerCertificate=true";
	//private String conStr = "jdbc:sqlserver://DESKTOP-3L6VNNS:1433;databaseName=ProjectAPI;user=sa;password=test;";
    private String conStr = "jdbc:sqlserver://localhost:1433;databaseName=ProjectAPI;encrypt=true;trustServerCertificate=true";
	Connection con;
	Statement stmt;
	
	public DBTools()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void connect() 
	{
		try {
			con = DriverManager.getConnection(conStr, "sa", "test");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	public Person getPersonById(int id) 
	{
		connect();
		String selectStr = "Select * from Person where id = " + id;
		System.out.print(selectStr);
		Person person = new Person();
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			if(result.next())
			{
				person.setId(result.getInt("Id"));
				person.setName(result.getString("Name"));
				person.setAddress(result.getString("Address"));
				person.setPhone(result.getString("Phone"));
				
				con.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return person;
	}	
}
