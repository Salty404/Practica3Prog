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
		int idresp;
		int idcorrecto;
		int puntos=0;
		int contador;
		Double aleatorio;
		String [] menu = {"Jugar","Editar"};
		String [] menu1 = {"Añadir","Modificar","Eliminar"};
		String [] menu2 = {"Preguntas","Respuestas"};
		String [] cont = {"Si","No"};
		ArrayList <Integer> idpreguntas;
		ArrayList <Integer> idrespuestas;
		BaseDatos bd = new BaseDatos();
		Preguntas p = new Preguntas ();
		Respuestas r = new Respuestas ();
		ArrayList <Preguntas> ListaPreguntas = new ArrayList <Preguntas>();
		
		//bd.ConectarBaseDatos();
		
		System.out.println("BIENVENIDO A PREGUNTAS SOBRE EL RENACIMIENTO");
		
		do {
		
			System.out.println("¿Que desea hacer?");
			
			opcion=elegirOpcion(menu);
			
			if(opcion==1) {
				
				do {
					
					idpreguntas = new ArrayList <Integer> (generarIDsAleatorio(bd.idUltimoRegistro(true),5)); //Convierto el Hash en un ArrayList para poder acceder a las posiciones, ademas de generar 5 preguntas aleatorias entre todas las que tengo en la base de datos
					
					for(int i=0; i<idpreguntas.size();i++) {
						
						idpreg=idpreguntas.get(i);
						
						bd.MostrarTextoBD(idpreg, true);
						
						idcorrecto=bd.idRespuestaCorrecta(idpreg);					
						
						idrespuestas = new ArrayList <Integer> (generarIDsAleatorio(bd.idUltimoRegistro(false)-1,3)); //Genero una lista aleatoria de 3 respuestas entre todas las respuestas menos la ultima.
						
						if(idrespuestas.contains(idcorrecto)) { //Reviso si en las 3 respuestas generadas aleatoriamente está la correcta
							
							idrespuestas.add(bd.idUltimoRegistro(false)); //Esta respuesta no se puede insertar aleatoriamente, si ya está la correcta, siempre va a ser errónea y si es la correcta, se añadirá en el else
							
						}else {
							
							idrespuestas.add(idcorrecto);
												
						}
						
						Collections.shuffle(idrespuestas); //Mezclo las respuestas, por si he añadido la correcta que no esté siempre al final
						
						puntos=puntos+responderJuego(idrespuestas,idcorrecto);
						
						
					}
					
					if(puntos<1) {
						
						System.out.println("Vaya, no has acertado ninguna pregunta... ¡Mas suerte la proxima vez!");
						
					}else if(puntos<3) {
						
						System.out.println("Bueno, has acertado menos de la mitad, no esta mal pero se puede mejorar. ¡Animo para la proxima!");
						
					}else if(puntos<=4) {
						
						System.out.println("¡Muy bien! Estas por encima de la media. ¡Bien hecho!");
						
					}else {
						
						System.out.println("Enseñanos donde tienes la maquina del tiempo, porque lo has hecho PER-FEC-TO. ¡Enhorabuena!");
					}
					
					System.out.println("¿Quiere volver a jugar?");
					
				}while(elegirOpcion(cont)==1);
				
			}else {
				
				System.out.println("¿Que desea editar?"); //Aqui pretendo saber si se quieren editar preguntas o respuestas para mostrar la lista de todas
				
				opcion=elegirOpcion(menu2);
				
				if(opcion==1) {
					
					opcion=10;
					System.out.println("Estas son las preguntas:");
					contador=bd.idUltimoRegistro(true);  //Almaceno en contador el ultimo id de la base de datos
					listaPreguntasRespuestas(contador,true);
					
				}else {
					
					opcion=20;
					System.out.println("Estas son las respuestas:");
					contador=bd.idUltimoRegistro(false);
					listaPreguntasRespuestas(contador,false);	
				}
				
											
				System.out.println("¿Que quieres hacer?");
				opcion1=elegirOpcion(menu1);
				opcion=opcion+opcion1;  //Con esto mantengo la decision inicial de que se quiere editar y cual de las opciones de edicion se ha seleccionado
				
				switch(opcion) {
				
					case 11:
						
						System.out.println("Vas a añadir una pregunta a la base de datos. ¿Esta la respuesta correcta ya en la base de datos?");
						
						opcion=elegirOpcion(cont);
						
						if(opcion==1) {
							
							System.out.println("Estas son las respuestas:");
							
							idresp=elegirOpcion(llenarArrayBD(bd.idUltimoRegistro(false),false));
							
							if (idresp<1 || idresp>bd.idUltimoRegistro(false)) { //Compruebo que el id de la respuesta existe, si no es así añado una por defecto y se tendra que modificar en el apartado correspondiente
								
								System.out.println("No existe esa respuesta, tendra que crearla o elegir una correcta en modificar, mientras se asignara una por defecto");
								
								idresp=11;
								
							}
							
						}else {
							
							System.out.println("Se va a asignar una respuesta por defecto, tendra que crear la suya y cambiarla en el apartado: Modificar.");
														
							idresp=11;
						}
						
						bd.insertSQL(p.CrearPregunta(contador+1, idresp));  //Con contador +1 consigo que siempre sea la siguiente id a la ultima de la base de datos
																		
						break;
					case 12:
														
						modificarPreguntaRespuesta(true,cont);
						
						break;
					case 13:
						break;
					case 21:
						
						System.out.println("Vas a añadir una respuesta a la base de datos:");
						
						bd.insertSQL(r.crearRespuesta(contador+1));						
						
						break;
					case 22:	
						
						modificarPreguntaRespuesta(false,cont);
						
						break;
					case 23:
						break;
				}
				
			}
			
			System.out.println("¿Quieres salir del programa?");
	
		}while(elegirOpcion(cont)==2);
		
		System.out.println("FIN DEL PROGRAMA");
		
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
	
	public static void listaPreguntasRespuestas (int registros, boolean EsPregunta) throws ClassNotFoundException, SQLException {
		
		BaseDatos bd = new BaseDatos ();
		
		for(int i=1;i<=registros;i++) {
			
			System.out.println("Id. "+i+":");
			
			bd.MostrarTextoBD(i, EsPregunta);
			
		}
			
	}
	
	public static String [] llenarArrayBD(int longitud, boolean EsPregunta) throws ClassNotFoundException, SQLException { //Este metodo me permite almacenar en un array los resultados de una consulta a la SQL
		
		String [] lista = new String [longitud];
		
		String llenado="";
		
		BaseDatos bd = new BaseDatos ();
		
		for (int i=0; i<longitud; i++) {
			
			llenado=bd.DevolverTexto(i+1, EsPregunta);
			
			lista [i] = llenado;
			
		}
		
		return lista;
		
	}
	
	public static void modificarPreguntaRespuesta(boolean EsPregunta, String [] cont) throws ClassNotFoundException, SQLException {
		
		BaseDatos bd = new BaseDatos();
		String [] opciones = new String [] {"La pregunta","Su respuesta"};
		String busqueda = "";
		Scanner sc = new Scanner (System.in);
		
		int respuesta=0;
		int opcion=0;
		
		System.out.println("¿Conoces el id de tu registro a modificar?");
		
				
		if(elegirOpcion(cont)==1) {
						
			opcion=elegirOpcion(llenarArrayBD(bd.idUltimoRegistro(EsPregunta),EsPregunta));  //Guardo el id elegido por el usuario para hacer la modificacion
			
						
		}else {
			
			try {
				
				System.out.println("Introduzca un texto para localizar su registro:");
				
				busqueda = sc.nextLine();
				
				opcion = bd.BuscarPreguntaRespuesta(EsPregunta, busqueda);
				
				
			}catch(Exception e) {
				
				System.out.println("Ha habido un error en su búsqueda");
				
			}finally {
				
				System.out.println("Busqueda finalizada");
				
			}
			
		}
		
		if (opcion<=0 || opcion>bd.idUltimoRegistro(EsPregunta)) {
			
			System.out.println("No existe ese registro a modificar");
						
		}else {
			
			if(EsPregunta) {
				
				System.out.println("¿Que quieres modificar?");
				
				if(elegirOpcion(opciones)==1) {
					
					System.out.println("Aquí puedes escribir tu pregunta:");
					
					bd.updateSQLtexto(opcion, escribirVarchar(), EsPregunta);
					
					
				}else {
					
					System.out.println("Aquí tienes todas las respuestas");
	
					respuesta=elegirOpcion(llenarArrayBD(bd.idUltimoRegistro(false),false));  //Guardo el id elegido por el usuario para hacer la modificacion
					
					if(respuesta<1 || respuesta>bd.idUltimoRegistro(false)) {
						
						System.out.println("No existe esa respuesta");
						
					}else {
						
						bd.updateSQLrespuestas(opcion, respuesta);
					}
						
				}
				
			}else {
				
				System.out.println("Aquí puedes escribir tu respuesta:");
			
				bd.updateSQLtexto(opcion, escribirVarchar(), EsPregunta);
				
			}
		}
		
		
	}
	
	public static String escribirVarchar () {
		
		String varchar = "";
		
		boolean compro = true;
		
		Scanner sc = new Scanner (System.in);
		
		try {
			
			do {
			
				System.out.println("Introduzca su registro:");
			
				varchar=sc.nextLine();
				
				if(varchar.length()>=255) { //Reviso que la cadena no sea mas larga de la longitud del VARCHAR de la base de datos
					
					System.out.println("Su registro tiene mas caracteres de los permitidos por el sistema, escriba otra");
					
					compro=false;
					
				}
			
			}while(!compro);
			
		}catch(Exception e) {
			
			varchar="Error en el proceso";
			
			System.out.println("Ha ocurrido un error almacenando su registro, tendra que modificarlo mas adelante");
			
		}finally {
			
			System.out.println("Proceso finalizado");
			
		}
				
		return varchar;
	}
		
}
