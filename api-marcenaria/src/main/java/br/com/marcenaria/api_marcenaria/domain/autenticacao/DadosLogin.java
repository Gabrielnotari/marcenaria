package br.com.marcenaria.api_marcenaria.domain.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank String email,
                         @NotBlank String senha) {
}
