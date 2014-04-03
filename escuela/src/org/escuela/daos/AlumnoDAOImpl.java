package org.escuela.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.escuela.beans.Alumno;
import org.escuela.beans.Curso;
import org.escuela.db.CountModificationsResultHandler;
import org.escuela.db.GeneratedIdResultHandler;
import org.escuela.db.GenericStatement;
import org.escuela.db.ParameterPopulator;
import org.escuela.db.ProjectableResultHandler;

import com.google.common.collect.Lists;

public class AlumnoDAOImpl implements GenericDAO<Alumno>{
	private GenericDAO<Curso> cursoDao;
	
	public AlumnoDAOImpl(){
		cursoDao = new CursoDAOImpl();
	}
	
	public AlumnoDAOImpl(GenericDAO<Curso> cursoDao){
		this.cursoDao = cursoDao; 
	}

	@Override
	public void create(final Alumno entity) throws SQLException {
		String sql = "INSERT INTO alumnos (id, nombre, apellido, fecha_nacimiento, id_curso) VALUES (NULL, ?, ?, ?, ?)";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException {
				prepStmt.setString(1, entity.getNombre());
				prepStmt.setString(2, entity.getApellido());
				prepStmt.setDate(3, new java.sql.Date(entity.getFechaNacimiento().getTime()));
				prepStmt.setLong(4, entity.getGrado().getId());
			}
		}).handler(new GeneratedIdResultHandler<Alumno>(entity)).run();
	}

	@Override
	public boolean update(final Alumno entity) throws SQLException {
		String sql = "UPDATE alumnos SET nombre = ?, apellido = ?, fecha_nacimiento = ?, id_curso = ? WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setString(1, entity.getNombre());
				prepStmt.setString(2, entity.getApellido());
				prepStmt.setDate(3, new java.sql.Date(entity.getFechaNacimiento().getTime()));
				prepStmt.setLong(4, entity.getGrado().getId());
				prepStmt.setLong(5, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public boolean delete(final Alumno entity) throws SQLException {
		String sql = "DELETE FROM alumnos WHERE id = ?";
		GenericStatement<Integer> genericStatement = new GenericStatement<Integer>();
		return genericStatement.statement(sql).populator(new ParameterPopulator(){
			@Override
			public void populateParameters(PreparedStatement prepStmt, Connection connection) throws SQLException{
				prepStmt.setLong(1, entity.getId());
			}
		}).handler(new CountModificationsResultHandler()).run().intValue() > 0;
	}

	@Override
	public List<Alumno> search(final Alumno entity) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM alumnos");
		boolean needsAnd = false;
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
		if(entity!= null && entity.getGrado() != null && entity.getGrado().getId() != null){
			addSqlNexus(sqlBuilder, needsAnd);
			sqlBuilder.append("id_curso = ? ");
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
				if(entity!= null && entity.getGrado() != null && entity.getGrado().getId() != null){
					prepStmt.setLong(param, entity.getGrado().getId());
					param++;
				}
			}
		}).handler(new ProjectableResultHandler()).run();
		return parseAlumnos(resultSet);
	}

	private List<Alumno> parseAlumnos(ResultSet resultSet) throws SQLException {
		List<Alumno> alumnos = Lists.newArrayList();
		while(resultSet.next()){
			alumnos.add(parseAlumno(resultSet));
		}
		return alumnos;
	}

	private Alumno parseAlumno(ResultSet resultSet) throws SQLException {
		Alumno alumno = new Alumno();
		alumno.setId(resultSet.getLong(1));
		alumno.setNombre(resultSet.getString(2));
		alumno.setApellido(resultSet.getString(3));
		alumno.setFechaNacimiento(new Date(resultSet.getDate(4).getTime()));
		Curso grado = new Curso();
		grado.setId(resultSet.getLong(5));
		alumno.setGrado(cursoDao.search(grado).get(0));
		return alumno;
	}

	private void addSqlNexus(StringBuilder sqlBuilder, boolean needsAnd) {
		if(needsAnd){
			sqlBuilder.append("AND ");
		}else{
			sqlBuilder.append("WHERE ");
		}
	}
}
