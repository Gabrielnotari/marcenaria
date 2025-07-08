package br.com.marcenaria.api_marcenaria.perfil;

import jakarta.validation.constraints.NotNull;

public record DadosPerfil(@NotNull PerfilNome perfilNome) {
}
