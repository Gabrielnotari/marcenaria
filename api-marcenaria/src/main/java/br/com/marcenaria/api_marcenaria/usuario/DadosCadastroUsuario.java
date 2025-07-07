package br.com.marcenaria.api_marcenaria.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank String nomeCompleto,
        @NotBlank String nomeUsuario
) {
}
