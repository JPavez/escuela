package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericStatement<T> {
	private String sql;
	private ParameterPopulator populator;
	private ResultHandler<T> resultHandler;
	
	public T run() throws SQLException{
		PreparedStatement prepStmt = SQLiteConnectionFactory.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		populator.populateParameters(prepStmt, SQLiteConnectionFactory.getInstance().getConnection());
		return resultHandler.handleResult(prepStmt);
	}
	public GenericStatement<T> handler(ResultHandler<T> handler) {
		this.resultHandler = handler;
		return this;
	}
	public GenericStatement<T> statement(String sql) {
		this.sql = sql;
		return this;
	}
	public GenericStatement<T> populator(ParameterPopulator populator) {
		this.populator = populator;
		return this;
	}
}
