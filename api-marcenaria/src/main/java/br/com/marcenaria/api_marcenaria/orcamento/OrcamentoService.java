package br.com.marcenaria.api_marcenaria.orcamento;

import br.com.marcenaria.api_marcenaria.infra.exception.RegraDeNegocioException;
import br.com.marcenaria.api_marcenaria.infra.seguranca.HierarquiaService;
import br.com.marcenaria.api_marcenaria.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    private final OrcamentoRepository repository;
    private final HierarquiaService hierarquiaService;


    public OrcamentoService(OrcamentoRepository repository, HierarquiaService hierarquiaService) {
        this.repository = repository;
        this.hierarquiaService = hierarquiaService;
    }

    @Transactional
    public Orcamento cadastrar(DadosCadastroOrcamento dados, Usuario autor) {
        var orcamento = new Orcamento(dados, autor);
        return repository.save(orcamento);
    }

    @Transactional
    public Orcamento atualizar(DadosAtualizacaoOrcamento dados) {
        var orcamento = buscarPeloId(dados.id());
        return orcamento.atualizarInformacoes(dados, orcamento);
    }

    @Transactional
    public Orcamento marcarComoAprovado(Long id, Usuario logado){
        var orcamento = buscarPeloId(id);

        if(hierarquiaService.usuarioNaoTemPermissoes(logado, orcamento.getAutor(), "ROLE_CONSULTOR"))
            throw new AccessDeniedException("Você não pode marcar esse orçamento como aprovado!");

        if(orcamento.getStatus() == Status.APROVADO)
            throw new RegraDeNegocioException("O orçamento já foi aprovado!");

        orcamento.alterarStatus(Status.APROVADO);
        return orcamento.marcarComoAprovado();

    }

    @Transactional
    public void excluir(Long id) {
        var orcamento = buscarPeloId(id);
        if (orcamento.getStatus() == Status.NAO_APROVADO)
            repository.deleteById(id);
        else
            throw new RegraDeNegocioException("Você não pode apagar o orçamento que já foi aprovado.");
    }

    public Orcamento buscarPeloId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Orçamento não encontrado!"));

    }
}
