package br.com.marcenaria.api_marcenaria.usuario;

import br.com.marcenaria.api_marcenaria.infra.exception.RegraDeNegocioException;
import br.com.marcenaria.api_marcenaria.perfil.Perfil;
import br.com.marcenaria.api_marcenaria.perfil.PerfilNome;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String nomeUsuario;

    @Embedded
    private Endereco endereco;

    private Boolean verificado;
    private String token;
    private LocalDateTime expiracaoToken;
    private Boolean ativo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario(){}

    public Usuario(DadosCadastroUsuario dados, String senhaCriptografada, Perfil perfil) {
        this.nomeCompleto = dados.nomeCompleto();
        this.email = dados.email();
        this.senha = senhaCriptografada;
        this.nomeUsuario = dados.nomeUsuario();
        this.verificado = false;
        this.token = UUID.randomUUID().toString();
        this.expiracaoToken = LocalDateTime.now().plusMinutes(30);
        this.ativo = false;
        this.perfis.add(perfil);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Long getId() {
        return id;
    }


    public String getToken() {
        return token;
    }

    public void verificar() {
        if (expiracaoToken.isBefore(LocalDateTime.now())){
            throw new RegraDeNegocioException("Link de verificação expirou!");
        }
        this.verificado = true;
        this.token = null;
        this.expiracaoToken = null;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }

    public void desativar() {
        this.ativo = false;
    }

    public Usuario alterarDados(DadosEdicaoUsuario dados) {
        if(dados.nomeUsuario() != null){
            this.nomeUsuario = dados.nomeUsuario();
        }
        return this;
    }

    public void alterarSenha(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }

    public void adicionarPerfil(Perfil perfil) {
        this.perfis.add(perfil);
    }
}