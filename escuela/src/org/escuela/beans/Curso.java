package org.escuela.beans;

import java.util.List;

public class Curso implements Identificable{
	private Long id;
	private String nombre;
	private List<Materia> materias;
	

	public List<Materia> getMaterias(){
		return materias;
	}
	
	public void setProfesores(List<Materia> materias){
		this.materias = materias;
	}
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}


}
