package br.com.marcenaria.api_marcenaria.orcamento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroOrcamento(
        @NotBlank String descricao,
        @NotBlank String endereco
) {
}
