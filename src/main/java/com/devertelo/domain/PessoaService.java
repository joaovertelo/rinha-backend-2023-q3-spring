package com.devertelo.domain;

import com.devertelo.controller.pessoa.Pessoa;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface PessoaService {
    Mono<Pessoa> create(Pessoa pessoa);

    Mono<Pessoa> getById(UUID id);

    Mono<List<Pessoa>> getAll(String term);

    Mono<Long> count();

}
