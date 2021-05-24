package kahoot_pr3prog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

public class Programa {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		
		int opcion=0;
		int opcion1=0;
		int idpreg;
		int idcorrecto;
		int puntos=0;
		Double aleatorio;
		String [] menu = {"Jugar","Editar"};
		String [] menu1 = {"Añadir","Modificar","Eliminar"};
		String [] menu2 = {"Preguntas","Respuestas"};
		String [] cont = {"Si","No"};
		ArrayList <Integer> idpreguntas;
		ArrayList <Integer> idrespuestas;
		BaseDatos bd = new BaseDatos();
		
		//bd.ConectarBaseDatos();
		
		System.out.println("BIENVENIDO A PREGUNTAS SOBRE EL RENACIMIENTO");
		System.out.println("¿Que desea hacer?");
		
		opcion=elegirOpcion(menu);
		
		if(opcion==1) {
			
			do {
				
				idpreguntas = new ArrayList <Integer> (generarIDsAleatorio(10,5)); //Convierto el Hash en un ArrayList para poder acceder a las posiciones
				
				for(int i=0; i<idpreguntas.size();i++) {
					
					idpreg=idpreguntas.get(i);
					
					bd.MostrarTextoBD(idpreg, true);
					
					idcorrecto=bd.idRespuestaCorrecta(idpreg);					
					
					idrespuestas = new ArrayList <Integer> (generarIDsAleatorio(15,3)); //Genero una lista aleatoria de 3 respuestas
					
					if(idrespuestas.contains(idcorrecto)) { //Reviso si en las 3 respuestas generadas aleatoriamente está la correcta
						
						idrespuestas.add(11); //Es una respuesta que para todas las preguntas es incorrecta,la añado si aleatoriamente se habia incluido la correcta en la lista.
						
					}else {
						
						idrespuestas.add(idcorrecto);
											
					}
					
					Collections.shuffle(idrespuestas); //Mezclo las respuestas, por si he añadido la correcta que no esté siempre al final
					
					puntos=puntos+responderJuego(idrespuestas,idcorrecto);
					
					
				}
				
				System.out.println("¿Quiere volver a jugar?");
				
			}while(elegirOpcion(cont)==1);
			
		}else {
			
			System.out.println("¿Que desea editar?"); //Aqui pretendo saber si se quieren editar preguntas o respuestas para mostrar la lista de todas
			
			opcion=elegirOpcion(menu2);
			
			if(opcion==1) {
				
				opcion=10;
				System.out.println("Estas son las preguntas:");
				
				
			}else {
				
				opcion=20;
				System.out.println("Estas son las respuestas:");
				
			}
			
			System.out.println("¿Que quieres hacer?");
			opcion1=elegirOpcion(menu1);
			opcion=opcion+opcion1;  //Con esto mantengo la decision inicial de que se quiere editar y cual de las opciones de ediccion se ha seleccionado
			
			switch(opcion) {
			
				case 11:
					break;
				case 12:
					break;
				case 13:
					break;
				case 21:
					break;
				case 22:
					System.out.println("hola");
					break;
				case 23:
					break;
			}
			
		}
		
		
	}
	
	public static int menu(String [] menu) throws InputMismatchException{ //Esta función me muestra el menú y el usuario elige la opción
		
		Scanner sc = new Scanner (System.in);
		int numero = 0;
		
		for (int i=0; i<menu.length;i++) {
			
			System.out.println(i+1 + ". "+menu[i]);
				
		}
				
		System.out.println("Seleccione la opción deseada");
		
		numero=sc.nextInt();
		
		return numero;

	}
	
	
	public static int elegirOpcion(String [] menu) {  //Esta función revisa que la opción elegida está contemplada
		
		int numero=0;
		boolean check=true;
		while(check) {
			
			try {
				
				numero=menu(menu);
				
				check=false;
				
			}catch (InputMismatchException e) {
				
				System.out.println("Por favor, introduce un numero.");
				
				
			}catch(Exception e) {
				
				System.out.println("Ha ocurrido un error vuelva a intentarlo.");
				
			}finally {
				
				if(numero<1 ||numero>menu.length) {
					
					System.out.println("Valor fuera de los parámetros");
					check=true;
				}
				
			} 
				
		}
				
		return numero;
	}
	
	public static HashSet<Integer> generarIDsAleatorio (int total, int cantidad) {//Genero aleatoriamente un HashSet de X numeros, al no repetirse los valores en esta estructura de almacenamiento, se que no se me van a repetir las preguntas
		
		Double aleatorio;
		
		HashSet<Integer> preg = new HashSet<Integer>();
		
		while(preg.size()<cantidad) {    
			
			aleatorio= Math.random()*total + 1;
			
			preg.add(aleatorio.intValue());
			
		}
		
		return preg;
	}
	
	
	public static int responderJuego(ArrayList<Integer> IDsRespuestas, int idcorrecto) throws ClassNotFoundException, SQLException, InputMismatchException {
		
		int idresp;
		int respuestaCorrecta=0;
		int respuesta=0;
		int contador=0;
		Scanner sc= new Scanner (System.in);
		BaseDatos bd = new BaseDatos();
		
		System.out.println("Opciones:");
		
		for(int i=0; i<IDsRespuestas.size();i++) {
			
			idresp=IDsRespuestas.get(i);
			
			System.out.println(i+1 +":");
			
			bd.MostrarTextoBD(idresp, false);
			
			if(idresp==idcorrecto) {
				
				respuestaCorrecta=i+1;
			}
			
		}
		
		try {
		
			System.out.println("Responda:");
			
			respuesta=sc.nextInt();
			
		}catch(Exception e) {
			
			System.out.println("Su respuesta no se ajusta a los parametros, se considera un error");		
			
			
		}finally {
			
			if(respuesta==respuestaCorrecta) {
				
				System.out.println("¡Correcto! Bien hecho");
				
				contador=1;
			}else {
				
				System.out.println("Vaya, has fallado");
			}
			
		}
		
		return contador;
		
	}
	
}
