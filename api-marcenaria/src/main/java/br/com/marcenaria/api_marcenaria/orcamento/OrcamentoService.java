package br.com.marcenaria.api_marcenaria.orcamento;

import br.com.marcenaria.api_marcenaria.infra.exception.RegraDeNegocioException;
import br.com.marcenaria.api_marcenaria.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    private final OrcamentoRepository repository;


    public OrcamentoService(OrcamentoRepository repository) {
        this.repository = repository;
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
