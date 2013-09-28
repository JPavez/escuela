package org.escuela.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.escuela.beans.Materia;

public class Main {

	public static void main(String[] args) {
		// System.out.println(args.length);
		// System.out.println(args[0]);
		// for(int contador=0; contador<args.length; contador++){
		// System.out.println(args[contador]);
		// }
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
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String input;

			while ((input = br.readLine()) != null) {
				System.out.println(input);

				if (input.equals("0")) {
					System.out.println("mira el curso...");
				} else if (input.equals("1")) {
					System.out.println("se ingreso persona");
				} else if (input.equals("2")) {
					System.out.println("se borro persona");
				} else if (input.equals("3")) {
					System.out.println("se ingreso curso");
				} else if (input.equals("4")) {
					System.out.println("se borro curso");
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
