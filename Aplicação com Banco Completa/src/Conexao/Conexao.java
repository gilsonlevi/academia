package Conexao;

import java.sql.Connection;

public interface Conexao {
	public Connection getConnection(String umBanco);
}
