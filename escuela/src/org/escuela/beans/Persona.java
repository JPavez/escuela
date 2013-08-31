package org.escuela.beans;

import java.util.Date;

public class Persona {

	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	
	
	public String getApellido(){
		return apellido;
	}
	public void setApellido(String apellido){
		this.apellido = apellido;
		return;
	}

	public String getNombre(){
		return nombre;
	}
	public void setNombreo(String nombre){
		this.nombre = nombre;
	}

	public Date getFechaNacimiento(){
		return fechaNacimiento;
	}
	public void setApellido(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}

}
