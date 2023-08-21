package com.devertelo.controller.pessoa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record Pessoa(
        UUID id,
        @Size(max = 32) @NotNull String apelido,
        @Size(max = 100) @NotNull String nome,
        String nascimento,
        List<String> stack) {
}
