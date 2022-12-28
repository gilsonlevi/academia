package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao  {
	
	public static Connection getConexao(String umBanco) {
		try {
			String url = "jdbc:mysql://localhost";
			String usuario = "root";
			String senha = "@JNL12345silva";
			
			url += "/" + umBanco;
			
			return DriverManager.getConnection(url,usuario, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
