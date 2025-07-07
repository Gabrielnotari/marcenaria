package br.com.marcenaria.api_marcenaria.usuario;

public record DadosListagemUsuario(
        Long id,
        String email,
        String nomeCompleto,
        String nomeUsuario
) {
    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(),
                usuario.getNomeCompleto(), usuario.getNomeUsuario());
    }
}
