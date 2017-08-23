package br.com.caelum.jdbc;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.jdbc.modelo.Tarefa;

@Repository
public class JpaTarefaDao implements TarefaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void adiciona(Tarefa tarefa) {
		manager.persist(tarefa);
		System.out.println("Tarefa ADICIONADA com sucesso! [METODO adiciona/JpaTarefaDao]");
	}

	// Montar uma lista das tarefas
	public List<Tarefa> lista() {
		System.out.println("LISTA TAREFA com sucesso! [METODO list/JpaTarefaDao]");
		return manager.createQuery("select t from Tarefa t").getResultList();
	}

	// Remover a tarefa selecionada pelo browser na pagina listaTarefas
	// (recebe o id da tarefa para remover).
	public void remove(Tarefa tarefa){
		Tarefa tarefaARemover = buscaPorId(tarefa.getId());
		manager.remove(tarefaARemover);
		System.out.println("Tarefa REMOVIDA com sucesso! [METODO remove/JpaTarefaDao]");
	}

	// Busca por id
	// (recebe o id da tarefa, localiza a tarefa e devolve o objeto tarefa).
	public Tarefa buscaPorId(Long id){
		System.out.println("BUSCA POR ID com sucesso! [METODO buscaPorId/JpaTarefaDao]");
		return manager.find(Tarefa.class, id);
	}
		
	// Altera a tarefa
	public void altera(Tarefa tarefa){
		manager.merge(tarefa);
		System.out.println("Tarefa ALTERADA com sucesso! [METODO altera/JpaTarefaDao]");
	}

	// Finaliza a tarefa
	public void finaliza(Long id){
		Tarefa tarefa = buscaPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		manager.merge(tarefa);
		System.out.println("Tarefa FINALIZADA com sucesso! [METODO finaliza/JpaTarefaDao]");
	}
		
}
