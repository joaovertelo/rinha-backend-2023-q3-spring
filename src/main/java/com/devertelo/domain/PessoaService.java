package com.devertelo.domain;

import com.devertelo.controller.pessoa.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa create(Pessoa pessoa);

    List<Pessoa> getAll(String term);

}
