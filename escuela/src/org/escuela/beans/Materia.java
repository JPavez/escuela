package org.escuela.beans;

import java.util.List;

public class Materia {
	private String nombre;
	private Long id;
	private List<Profesor> profesores;
	private List<Curso> cursos;
	
	
	public List<Profesor> getProfesores(){
		return profesores;
	}
	public void setProfesores(List<Profesor> profesores){
		this.profesores = profesores;
	}
	public List<Curso> getProCursos(){
		return cursos;
	}
	public void setCursos(List<Curso> cursos){
		this.cursos = cursos;
	}
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
}
