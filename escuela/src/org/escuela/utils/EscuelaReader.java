package org.escuela.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.escuela.beans.Alumno;
import org.escuela.beans.Curso;
import org.escuela.beans.Materia;
import org.escuela.beans.Profesor;
import org.escuela.daos.AlumnoDAOImpl;
import org.escuela.daos.CursoDAOImpl;
import org.escuela.daos.GenericDAO;
import org.escuela.daos.MateriaDAOImpl;
import org.escuela.daos.ProfesorDAOImpl;

public class EscuelaReader {
	private BufferedReader br;
	private GenericDAO<Curso> cursoDao;
	private GenericDAO<Alumno> alumnoDao;
	private GenericDAO<Profesor> profesorDao;
	private GenericDAO<Materia> materiaDao;
	

	public EscuelaReader() {
		InputStreamReader isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		cursoDao = new CursoDAOImpl();
		alumnoDao = new AlumnoDAOImpl();
		profesorDao = new ProfesorDAOImpl();
		materiaDao = new MateriaDAOImpl();
	}
	public void menu(){
		System.out.println("Bienvenido. Elija opcion a realizar:");
		System.out.println("0_ VER CURSO");
		System.out.println("1_ Ingresar ALUMNO");
		System.out.println("2_ Borrar ALUMNO");
		System.out.println("3_ Ingresar MATERIA");
		System.out.println("4_ Borrar MATERIA");
		System.out.println("5_ Ingresar CURSO");
		System.out.println("6_ Borrar CURSO");
		System.out.println("7_ Ingresar PROFESOR");
		System.out.println("8_ Borrar PROFESOR");
		System.out.println("9_ SALIR");
	}
	//if 0
	public void verCurso() throws SQLException{
		List<Curso> cursos = cursoDao.search(null);
		for (int i = 0; i < cursos.size(); i++) {
				System.out.println("mira el curso "+ cursos.get(i).getNombre());
		}
	}
	
	private Date safeReadDate() throws IOException{
		boolean success = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		do{
			try{
				System.out.println("ingrese fecha (DD/MM/AAAA)");
				String fechita = br.readLine();
				fecha = sdf.parse(fechita);
				success = true;
			}catch(ParseException e){
				System.out.println("dale papito, media pila...");
				success = false;
			}
		}while(!success);
		return fecha;
	}
	
	
	private Long safeReadLong() throws IOException{
		boolean ok = true;
		Long cursoId = null;
		do{
			try{
				System.out.println("ingrese ID de curso del alumno");
				String input = br.readLine();
				cursoId = Long.parseLong(input);
				ok = true;
			}catch(NumberFormatException e){
				ok = false;
			}
		}while(!ok);
		return cursoId;
	}
	
	//if 1
	public void ingresarAlumno() throws IOException, SQLException{
		System.out.println("ingrese apellido");
		String ape = br.readLine();
		System.out.println("ingrese nombre");
		String nom = br.readLine();
		Date fecha = safeReadDate();
		Long cursoId = safeReadLong();
		Alumno nuevoAlumno = new Alumno();
		nuevoAlumno.setNombre(nom);
		nuevoAlumno.setApellido(ape);
		nuevoAlumno.setFechaNacimiento(fecha);
		Curso grado = new Curso();
		grado.setId(cursoId);
		nuevoAlumno.setGrado(grado);
		alumnoDao.create(nuevoAlumno);
		System.out.println("se ingreso alumno");
	}
	//if 2
	public void borrarAlumno() throws IOException, SQLException{
		System.out.println("ingrese ID de alumno a eliminar.");
		String perselid = br.readLine();
		Long alumnoElim = Long.parseLong(perselid);
		Alumno alumno = new Alumno();
		alumno.setId(alumnoElim);
		alumnoDao.delete(alumno);
		System.out.println("se elimino alumno");
	}
	//if 3
	public void ingresoMateria() throws IOException, SQLException{	
		System.out.println("ingrese nombre de materia");
		String nMat = br.readLine();
		Materia materia = new Materia();
		materia.setNombre(nMat);
		materiaDao.create(materia);
		System.out.println("se ingreso materia");
	}
	//if 4
	public void borrarMateria() throws IOException, SQLException{
		System.out.println("ingrese ID de materia a eliminar");
		String input = br.readLine();
		Materia idMat = new Materia();
		idMat.setId(Long.parseLong(input));
		materiaDao.delete(idMat);
		System.out.println("se elimino Materia");
	}
	//if 5
	public void ingresoCurso() throws IOException, SQLException{
		System.out.println("ingrese nombre de curso");
		String input = br.readLine();
		Curso curso = new Curso();
		curso.setNombre(input);
		cursoDao.create(curso);
		System.out.println("se ingreso curso");
	}
	//if 6
	public void borrarCurso() throws IOException, SQLException{
		System.out.println("ingrese ID del curso a eliminar");
		String idel = br.readLine();
		Long cursoElim = Long.parseLong(idel);
		Curso curso = new Curso();
		curso.setId(cursoElim);
		cursoDao.delete(curso);
		System.out.println("se elimino curso");
	}
	//if 7
	public void ingresarProfesor() throws IOException, SQLException, ParseException{
		System.out.println("ingrese apellido");
		String ape = br.readLine();
		System.out.println("ingrese nombre");
		String nom = br.readLine();
		Date fecha = safeReadDate();
		Profesor nuevoProfesor = new Profesor();
		nuevoProfesor.setNombre(nom);
		nuevoProfesor.setApellido(ape);
		nuevoProfesor.setFechaNacimiento(fecha);
		profesorDao.create(nuevoProfesor);
		System.out.println("se ingreso profesor");
	}
	//if 8
	public void borrarProfesor() throws IOException, SQLException{
		System.out.println("ingrese ID de profesor a eliminar.");
		String perselid = br.readLine();
		Long profesorElim = Long.parseLong(perselid);
		Profesor profesor = new Profesor();
		profesor.setId(profesorElim);
		profesorDao.delete(profesor);
		System.out.println("se elimino profesor");
	}
	public String leerOpcion() throws IOException, SQLException, ParseException {
		String input = br.readLine();
		if(input.equals("0")){
			verCurso();
		}else if(input.equals("1")){
			ingresarAlumno();
		}else if(input.equals("2")){
			borrarAlumno();
		}else if(input.equals("3")){
			ingresoMateria();
		}else if(input.equals("4")){
			borrarMateria();
		}else if(input.equals("5")){
			ingresoCurso();
		}else if(input.equals("6")){
			borrarCurso();
		}else if(input.equals("7")){
			ingresarProfesor();
		}else if(input.equals("8")){
			borrarProfesor();
		}else if(input.equals("9")){
			System.exit(0);
		}
		return input;
	}
}
