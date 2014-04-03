package org.escuela.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.escuela.beans.Materia;
import org.escuela.beans.Profesor;
import org.escuela.db.CountModificationsResultHandler;
import org.escuela.db.GeneratedIdResultHandler;
import org.escuela.db.GenericStatement;
import org.escuela.db.ParameterPopulator;
import org.escuela.db.ProjectableResultHandler;

import com.google.common.collect.Lists;

public class MateriaDAOImpl implements GenericDAO<Materia> {

	@Override
	public void create(final Materia materia) throws SQLException{
		String sql = "INSERT INTO materias (id, nombre) VALUES (?, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setNull(1, Types.INTEGER);
				prepStmt.setString(2, materia.getNombre());
			}
		}).handler(new GeneratedIdResultHandler<Materia>(materia)).run();
	}
	
	public void addProfesor(final Materia materia, final Profesor profesor) throws SQLException{
		String sql = "INSERT INTO profesores_materias (id_profesor, id_materia) VALUES (?, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setLong(1, profesor.getId());
				prepStmt.setLong(2, materia.getId());
			}
		}).handler(new GeneratedIdResultHandler<Materia>(materia)).run();
	}

	@Override
	public boolean update(final Materia entity) throws SQLException {
		String sql = "UPDATE materias SET name = ? WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
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
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setLong(1, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public List<Materia> search(final Materia entity) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM materias");
		boolean needsAnd = false;
		if(entity!= null && entity.getId() != null){
			sqlBuilder.append("WHERE id = ? ");
			needsAnd = true;
		}
		if(entity!= null && entity.getNombre() != null){
			if(needsAnd){
				sqlBuilder.append("AND ");
			}else{
				sqlBuilder.append("WHERE ");
			}
			sqlBuilder.append("nombre = ?");
		}
		GenericStatement<ResultSet> genericStatement = new GenericStatement<ResultSet>();
		ResultSet resultSet = genericStatement.statement(sqlBuilder.toString()).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				int param = 1;
				if(entity!= null && entity.getId() != null){
					prepStmt.setLong(param, entity.getId());
					param++;
				}
				if(entity!= null && entity.getNombre() != null){
					prepStmt.setString(param, entity.getNombre() + "%");
				}
			}
		}).handler(new ProjectableResultHandler()).run();
		return parseMaterias(resultSet);
	}

	private List<Materia> parseMaterias(ResultSet resultSet) throws SQLException {
		List<Materia> materias = Lists.newArrayList();
		while(resultSet.next()){
			materias.add(parseMateria(resultSet));
		}
		return materias;
	}

	private Materia parseMateria(ResultSet resultSet) throws SQLException {
		Materia materia = new Materia();
		materia.setId(resultSet.getLong(1));
		materia.setNombre(resultSet.getString(2));
		return materia;
	}
	
}
