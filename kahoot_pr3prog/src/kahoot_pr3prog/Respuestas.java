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

	public Respuestas crearRespuesta(int idresp) { // Este metodo crea el objeto respuesta para añadirlo posteriormente
													// a la base de datos

		Respuestas nresp = new Respuestas();

		String respuesta = "";

		nresp.setIdres(idresp);

		respuesta = Programa.escribirVarchar();

		nresp.setRespuesta(respuesta);

		return nresp;

	}

}
