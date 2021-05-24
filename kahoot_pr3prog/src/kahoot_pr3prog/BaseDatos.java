package kahoot_pr3prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {

		
	public BaseDatos() {  //Genero el constructor vacio para poder crear desde el programa el objeto bd con el que acceder a todos los metodos
		super();
		
	}

	public void ConectarBaseDatos() throws ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost",
					"root", "DSE260403");

			conexion.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Error");
		}finally {
		
			
			System.out.println("Conectado/desconectado");
		
		}

	}
		
	
	
	
}
