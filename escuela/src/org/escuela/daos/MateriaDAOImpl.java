package org.escuela.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.escuela.beans.Materia;
import org.escuela.db.CountModificationsResultHandler;
import org.escuela.db.GeneratedIdResultHandler;
import org.escuela.db.GenericStatement;
import org.escuela.db.ParameterPopulator;

public class MateriaDAOImpl implements GenericDAO<Materia> {

	@Override
	public void create(final Materia entity) throws SQLException{
		String sql = "INSERT INTO materias VALUES (?, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setNull(1, Types.INTEGER);
				prepStmt.setString(2, entity.getNombre());
			}
		}).handler(new GeneratedIdResultHandler<Materia>(entity)).run();
	}

	@Override
	public boolean update(final Materia entity) throws SQLException {
		String sql = "UPDATE materias SET name = ? WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setLong(1, entity.getId());
				prepStmt.setString(2, entity.getNombre());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public boolean delete(final Materia entity) throws SQLException{
		String sql = "DELETE FROM materias WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator() {
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setLong(1, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public List<Materia> search(Materia entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
