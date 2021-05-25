package kahoot_pr3prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {

	private Connection conexion = null;
	private Statement sentenciaSQL = null;
	private ResultSet rs;
		
	public BaseDatos() {  //Genero el constructor vacio para poder crear desde el programa el objeto bd con el que acceder a todos los metodos
		super();
		
	}

	public void MostrarTextoBD(int id, boolean EsPregunta) throws ClassNotFoundException, SQLException { //Esta funcion muestra por pantalla el resultado de la consulta
		
		try {
			
			String sql;
			
			if(EsPregunta) {
				
				sql="SELECT * FROM preguntas WHERE id_preg="+id;
				
			}else {	
				
				sql="SELECT * FROM respuestas WHERE id_resp="+id;	
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
					"root", "DSE260403");

			sentenciaSQL = conexion.createStatement();
			
			rs=sentenciaSQL.executeQuery(sql);
			
			while (rs.next()) {
				
				System.out.println(rs.getString(2));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
			conexion.close();
			sentenciaSQL.close();
					
		}

	}
	
	public String DevolverTexto(int id, boolean EsPregunta) throws ClassNotFoundException, SQLException { //Esta funcion devuelve la cadena en lugar de mostrarla
		
		String texto="No hay resultados";
		
		try {
			
			String sql;
			
			
			if(EsPregunta) {
				
				sql="SELECT * FROM preguntas WHERE id_preg="+id;
				
			}else {	
				
				sql="SELECT * FROM respuestas WHERE id_resp="+id;	
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
					"root", "DSE260403");

			sentenciaSQL = conexion.createStatement();
			
			rs=sentenciaSQL.executeQuery(sql);
			
			while (rs.next()) {
				
				texto=rs.getString(2);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
			conexion.close();
			sentenciaSQL.close();
					
		}
		
		return texto;

	}
	
	
	public int idRespuestaCorrecta(int idpreg) throws ClassNotFoundException, SQLException {
		
		int id=0;
		
			try {
				
				String sql;
					
				sql="SELECT cod_resp FROM preguntas WHERE id_preg="+idpreg;
					
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
						"root", "DSE260403");
		
				sentenciaSQL = conexion.createStatement();
					
				rs=sentenciaSQL.executeQuery(sql);
					
				while (rs.next()) {
						
					id=rs.getInt(1);
				}
					
			} catch (SQLException ex) {
				ex.printStackTrace();
				//System.out.println("Error");
			}finally {
				conexion.close();
				sentenciaSQL.close();
				
			
			}
					
		
		
		return id;
	}
	
	public int idUltimoRegistro(boolean EsPregunta) throws ClassNotFoundException, SQLException {
		
		int id=0;
		
			try {
				
				String sql;
				
				if(EsPregunta) {
					
					sql="SELECT * FROM preguntas";
					
				}else {
					
					sql="SELECT * FROM respuestas";
					
				}
					
									
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
						"root", "DSE260403");
		
				sentenciaSQL = conexion.createStatement();
					
				rs=sentenciaSQL.executeQuery(sql);
					
				while (rs.next()) {
						
					id=rs.getInt(1);
				}
					
			} catch (SQLException ex) {
				ex.printStackTrace();
				//System.out.println("Error");
			}finally {
				conexion.close();
				sentenciaSQL.close();
				
			
			}
					
		
		
		return id;
	}
	
	public void insertSQL (Preguntas pregunta) throws ClassNotFoundException, SQLException { //Metodo para insertar preguntas
		
		try {
			
			String sql;
			
			int resultado;
				
			sql="insert into preguntas values ("+pregunta.getIdpreg()+",'"+pregunta.getPregunta()+"',"+pregunta.getIdresp()+");";
														
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
					"root", "DSE260403");
	
			sentenciaSQL = conexion.createStatement();
				
			resultado=sentenciaSQL.executeUpdate(sql);
				
			if(resultado>=1) {
				
				System.out.println("Pregunta insertada correctamente");
				
			}
				
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
			conexion.close();
			sentenciaSQL.close();
			
		
		}
		
		
	}
		
	public void insertSQL (Respuestas respuesta) throws ClassNotFoundException, SQLException { //Metodo para insertar respuestas
		
		try {
			
			String sql;
			int resultado;
			
			sql="insert into respuestas values ("+respuesta.getIdres()+",'"+respuesta.getRespuesta()+"');";
					
											
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
					"root", "DSE260403");
	
			sentenciaSQL = conexion.createStatement();
				
			resultado=sentenciaSQL.executeUpdate(sql);
			
			if(resultado>=1) {
				
				System.out.println("Respuesta insertada correctamente");
				
			}
				
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
			conexion.close();
			sentenciaSQL.close();
			
		
		}
		
		
	}
	
	public int BuscarPreguntaRespuesta(boolean EsPregunta, String busqueda) throws ClassNotFoundException, SQLException {
		
		int id=0;
		
			try {
				
				String sql;
				
				if(EsPregunta) {
					
					sql="SELECT * FROM preguntas WHERE pregunta like '%"+busqueda+"%'";
					
				}else {
					
					sql="SELECT * FROM respuestas WHERE respuesta like '%"+busqueda+"%'";
					
				}
					
									
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
						"root", "DSE260403");
		
				sentenciaSQL = conexion.createStatement();
					
				rs=sentenciaSQL.executeQuery(sql);
					
				while (rs.next()) {
						
					id=rs.getInt(1);
					System.out.println("El resultado de su busqueda es: "+rs.getString(2));
				}
					
			} catch (SQLException ex) {
				ex.printStackTrace();
				//System.out.println("Error");
			}finally {
				conexion.close();
				sentenciaSQL.close();
				
			
			}
					
				
		return id;
	}
	
	public void updateSQLtexto (int idreg, String modificacion, boolean EsPregunta) throws ClassNotFoundException, SQLException { //Metodo para insertar preguntas
		
		try {
			
			String sql;
			
			int resultado;
				
			if(EsPregunta) {
				
				sql="update preguntas set pregunta='"+modificacion+"' where id_preg="+idreg+";";
				
			}else {
				
				sql="update respuestas set respuesta='"+modificacion+"' where id_resp="+idreg+";";
				
			}
														
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
					"root", "DSE260403");
	
			sentenciaSQL = conexion.createStatement();
				
			resultado=sentenciaSQL.executeUpdate(sql);
				
			if(resultado>=1) {
				
				System.out.println("Registro modificado correctamente");
				
			}
				
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
			conexion.close();
			sentenciaSQL.close();
			
		
		}
		
	}
	
	public void updateSQLrespuestas (int idpreg, int idresp) throws ClassNotFoundException, SQLException { //Metodo para insertar preguntas
		
		try {
			
			String sql;
			
			int resultado;
					
			sql="update preguntas set cod_resp='"+idresp+"' where id_preg="+idpreg+";";
				
																	
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/preguntas_respuestas",
					"root", "DSE260403");
	
			sentenciaSQL = conexion.createStatement();
				
			resultado=sentenciaSQL.executeUpdate(sql);
				
			if(resultado>=1) {
				
				System.out.println("Registro modificado correctamente");
				
			}
				
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
			conexion.close();
			sentenciaSQL.close();
			
		
		}
		
	}
	
	
}
