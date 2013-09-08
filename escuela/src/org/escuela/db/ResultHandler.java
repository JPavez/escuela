package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ResultHandler<T> {
	public T handleResult(PreparedStatement prep) throws SQLException;
}
