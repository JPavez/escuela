package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectableResultHandler implements ResultHandler<ResultSet> {

	@Override
	public ResultSet handleResult(PreparedStatement prep) throws SQLException {
		return prep.executeQuery();
	}

}
