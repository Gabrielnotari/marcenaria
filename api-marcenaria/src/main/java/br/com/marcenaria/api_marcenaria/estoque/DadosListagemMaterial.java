package br.com.marcenaria.api_marcenaria.estoque;

public record DadosListagemMaterial(
        Long id,
        String nome,
        String dimensao,
        Integer quantidade,
        String fornecedor
) {
    public DadosListagemMaterial(MaterialEstoque materialEstoque){
        this(materialEstoque.getId(), materialEstoque.getNome(),
                materialEstoque.getDimensao(), materialEstoque.getQuantidade(),
                materialEstoque.getFornecedor());
    }
}
