package br.com.softcube.gestaofinanceira.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softcube.gestaofinanceira.daos.LancamentoDao;
import br.com.softcube.gestaofinanceira.models.Lancamento;
import br.com.softcube.gestaofinanceira.models.dtos.LancamentoDTO;
import br.com.softcube.gestaofinanceira.models.forms.LancamentoForm;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoDao daoLancamento;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listaLancamentos() {
		return ResponseEntity.ok(daoLancamento.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> detalharLancamento(@PathVariable Long id) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO(daoLancamento.getOne(id));
		
		return ResponseEntity.ok(lancamentoDTO);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salva(@RequestBody LancamentoForm form, UriComponentsBuilder builder) {
		Lancamento lancamento = form.converte();
		daoLancamento.save(lancamento);
		
		URI uri = builder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri();
		
		return ResponseEntity.created(uri).body(lancamento);
	}
	
}
