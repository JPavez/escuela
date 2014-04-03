package org.escuela.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterPopulator {
	public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException; 
}
