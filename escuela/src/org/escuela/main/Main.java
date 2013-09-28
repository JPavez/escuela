package org.escuela.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.escuela.beans.Curso;
import org.escuela.beans.Materia;
import org.escuela.daos.CursoDAOImpl;
import org.escuela.daos.GenericDAO;

public class Main {
	private static GenericDAO<Curso> cursoDao;
	
	
	static{
		cursoDao = new CursoDAOImpl();
	}

	public static void main(String[] args) throws SQLException {
		// System.out.println(args.length);
		// System.out.println(args[0]);
		// for(int contador=0; contador<args.length; contador++){
		// System.out.println(args[contador]);
		// }
		
		
		//Curso curso = new Curso();
		//curso.setNombre("bla");
		
		//System.out.println(curso.getNombre());
		
		//Profesor profesor = new Profesor();
		//Alumno alumno = new Alumno();
		//Materia materia = new Materia();
		
		
		
		
		
		
		System.out.println("Bienvenido. Elija opcion a realizar:");
		System.out.println("0_ VER CURSO");
		System.out.println("1_ Ingresar PERSONA");
		System.out.println("2_ Borrar PERSONA");
		System.out.println("3_ Ingresar MATERIA");
		System.out.println("4_ Borrar MATERIA");
		System.out.println("5_ Ingresar CURSO");
		System.out.println("6_ Borrar CURSO");
		System.out.println("7_ SALIR");

		try {

			InputStreamReader isr = new InputStreamReader(System.in);
			
			BufferedReader br = new BufferedReader(isr);

			String input;

			while ((input = br.readLine()) != null) {

				if (input.equals("0")) {
					List<Curso> cursos = cursoDao.search(null);
					for (int i = 0; i < cursos.size(); i++) {
						System.out.println("mira el curso " + cursos.get(i).getNombre());
					}

					
				} else if (input.equals("1")) {
					System.out.println("se ingreso persona");
				} else if (input.equals("2")) {
					System.out.println("se borro persona");
				} else if (input.equals("3")) {
					System.out.println("se ingreso curso");
				} else if (input.equals("4")) {
					System.out.println("se borro curso");
				}else if (input.equals("5")) {
					System.out.println("ingrese nombre del curso.");
					if ((input = br.readLine()) != null) {
						Curso curso = new Curso();
						curso.setNombre(input);
						cursoDao.create(curso);
						System.out.println("Se ingreso curso " + curso.getId());
					}
				} else if (input.equals("6")) {
						System.out.println("Se borro curso");
				} else if (input.equals("7")) {
					System.out.println("chau");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
		List<Materia> materias = new LinkedList<Materia>();
		materias.add(new Materia());
		for (Materia m : materias) {
			m.getNombre();
		}
		for (int i = 0; i < materias.size(); i++) {
			Materia m = materias.get(i);
			m.getNombre();
		}

	}
}
