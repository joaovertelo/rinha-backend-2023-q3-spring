package com.devertelo.domain;

import com.devertelo.controller.pessoa.Pessoa;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

public interface PessoaService {
    @Async
    void create(Pessoa pessoa);

    Optional<Pessoa> getById(UUID id);

    List<Pessoa> getAll(String term);

    Long count();

}
