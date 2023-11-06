package dk.tec.maso41.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dk.tec.maso41.Haircolor;
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
	
	
	public Person getPersonById(int id) {
		/**
		 * Gets a specific recordset from the DB, based on the ID 
		 * sent along in the request (path)
		 */
		connect();
		String selectStr = "Select * from Person where id = " + id;
		Person person = new Person();
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			if(result.next())
			{
				person.setId(result.getInt("Id"));
				person.setName(result.getString("Name"));
				person.setAddress(result.getString("Address"));
				person.setPhone(result.getString("Phone"));
				person.setNote(result.getString("Note"));
				person.setFavorite(result.getBoolean("Favorite"));
				person.setHaircolor_id(result.getInt("Haircolor_id"));
				con.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.print(person.toString());
		return person;
	}	
	
	public List<Person> getAllPerson() {
		/**
		 * Gets all Person objects in Database
		 * Returns them as a List
		 */
		connect();
		String selectStr = "SELECT * FROM Person";
		List<Person> people = new ArrayList<>();
		try {
	        ResultSet result = stmt.executeQuery(selectStr);
	        while (result.next()) {
	            Person person = new Person();
	            person.setId(result.getInt("Id"));
	            person.setName(result.getString("Name"));
	            person.setAddress(result.getString("Address"));
	            person.setPhone(result.getString("Phone"));
				person.setNote(result.getString("Note"));
				person.setFavorite(result.getBoolean("Favorite"));
				person.setHaircolor_id(result.getInt("Haircolor_id"));
	            people.add(person);
	        	}
	
	        con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	
		    return people;
		
	}
	
	
	
	public Integer addPerson(Person person) {
		/**
		 * Prepares an SQL query with open variables, which are subsequently populated,
		 * using the get-methods available in the Person class.
		 */
		Integer pId = null;
		
		connect();
		String insertStr = "INSERT INTO Person (name, address, phone, note, favorite, haircolor_id) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = con.prepareStatement(insertStr, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getAddress());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getNote());
            preparedStatement.setBoolean(5, person.getFavorite());
            preparedStatement.setInt(6, person.getHaircolor_id());
            preparedStatement.executeUpdate();
            ResultSet genKey = preparedStatement.getGeneratedKeys();
            if (genKey.next()) {
            	pId = genKey.getInt(1);
            }
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return pId;
	}
	
	public void delPerson(int id) {
		connect();
		String delStr = "DELETE FROM Person WHERE id = " + id;

		try {
			stmt.executeQuery(delStr);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void updatePerson(Person person) {
		connect();
		int id = person.getId();
		String updateStr = "UPDATE Person SET name = ?, address = ?, phone = ?, note = ?, favorite = ?, haircolor_id = ? where ID = " + id;
		try (PreparedStatement statement = con.prepareStatement(updateStr)) {
			statement.setString(1, person.getName());
			statement.setString(2, person.getAddress());
			statement.setString(3, person.getPhone());
			statement.setString(4, person.getPhone());
			statement.setBoolean(5, person.getFavorite());
			statement.setInt(6, person.getHaircolor_id());
			statement.executeUpdate();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Haircolor> getAllHaircolor() {
		connect();
		String selectStr = "SELECT * FROM Haircolor";
		List<Haircolor> haircolors = new ArrayList<>();
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			while (result.next()) {
				Haircolor hc = new Haircolor();
				hc.setId(result.getInt("Id"));
				hc.setName(result.getString("Name"));
				haircolors.add(hc);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return haircolors;
	}
	
	public Haircolor getHaircolorById(int id) {
		/**
		 * Gets a specific recordset from the DB, based on the ID 
		 * sent along in the request (path)
		 */
		connect();
		System.out.println(id);
		String selectStr = "Select * from Haircolor where id = " + id;
		Haircolor haircolor = new Haircolor();
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			if(result.next())
			{
				haircolor.setId(result.getInt("Id"));
				haircolor.setName(result.getString("Name"));
				con.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.print(haircolor.toString());
		return haircolor;
	}	
	
}
