package org.escuela.beans;

import java.util.Date;

public class Alumno {
	private Curso grado;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private Long id;
	
	public Curso getGrado(){
		return grado;
	}
	public void setGrado(Curso grado){
		this.grado = grado;
	}
	public String getApellido(){
		return apellido;
	}
	public void setApellido(String apellido){
		this.apellido = apellido;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public Date getFechaNacimiento(){
		return fechaNacimiento;
	}
	public void setApellido(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
}
