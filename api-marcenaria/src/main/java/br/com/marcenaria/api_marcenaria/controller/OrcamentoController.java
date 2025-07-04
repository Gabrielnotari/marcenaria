package br.com.marcenaria.api_marcenaria.controller;

import br.com.marcenaria.api_marcenaria.orcamento.DadosCadastroOrcamento;
import br.com.marcenaria.api_marcenaria.orcamento.DadosListagemOrcamento;
import br.com.marcenaria.api_marcenaria.orcamento.OrcamentoService;
import br.com.marcenaria.api_marcenaria.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("orcamentos")
public class OrcamentoController {

    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosListagemOrcamento> cadastrar(@RequestBody @Valid DadosCadastroOrcamento dados, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Usuario autor) {
        var orcamento = service.cadastrar(dados, autor);
        var uri = uriBuilder.path("/orcamentos/{id}").buildAndExpand(orcamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemOrcamento(orcamento));
    }

}
