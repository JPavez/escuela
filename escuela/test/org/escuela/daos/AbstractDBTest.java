package org.escuela.daos;

import java.sql.SQLException;

import org.escuela.db.SQLiteConnectionFactory;
import org.junit.After;

public class AbstractDBTest {

	@After
	public void tearDown() throws SQLException{
		SQLiteConnectionFactory.getInstance().getConnection().prepareStatement("DELETE FROM cursos_materias").executeUpdate();
	}
}
