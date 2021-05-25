package kahoot_pr3prog;

import java.util.ArrayList;
import java.util.Scanner;

public class Preguntas {

	private int idpreg;
	private String pregunta;
	private int idresp;
		
	public Preguntas(int idpreg, String pregunta, int idresp) {
		super();
		this.idpreg = idpreg;
		this.pregunta = pregunta;
		this.idresp = idresp;
	}

	public Preguntas() {
		super();
	}

	public int getIdpreg() {
		return idpreg;
	}

	public void setIdpreg(int idpreg) {
		this.idpreg = idpreg;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public int getIdresp() {
		return idresp;
	}

	public void setIdresp(int idresp) {
		this.idresp = idresp;
	}

	@Override
	public String toString() {
		return "Preguntas [idpreg=" + idpreg + ", pregunta=" + pregunta + ", idresp=" + idresp + "]";
	}
	
	public Preguntas CrearPregunta(int idpreg, int idresp) {
		
		Preguntas npreg = new Preguntas();
		
		String pregunta;

		Scanner sc = new Scanner (System.in);
		
		boolean compro=true;
		
		npreg.setIdpreg(idpreg);
		npreg.setIdresp(idresp);
		
		do {
		
			System.out.println("Introduzca la pregunta:");
		
			pregunta=sc.nextLine();
			
			if(pregunta.length()>=255) { //Reviso que la cadena no sea mas larga de la longitud del VARCHAR de la base de datos
				
				System.out.println("La pregunta tiene mas caracteres de los permitidos por el sistema, escriba otra");
				
				compro=false;
				
			}
		
		}while(!compro);
		
		npreg.setPregunta(pregunta);
		
		return npreg;
						
	}
	
	
}
