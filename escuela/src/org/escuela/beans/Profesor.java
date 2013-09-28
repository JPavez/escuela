package org.escuela.beans;

import java.util.List;

public class Profesor extends Persona{
	private List<Materia> materias;
	
	
	public List<Materia> getMaterias(){
		return materias;
	}
	public void setMaterias(List<Materia> materias){
		this.materias = materias;
		return;
	}
	
	
}