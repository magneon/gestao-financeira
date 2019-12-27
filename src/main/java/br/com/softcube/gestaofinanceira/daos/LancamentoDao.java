package br.com.softcube.gestaofinanceira.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softcube.gestaofinanceira.models.Lancamento;

public interface LancamentoDao extends JpaRepository<Lancamento, Long> {

}
