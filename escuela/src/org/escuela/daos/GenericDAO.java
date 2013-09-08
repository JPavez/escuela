package org.escuela.daos;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public void create(T entity) throws SQLException;
	public boolean update(T entity) throws SQLException;
	public boolean delete(T entity) throws SQLException;
	public List<T> search(T entity) throws SQLException;
}
