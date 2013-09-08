package org.escuela.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenericStatement<T> {
	private String statement;
	private ParameterPopulator populator;
	private ResultHandler<T> resultHandler;
	
	public T run() throws SQLException{
		PreparedStatement prepStmt = SQLiteConnectionFactory.getInstance().getConnection().prepareStatement(statement);
		populator.populateParameters(prepStmt);
		return resultHandler.handleResult(prepStmt);
	}
	public GenericStatement<T> handler(ResultHandler<T> handler) {
		this.resultHandler = handler;
		return this;
	}
	public GenericStatement<T> statement(String statement) {
		this.statement = statement;
		return this;
	}
	public GenericStatement<T> populator(ParameterPopulator populator) {
		this.populator = populator;
		return this;
	}
}
