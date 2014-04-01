package org.escuela.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.escuela.beans.Curso;
import org.escuela.beans.Materia;
import org.escuela.beans.Persona;
import org.escuela.beans.Profesor;
import org.escuela.daos.CursoDAOImpl;
import org.escuela.daos.GenericDAO;
import org.escuela.daos.PersonaDAOImpl;

public class EscuelaReader {
	private BufferedReader br;
	private GenericDAO<Curso> cursoDao;
	private GenericDAO<Persona> personaDao;
	private GenericDAO<Materia> materiaDao;
	

	public EscuelaReader() {
		InputStreamReader isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		cursoDao = new CursoDAOImpl();
		personaDao = new PersonaDAOImpl();
	}
	public void menu(){
		System.out.println("Bienvenido. Elija opcion a realizar:");
		System.out.println("0_ VER CURSO");
		System.out.println("1_ Ingresar PERSONA");
		System.out.println("2_ Borrar PERSONA");
		System.out.println("3_ Ingresar MATERIA");
		System.out.println("4_ Borrar MATERIA");
		System.out.println("5_ Ingresar CURSO");
		System.out.println("6_ Borrar CURSO");
		System.out.println("7_ SALIR");
	}
	
	//if 0
	public void verCurso() throws SQLException{
		List<Curso> cursos = cursoDao.search(null);
		for (int i = 0; i < cursos.size(); i++) {
				System.out.println("mira el curso "+ cursos.get(i).getNombre());
		}
	}
	//if 1
	public void ingresoPersona() throws IOException, SQLException, ParseException{
		String ape = br.readLine();
		String nom = br.readLine();
		String fecha = br.readLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Persona nuevaPersona = new Persona();
		nuevaPersona.setNombre(nom);
		nuevaPersona.setApellido(ape);
		nuevaPersona.setFechaNacimiento(sdf.parse(fecha));
		personaDao.create(nuevaPersona);
	}
	//if 2
	public void borrarPersona() throws IOException, SQLException{
		String perselid = br.readLine();
		Long personaElim = Long.parseLong(perselid);
		Persona persona = new Persona();
		persona.setId(personaElim);
		personaDao.delete(persona);
	}
	//if 3
	public void ingresoMateria() throws IOException, SQLException{	
		List <Profesor> lProfe = new ArrayList<Profesor>();
		List <Curso> lCurso = new ArrayList<Curso>();
		String nMat = br.readLine();
		String nProfe = br.readLine();
		String nCurso = br.readLine();
		Materia materia = new Materia();
		Profesor prof = new Profesor();
		Curso curso = new Curso();
		curso.setNombre(nCurso);
		materia.setNombre(nMat);
		lCurso.add(curso);
		prof.setNombre(nProfe);
		lProfe.add(prof);
		materiaDao.create(materia);
	}
	public void borrarMateria() throws IOException, SQLException{
		String input = br.readLine();
		Materia idMat = new Materia();
		idMat.setId(Long.parseLong(input));
		materiaDao.delete(idMat);
	}
	//if 5
	public void ingresoCurso() throws IOException, SQLException{
		String input = br.readLine();
		Curso curso = new Curso();
		curso.setNombre(input);
		cursoDao.create(curso);
	}
	//if 6
	public void borrarCurso() throws IOException, SQLException{
		String idel = br.readLine();
		Long cursoElim = Long.parseLong(idel);
		Curso curso = new Curso();
		curso.setId(cursoElim);
		cursoDao.delete(curso);
	}
	
	public void leerOpcion() throws IOException, SQLException, ParseException {
		String input = br.readLine();
		if(input.equals("0")){
			verCurso();
		}else if(input.equals("1")){
			ingresoPersona();
		}else if(input.equals("2")){
			borrarPersona();
		}else if(input.equals("3")){
			ingresoMateria();
		}else if(input.equals("4")){
			//
		}else if(input.equals("5")){
			ingresoCurso();
		}else if(input.equals("6")){
			borrarCurso();
		}else if(input.equals("7")){
			System.exit(0);
		}
	}
}
