package br.com.marcenaria.api_marcenaria.controller;

import br.com.marcenaria.api_marcenaria.orcamento.DadosCadastroOrcamento;
import br.com.marcenaria.api_marcenaria.orcamento.DadosListagemOrcamento;
import br.com.marcenaria.api_marcenaria.usuario.DadosCadastroUsuario;
import br.com.marcenaria.api_marcenaria.usuario.DadosListagemUsuario;
import br.com.marcenaria.api_marcenaria.usuario.Usuario;
import br.com.marcenaria.api_marcenaria.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/registrar")
    public ResponseEntity<DadosListagemUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrar(dados);
        var uri = uriBuilder.path("/{nomeUsuario}").buildAndExpand(usuario.getNomeUsuario()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemUsuario(usuario));
    }

    @GetMapping("/verificar-conta")
    public ResponseEntity<String> verificarEmail(@RequestParam String codigo){
        usuarioService.verificarEmail(codigo);
        return ResponseEntity.ok("conta verificada com sucesso!");
    }
}
