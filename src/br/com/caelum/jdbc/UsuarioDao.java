package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.caelum.jdbc.modelo.Usuario;

public class UsuarioDao {
	private Connection connection;

	public UsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public boolean existeUsuario(Usuario usuario){
		System.out.println("Buscando o usuario no Banco de Dados...");

		String sql = "select * from usuarios where login=? and senha=?";
		
		try{
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement(sql);
		
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
		
			ResultSet rs = stmt.executeQuery();
		
			stmt.execute();

			//verifica se existe retorno na consulta
			if(rs.next())
				{
				stmt.close();
				return true;
				}
			else
				{
				stmt.close();
				return false;
				}
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	
}
