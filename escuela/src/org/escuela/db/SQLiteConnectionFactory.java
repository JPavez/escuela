package org.escuela.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Constructs a single connection to SQLite.
 * No Suitable for multiple threads.
 * @author javier
 *
 */
public class SQLiteConnectionFactory {
	private static SQLiteConnectionFactory instance;
	private Connection connection;
	
	
	private SQLiteConnectionFactory(){
		InputStream propertiesStream = this.getClass().getResourceAsStream("/org/escuela/resources/escuela.properties");
		Properties properties = new Properties();
		try {
			properties.load(propertiesStream);
			System.out.println(properties.getProperty("db.location"));
			connection = DriverManager.getConnection("jdbc:sqlite:" + properties.getProperty("db.location"));
			Statement statement = connection.createStatement();
			String checkQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='cursos'";
			ResultSet resultSet = statement.executeQuery(checkQuery);
			if(!resultSet.next()){
				//SCHEMA NOT CREATED!
				createSchema();
			}
			resultSet.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createSchema() throws IOException, SQLException {
		ScriptRunner scriptRunner = new ScriptRunner(connection, true, true);
		InputStream sqlFileStream = this.getClass().getResourceAsStream("/org/escuela/resources/db.sql");
		InputStreamReader reader = new InputStreamReader(sqlFileStream); 
		scriptRunner.runScript(reader);
	}

	/**
	 * Retrieves an instance of SQLiteConnectionFactory.
	 * @return
	 */
	public static SQLiteConnectionFactory getInstance(){
		if(instance == null){
			instance = new SQLiteConnectionFactory();
		}
		return instance;
	}

	/**
	 * Returns an initialized connection to SQLite or null if it failed
	 * to create a connection
	 * @return Connection 
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
