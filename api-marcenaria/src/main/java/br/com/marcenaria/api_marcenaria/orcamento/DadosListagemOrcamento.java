package br.com.marcenaria.api_marcenaria.orcamento;

import java.time.LocalDateTime;

public record DadosListagemOrcamento(
        Long id,
        String descricao,
        String autor,
        String endereco,
        LocalDateTime dataCriacao
) {
    public DadosListagemOrcamento(Orcamento orcamento){
        this(orcamento.getId(), orcamento.getDescricao(), orcamento.getAutor().getNomeCompleto(), orcamento.getEndereco(), orcamento.getDataCriacao());
    }
}
