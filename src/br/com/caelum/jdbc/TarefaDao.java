package br.com.caelum.jdbc;

import java.util.List;

import br.com.caelum.jdbc.modelo.Tarefa;

//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;	//cuidado sempre usar a interface do java.sql e nao com.mysql.*
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import br.com.caelum.jdbc.modelo.Tarefa;
//
//@Repository
//public class TarefaDao {
//	private final Connection connection;
//
//	// Usar AUTOWIRED , para injetar a dependencia.
//	@Autowired
//	public TarefaDao(DataSource dataSource) {
//		try {
//			this.connection = dataSource.getConnection();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//			}
//	}
//
//	// inserir um tarefa.
//	public void adiciona(Tarefa tarefa) {
//		String sql = "insert into tarefas " +
//				"(descricao, finalizado)" +
//				" values (?, ?)";
//		try {
//			// prepared statement para inserção
//			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
//
//			// seta os valores
//			stmt.setString(1, tarefa.getDescricao());
//			stmt.setBoolean(2, tarefa.isFinalizado());
//
//			// executa
//			stmt.execute();
//			stmt.close();
//			System.out.println("Tarefa adicionada com Sucesso! (METODO ADICIONA/TarefaDao)");
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//			//throw new erroNoBancoDadosException("Erro no Banco de Dados, tente mais tarde!");
//		}
//	}
//
//	// Montar uma lista das tarefas
//	// (select * from tarefas).
//	public List<Tarefa> lista() {
//		List<Tarefa> tarefas = new ArrayList<Tarefa>();
//
//		try {
//			PreparedStatement stmt =
//					this.connection.prepareStatement("select * from tarefas");
//
//			ResultSet rs = stmt.executeQuery();
//		
//			while (rs.next()) {
//				// criando o objeto Tarefa
//				Tarefa tarefa = new Tarefa();
//
//				tarefa.setId(rs.getLong("id"));
//				tarefa.setDescricao(rs.getString("descricao"));
//				tarefa.setFinalizado(rs.getBoolean("finalizado"));
//
//				if(rs.getDate("dataFinalizacao") != null) {
//					//montando data atraves do calendar
//					Calendar dataFinalizacao = Calendar.getInstance();
//					dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));
//				
//					tarefa.setDataFinalizacao(dataFinalizacao);
//				}
//
//				// adicionando o objeto à lista
//				tarefas.add(tarefa);
//			}
//			rs.close();
//			stmt.close();
//			System.out.println("Lista gerada com Sucesso! (METODO LISTA/TarefaDao)");
//			return tarefas;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	// Remover a tarefa selecionada pelo browser na pagina listaTarefas
//	// (recebe o id da tarefa para remover).
//	public void remove(Tarefa tarefa){
//		try{
//			PreparedStatement stmt = (PreparedStatement) 
//					this.connection.prepareStatement("delete from tarefas where id = ?");
//
//			stmt.setLong(1, tarefa.getId());
//			stmt.execute();
//			stmt.close();
//			
//			System.out.println("Tarefa Excluida com sucesso! (METODO REMOVE/TarefaDao)");
//		}catch(SQLException e){
//			throw new RuntimeException(e);
//		}
//	}
//
//	// Busca por id
//	// (recebe o id da tarefa, localiza a tarefa e devolve o objeto tarefa).
//	public Tarefa buscaPorId(Long id){
//		try{
//			PreparedStatement stmt = (PreparedStatement) 
//					this.connection.prepareStatement("select * from tarefas");
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while(rs.next())
//			{
//				//System.out.println(rs.getLong("id")+ " // " + id);
//				if(id == rs.getLong("id"))
//				{
//					//criando objeto tarefa
//					Tarefa tarefa = new Tarefa();
//					tarefa.setId(rs.getLong("id"));
//					tarefa.setDescricao(rs.getString("descricao"));
//					tarefa.setFinalizado(rs.getBoolean("finalizado"));
//					
//					if(rs.getDate("dataFinalizacao") != null)
//					{
//						//montando data atraves do calendar
//						Calendar dataFinalizacao = Calendar.getInstance();
//						dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));
//					
//						tarefa.setDataFinalizacao(dataFinalizacao);
//					}
//					System.out.println("retornada tarefa:" + tarefa.getId());
//					System.out.println("Tarefa Retornada com sucesso! (METODO BUSCAPORID/TarefaDao)");
//					return tarefa;
//				}
//			}
//				return null;      //nao achou o id recebido.
//			}catch(SQLException e){
//				throw new RuntimeException(e);
//			}	
//	}
//	
//	// Altera a tarefa atraves do id
//	// (recebe o objeto tarefa alterado e pelo id ira atualizar a tarefa no banco de dados).
//	public void altera(Tarefa tarefa){
//		String sql = "update tarefas set descricao=?, finalizado=?, dataFinalizacao=? where id=?";
//		
//		try{
//			PreparedStatement stmt = (PreparedStatement) 
//					this.connection.prepareStatement(sql);
//
//			stmt.setString(1, tarefa.getDescricao());
//			stmt.setBoolean(2, tarefa.isFinalizado());
//
//			if(tarefa.getDataFinalizacao() != null)
//			{
//				stmt.setDate(3, new Date (tarefa.getDataFinalizacao().getTimeInMillis()));
//			}
//			else
//			{
//				stmt.setDate(3, null);
//			}
//			
//			stmt.setLong(4, tarefa.getId());
//				
//			stmt.execute();
//			stmt.close();
//			
//			System.out.println("Dados alterados com sucesso! (METODO ALTERA/TarefaDao)");
//		}catch(SQLException e){
//			throw new RuntimeException(e);
//		}
//	}
//
//	
//	public void finaliza(Long id) throws SQLException{
//		Tarefa tarefa = buscaPorId(id);
//		
//		String sql = "update tarefas set finalizado=?, dataFinalizacao=? where id=?";
//		
//		try{
//			PreparedStatement stmt = (PreparedStatement) 
//					this.connection.prepareStatement(sql);
//
//			stmt.setBoolean(1, true);
//			
//			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
//			
//			stmt.setLong(3, tarefa.getId());
//				
//			stmt.execute();
//			stmt.close();
//			
//			System.out.println("DADOS ALTERADOS COM SUCESSO! (METODO FINALIZA/TarefaDao)");
//		}catch(SQLException e){
//			throw new RuntimeException(e);
//		}
//	}
//	
//}



// NOVA INTERFACE EM 25/05/2017
public interface TarefaDao {
	Tarefa buscaPorId(Long id);

	List<Tarefa> lista();

	void adiciona(Tarefa t);

	void altera(Tarefa t);

	void remove(Tarefa t);

	void finaliza(Long id);
}
// FIM NOVA INTERFACE EM 25/05/2017
