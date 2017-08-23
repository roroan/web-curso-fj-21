package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados...");

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");   //IMPORTANTE
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "root", "123456");
		} catch (SQLException e) {
			throw new RuntimeException(e);
			}
		}
	
}
