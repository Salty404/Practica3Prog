package kahoot_pr3prog;

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
	
	
	
	
}
