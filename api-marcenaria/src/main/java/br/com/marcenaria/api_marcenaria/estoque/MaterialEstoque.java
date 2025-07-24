package br.com.marcenaria.api_marcenaria.estoque;

import jakarta.persistence.*;

@Entity
@Table(name = "materiais_estoque")
public class MaterialEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String dimensao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String fornecedor;

    public MaterialEstoque(){

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDimensao() {
        return dimensao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }
}
