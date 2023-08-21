package com.devertelo.domain;

import com.devertelo.controller.pessoa.Pessoa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaService {
    Pessoa create(Pessoa pessoa);

    Optional<Pessoa> getById(UUID id);

    List<Pessoa> getAll(String term);

    Long count();

}
