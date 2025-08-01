package br.com.marcenaria.api_marcenaria.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialEstoqueRepository extends JpaRepository<MaterialEstoque, Long>, JpaSpecificationExecutor<MaterialEstoque> {
}
