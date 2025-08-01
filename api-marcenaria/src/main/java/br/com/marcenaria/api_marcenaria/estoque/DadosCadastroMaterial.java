package br.com.marcenaria.api_marcenaria.estoque;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMaterial(
        @NotBlank String nome,
        @NotBlank String dimensao,
        @NotBlank Integer quantidade,
        @NotBlank String fornecedor
) {
}
