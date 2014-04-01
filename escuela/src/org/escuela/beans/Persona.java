package org.escuela.beans;

import java.util.Date;

public class Persona implements Identificable{

	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private Long id;
	
	public Persona(Date fecha, String nuevoApe){
		fechaNacimiento = fecha;
		apellido = nuevoApe;
	}
	
	public Persona(){
		
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
	public void setFechaNacimiento(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
}