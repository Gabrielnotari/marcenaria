package br.com.marcenaria.api_marcenaria.orcamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoOrcamento(
        @NotNull Long id,
        @NotBlank String descricao,
        @NotBlank String endereco
) {
}
