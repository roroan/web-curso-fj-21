package br.com.caelum.jdbc.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity			//para o hibernate
@Table(name="tarefas")			//se quiser ter nome de tabela diferente do nome definido na classe.
								// no meu caso, a tabela no banco sera tarefas, apesar da classe ser Tarefa.
								// o padrao do hibernate é o nome da classe Tarefa ter a tabela Tarefa também.
public class Tarefa {

		@Id					//para o hibernate
		@GeneratedValue		//para o hibernate
		private Long id;
		
		@NotNull @Size(min=5)
		
		private String descricao;

		private boolean finalizado;
		
		@Temporal(TemporalType.DATE)	//para o hibernate
		@DateTimeFormat(pattern="dd/MM/yyyy")  //para que o spring entenda datas no formato brasileiro.
		private Calendar dataFinalizacao;

		//getters e setters
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		public boolean isFinalizado() {
			return finalizado;
		}
		public void setFinalizado(boolean finalizado) {
			this.finalizado = finalizado;
		}
		
		public Calendar getDataFinalizacao() {
			return dataFinalizacao;
		}
		public void setDataFinalizacao(Calendar dataFinalizacao) {
			this.dataFinalizacao = dataFinalizacao;
		}

}
