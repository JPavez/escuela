package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterPopulator {
	public void populateParameters(PreparedStatement prepStmt) throws SQLException; 
}
