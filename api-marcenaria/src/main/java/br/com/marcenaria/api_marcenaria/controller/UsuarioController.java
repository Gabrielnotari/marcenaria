package br.com.marcenaria.api_marcenaria.controller;

import br.com.marcenaria.api_marcenaria.perfil.DadosPerfil;
import br.com.marcenaria.api_marcenaria.usuario.*;
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
    public ResponseEntity<String> verificarEmail(@RequestParam String codigo) {
        usuarioService.verificarEmail(codigo);
        return ResponseEntity.ok("conta verificada com sucesso!");
    }

    @PutMapping("/editar-perfil")
    public ResponseEntity<DadosListagemUsuario> editarPerfil(@RequestBody @Valid DadosEdicaoUsuario dados, @AuthenticationPrincipal Usuario logado) {
        var usuario = usuarioService.editarPerfil(logado, dados);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @PatchMapping("alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid DadosAlteracaoSenha dados, @AuthenticationPrincipal Usuario logado) {
        usuarioService.alterarSenha(dados, logado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/desativar")
    public ResponseEntity<Void> banirUsuario(@AuthenticationPrincipal Usuario logado) {
        usuarioService.desativarUsuario(logado);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("adicionar-perfil/{id}")
    public ResponseEntity<DadosListagemUsuario> adicionarPerfil(@PathVariable Long id, @RequestBody @Valid DadosPerfil dados) {
        var usuario = usuarioService.adicionarPerfil(id, dados);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }
}
