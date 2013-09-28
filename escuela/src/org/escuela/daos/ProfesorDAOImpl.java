package org.escuela.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.escuela.beans.Profesor;
import org.escuela.db.CountModificationsResultHandler;
import org.escuela.db.GenericStatement;
import org.escuela.db.ParameterPopulator;


public class ProfesorDAOImpl implements GenericDAO<Profesor>{

	@Override
	public void create(final Profesor entity) throws SQLException {
		String sql = "INSERT INTO profesor VALUES (?, ?, ?, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setNull(1, Types.INTEGER);
				prepStmt.setString(2, entity.getNombre());
				prepStmt.setString(3, entity.getApellido());
				prepStmt.setDate(4, new java.sql.Date(entity.getFechaNacimiento().getTime()));
			}
		}).handler(new CountModificationsResultHandler()).run();
	}

	@Override
	public boolean update(final Profesor entity) throws SQLException {
		String sql = "UPDATE profesor SET name = ?, apellido = ?, fecha_nacimiento = ? WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setString(1, entity.getNombre());
				prepStmt.setString(2, entity.getApellido());
				prepStmt.setDate(3, new java.sql.Date(entity.getFechaNacimiento().getTime()));
				prepStmt.setLong(4, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public boolean delete(Profesor entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Profesor> search(Profesor entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
