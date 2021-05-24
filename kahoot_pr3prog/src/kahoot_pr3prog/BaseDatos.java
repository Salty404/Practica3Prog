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

	public void MostrarTextoBD(int id, boolean EsPregunta) throws ClassNotFoundException, SQLException {
		
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
		
	
	
	
}
