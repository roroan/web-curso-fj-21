package br.com.caelum.tarefas.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.jdbc.JpaTarefaDao;
import br.com.caelum.jdbc.TarefaDao;
import br.com.caelum.jdbc.modelo.Tarefa;

@Controller
@Transactional
public class TarefasController {
	//construtor dao das tarefas.
	//private final TarefaDao dao;

	//apenas usa a TarefaDao da interface (referente ao Hibernate com jpa)
	TarefaDao dao;
	
	@Autowired
	public TarefasController(TarefaDao dao) {
		this.dao = dao;
	}
	
	//@Autowired
	//private JpaTarefaDao daoTarefa;
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		
		//daoTarefa.adiciona(tarefa);
		
		//TarefaDao dao = new TarefaDao();    JA CRIOU NO CONSTRUTOR ACIMA.
		dao.adiciona(tarefa);
		
		return "tarefa/adicionada";
	}
	
	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", dao.lista());
		return "tarefa/lista";
	}
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}
	
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) throws SQLException {
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/finalizada";
	}
	
	
}
