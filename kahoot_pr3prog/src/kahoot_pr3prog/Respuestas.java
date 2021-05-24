package kahoot_pr3prog;

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
	
	
	
}
