package org.escuela.beans;


public class Alumno extends Persona{
	private Curso grado;
	
	public Curso getGrado(){
		return grado;
	}
	public void setGrado(Curso grado){
		this.grado = grado;
	}
}
