package org.escuela.daos;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.escuela.beans.Curso;
import org.escuela.beans.Materia;
import org.escuela.beans.Profesor;
import org.escuela.db.CountModificationsResultHandler;
import org.escuela.db.GeneratedIdResultHandler;
import org.escuela.db.GenericStatement;
import org.escuela.db.ParameterPopulator;
import org.escuela.db.ProjectableResultHandler;

import com.google.common.base.Function;
import com.google.common.collect.Lists;


public class ProfesorDAOImpl implements GenericDAO<Profesor>{
	private GenericDAO<Materia> materiaDAO;
	
	public ProfesorDAOImpl(){
		this.materiaDAO = new MateriaDAOImpl();
	}

	@Override
	public void create(final Profesor entity) throws SQLException {
		String sql = "INSERT INTO profesor(id, nombre, apellido, fecha_nacimiento) VALUES (NULL, ?, ?, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setNull(1, Types.INTEGER);
				prepStmt.setString(2, entity.getNombre());
				prepStmt.setString(3, entity.getApellido());
				prepStmt.setDate(4, new java.sql.Date(entity.getFechaNacimiento().getTime()));
			}
		}).handler(new GeneratedIdResultHandler<Profesor>(entity)).run();
	}

	@Override
	public boolean update(final Profesor entity) throws SQLException {
		String sql = "UPDATE profesor SET name = ?, apellido = ?, fecha_nacimiento = ? WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setString(1, entity.getNombre());
				prepStmt.setString(2, entity.getApellido());
				prepStmt.setDate(3, new java.sql.Date(entity.getFechaNacimiento().getTime()));
				prepStmt.setLong(4, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public boolean delete(final Profesor entity) throws SQLException {
		String sql = "DELETE FROM profesor WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setLong(1, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public List<Profesor> search(final Profesor entity) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder("SELECT p.* FROM profesor p ");
		boolean needsAnd = false;
		if(entity!= null && entity.getMaterias() != null && !entity.getMaterias().isEmpty()){
			sqlBuilder.append("INNER JOIN profesores_materias pm ON (p.id = pm.id_profesor) ");
		}
		if(entity!= null && entity.getId() != null){
			sqlBuilder.append("WHERE id = ? ");
			needsAnd = true;
		}
		if(entity!= null && entity.getNombre() != null){
			addSqlNexus(sqlBuilder, needsAnd);
			sqlBuilder.append("nombre = ? ");
		}
		if(entity!= null && entity.getApellido() != null){
			addSqlNexus(sqlBuilder, needsAnd);
			sqlBuilder.append("apellido = ? ");
		}
		if(entity!= null && entity.getFechaNacimiento() != null){
			addSqlNexus(sqlBuilder, needsAnd);
			sqlBuilder.append("fecha_nacimiento = ? ");
		}
		if(entity!= null && entity.getMaterias() != null && !entity.getMaterias().isEmpty()){
			addSqlNexus(sqlBuilder, needsAnd);
			sqlBuilder.append("pm.id_materia IN (?)");
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
					prepStmt.setString(param, entity.getNombre());
					param++;
				}
				if(entity!= null && entity.getApellido() != null){
					prepStmt.setString(param, entity.getApellido());
					param++;
				}
				if(entity!= null && entity.getFechaNacimiento() != null){
					prepStmt.setDate(param, new java.sql.Date(entity.getFechaNacimiento().getTime()));
					param++;
				}
				if(entity!= null && entity.getMaterias() != null && !entity.getMaterias().isEmpty()){
					List<Long> transform = Lists.transform(entity.getMaterias(), new Function<Materia, Long>(){
						@Override
						public Long apply(Materia materia) {
							return materia.getId();
						}
					});
					Array materiasArray = connection.createArrayOf("INTEGER", transform.toArray());
					prepStmt.setArray(param, materiasArray);
					param++;
				}
			}
		}).handler(new ProjectableResultHandler()).run();
		return parseProfesores(resultSet);
	}

	private List<Profesor> parseProfesores(ResultSet resultSet) throws SQLException {
		List<Profesor> profesores = Lists.newArrayList();
		while(resultSet.next()){
			profesores.add(parseProfesor(resultSet));
		}
		return profesores;
	}

	private Profesor parseProfesor(ResultSet resultSet) throws SQLException {
		Profesor profesor = new Profesor();
		profesor.setId(resultSet.getLong(1));
		profesor.setNombre(resultSet.getString(2));
		profesor.setApellido(resultSet.getString(3));
		profesor.setFechaNacimiento(new Date(resultSet.getDate(4).getTime()));
		Curso grado = new Curso();
		grado.setId(resultSet.getLong(5));
		Materia materia = new Materia();
		List<Profesor> profesores = Lists.newArrayList(profesor);
		materia.setProfesores(profesores);
		profesor.setMaterias(materiaDAO.search(materia));
		return profesor;
	}

	private void addSqlNexus(StringBuilder sqlBuilder, boolean needsAnd) {
		if(needsAnd){
			sqlBuilder.append("AND ");
		}else{
			sqlBuilder.append("WHERE ");
		}
	}

}
