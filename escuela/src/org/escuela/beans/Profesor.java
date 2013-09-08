package org.escuela.beans;

import java.util.Date;

public class Profesor {
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private Long id;
	
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
		return;	
	}
	public String getApellido(){
		return apellido;
	}
	public void setApellido(String apellido){
		this.apellido = apellido;
		return;
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
