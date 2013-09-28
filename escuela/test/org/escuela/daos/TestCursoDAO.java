package org.escuela.daos;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.escuela.beans.Curso;
import org.junit.Test;

public class TestCursoDAO {

	@Test
	public void testCurso() throws SQLException{
		GenericDAO<Curso> cursoDAO = new CursoDAOImpl();
		Curso curso = new Curso();
		curso.setNombre("A");
		cursoDAO.create(curso);
		for(Curso c : cursoDAO.search(null)){
			assertEquals("Curso id must match", curso.getId(), c.getId());
			assertEquals("Curso id must match", curso.getNombre(), c.getNombre());
		}
	}
}
