package org.escuela.main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.escuela.utils.EscuelaReader;

public class Main {
	
	public static void main(String[] args) throws IOException, SQLException, ParseException{
		
		EscuelaReader escRead = new EscuelaReader();
		escRead.menu();
		String input = escRead.leerOpcion();
		while (!input.equals("7")) {
			escRead.menu();
		   	escRead.leerOpcion();
		}
	}
}
