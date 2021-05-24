package kahoot_pr3prog;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub

		
		int opcion=0;
		int opcion1=0;
		String [] menu = {"Jugar","Editar"};
		String [] menu1 = {"Añadir","Modificar","Eliminar"};
		String [] menu2 = {"Preguntas","Respuestas"};
		String [] cont = {"Si","No"};
		BaseDatos bd = new BaseDatos();
		
		//bd.ConectarBaseDatos();
		
		System.out.println("BIENVENIDO A PREGUNTAS SOBRE EL RENACIMIENTO");
		System.out.println("¿Que desea hacer?");
		
		opcion=elegirOpcion(menu);
		
		if(opcion==1) {
			
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
}
