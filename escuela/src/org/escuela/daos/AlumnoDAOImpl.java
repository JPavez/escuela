package org.escuela.daos;

import java.sql.SQLException;
import java.util.List;

import org.escuela.beans.Alumno;

public class AlumnoDAOImpl implements GenericDAO<Alumno>{

	@Override
	public void create(final Alumno entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(final Alumno entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(final Alumno entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Alumno> search(Alumno entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
