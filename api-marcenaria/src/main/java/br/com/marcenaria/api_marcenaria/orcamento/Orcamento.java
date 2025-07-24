package br.com.marcenaria.api_marcenaria.orcamento;

import br.com.marcenaria.api_marcenaria.usuario.Endereco;
import br.com.marcenaria.api_marcenaria.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "orcamento")
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="autor_id")
    private Usuario autor;
    private String endereco;
    private String descricao;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Orcamento(){

    }

    public Orcamento(DadosCadastroOrcamento dados, Usuario autor){
        this.descricao = dados.descricao();
        this.endereco = dados.endereco();
        this.dataCriacao = LocalDateTime.now();
        this.autor = autor;
        this.status = Status.NAO_APROVADO;
    }

    public Long getId() {
        return id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public Orcamento atualizarInformacoes(DadosAtualizacaoOrcamento dados, Orcamento orcamento) {
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.endereco() != null){
            this.endereco = dados.endereco();
        }
        return this;
    }

    public void alterarStatus(Status status) {
        this.status = status;
    }
}
