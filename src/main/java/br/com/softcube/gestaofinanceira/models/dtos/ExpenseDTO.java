package br.com.softcube.gestaofinanceira.models.dtos;

import java.time.LocalDate;

import br.com.softcube.gestaofinanceira.models.Lancamento;

public class LancamentoDTO {

	private String nome;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private String tipo;

	public LancamentoDTO(Lancamento lancamento) {
		this.nome = lancamento.getNome();
		this.descricao = lancamento.getDescricao();
		this.valor = lancamento.getValor();
		this.data = lancamento.getData();
		this.tipo = lancamento.getTipo().name();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public String getTipo() {
		return tipo;
	}

}