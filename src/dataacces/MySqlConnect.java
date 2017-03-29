/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacces;

/**
 *
 * @author maria
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
public class MySqlConnect {
    private Connection connection;

	/**
	 * Singleton pattern implemented
	 */
	private static Connection instance;
	public  Connection getInstance(){
		
		return connection;
	}
	
	/**
	 * Constructor that calls the connect method
	 */
	public MySqlConnect() {
		connect();
	}

	/**
	 * Creates the connection to the given database
	 */
	public boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
                        return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
                        return false;
		}
	}
	
	/**
	 * Method that returns a ResultSet after a searched query
	 * @param query - the given query to be executed
	 * @return the execution of the query
	 */
	public ResultSet get(String query){
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			return statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
                
	}

	/**
	 * Method that executes an update of the database
	 * @param sql the sql to be updated
	 * @return the execution
	 */
	public int update(String sql) {
		try {
			return connection.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
    
}
