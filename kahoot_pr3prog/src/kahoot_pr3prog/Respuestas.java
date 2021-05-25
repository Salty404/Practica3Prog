package kahoot_pr3prog;

import java.util.Scanner;

public class Respuestas {

	private int idres;
	private String respuesta;
	
	public Respuestas(int idres, String respuesta) {
		super();
		this.idres = idres;
		this.respuesta = respuesta;
	}

	public Respuestas() {
		super();
	}

	public int getIdres() {
		return idres;
	}

	public void setIdres(int idres) {
		this.idres = idres;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "Respuestas [idres=" + idres + ", respuesta=" + respuesta + "]";
	}
	
	public Respuestas crearRespuesta (int idresp) {
		
		Respuestas nresp = new Respuestas ();
		
		String respuesta="";

		Scanner sc = new Scanner (System.in);
		
		boolean compro = true;
		
		nresp.setIdres(idresp);
		
		try {
			
			do {
			
				System.out.println("Introduzca la respuesta:");
			
				respuesta=sc.nextLine();
				
				if(respuesta.length()>=255) { //Reviso que la cadena no sea mas larga de la longitud del VARCHAR de la base de datos
					
					System.out.println("La respuesta tiene mas caracteres de los permitidos por el sistema, escriba otra");
					
					compro=false;
					
				}
			
			}while(!compro);
			
		}catch(Exception e) {
			
			respuesta="Error en el proceso";
			
			System.out.println("Ha ocurrido un error almacenando su respuesta, tendra que modificarla mas adelante");
			
		}finally {
			
			nresp.setRespuesta(respuesta);
			
		}
				
		
		return nresp;
		
	} 
	
}
