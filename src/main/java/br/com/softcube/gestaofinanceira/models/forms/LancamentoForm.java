package br.com.softcube.gestaofinanceira.models.forms;

import java.time.LocalDate;

import br.com.softcube.gestaofinanceira.models.Lancamento;
import br.com.softcube.gestaofinanceira.models.enums.LancamentoTipo;

public class LancamentoForm {

	private String nome;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private String tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Lancamento converte() {
		Lancamento lancamento = new Lancamento();
		lancamento.setNome(this.getNome());
		lancamento.setDescricao(this.getDescricao());
		lancamento.setValor(this.getValor());
		lancamento.setData(this.getData());
		lancamento.setTipo(LancamentoTipo.valueOf(this.getTipo()));
		
		return lancamento;
	}

}