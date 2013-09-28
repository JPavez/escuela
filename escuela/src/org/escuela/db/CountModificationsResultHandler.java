package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountModificationsResultHandler implements ResultHandler<Integer> {

	@Override
	public Integer handleResult(PreparedStatement prep) throws SQLException {
		return prep.executeUpdate();
	}

}
