package org.escuela.main;

public class Main {

	public static void main(String[] args){
		System.out.println(args.length);
		System.out.println(args[0]);
	 	for(int contador=0; contador<args.length; contador++){
			System.out.println(args[contador]);
		}
	}
}
