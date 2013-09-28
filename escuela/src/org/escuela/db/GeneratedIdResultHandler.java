package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.escuela.beans.Identificable;

public class GeneratedIdResultHandler<T extends Identificable> extends CountModificationsResultHandler {
	private T entity;
	
	public GeneratedIdResultHandler(T entity){
		this.entity = entity;
	}

	@Override
	public Integer handleResult(PreparedStatement prep) throws SQLException {
		Integer result = super.handleResult(prep);
		ResultSet resultSet = prep.getGeneratedKeys();
		resultSet.next();
		entity.setId(resultSet.getLong(1));
		return result;
	}
}
