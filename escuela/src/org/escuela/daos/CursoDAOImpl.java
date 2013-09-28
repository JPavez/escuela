package org.escuela.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.escuela.beans.Curso;
import org.escuela.db.CountModificationsResultHandler;
import org.escuela.db.GeneratedIdResultHandler;
import org.escuela.db.GenericStatement;
import org.escuela.db.ParameterPopulator;
import org.escuela.db.ProjectableResultHandler;

import com.google.common.collect.Lists;

public class CursoDAOImpl implements GenericDAO<Curso> {

	@Override
	public void create(final Curso entity) throws SQLException {
		String sql = "INSERT INTO cursos (id, name) VALUES (NULL, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException {
				prepStmt.setString(1, entity.getNombre());
			}
		}).handler(new GeneratedIdResultHandler(entity)).run();
	}

	@Override
	public boolean update(final Curso entity) throws SQLException {
		String sql = "UPDATE cursos SET name = ? WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setString(1, entity.getNombre());
				prepStmt.setLong(2, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public boolean delete(final Curso entity) throws SQLException {
		String sql = "DELETE FROM cursos WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
				prepStmt.setLong(1, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public List<Curso> search(final Curso entity) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM cursos");
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
			sqlBuilder.append("name = ?");
		}
		GenericStatement<ResultSet> genericStatement = new GenericStatement<ResultSet>();
		ResultSet resultSet = genericStatement.statement(sqlBuilder.toString()).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt) throws SQLException{
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
		return parseCursos(resultSet);
	}

	private List<Curso> parseCursos(ResultSet resultSet) throws SQLException {
		List<Curso> cursos = Lists.newArrayList();
		while(resultSet.next()){
			cursos.add(parseCurso(resultSet));
		}
		return cursos;
	}

	private Curso parseCurso(ResultSet resultSet) throws SQLException {
		Curso curso = new Curso();
		curso.setId(resultSet.getLong(1));
		curso.setNombre(resultSet.getString(2));
		return curso;
	}

}
