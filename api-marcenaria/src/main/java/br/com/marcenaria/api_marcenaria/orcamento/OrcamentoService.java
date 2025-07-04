package br.com.marcenaria.api_marcenaria.orcamento;

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
}
