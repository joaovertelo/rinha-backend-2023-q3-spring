package com.devertelo.controller.domain;

import com.devertelo.controller.Pessoa;
import com.devertelo.infrastructure.PessoaEntity;
import com.devertelo.infrastructure.PessoaRepository;
import io.micronaut.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Bean
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa create(Pessoa pessoa) {
        var stack = pessoa.stack() != null ? String.join(";", pessoa.stack()) : null;
        var entity = new PessoaEntity(pessoa.apelido(), pessoa.nome(), pessoa.nascimento(), stack);
        var entitySaved = pessoaRepository.save(entity);
        return entityToDTO(entitySaved);
    }

    public List<Pessoa> getAll(String term) {

        var entities = pessoaRepository.findByTerm(term);

        return entities.stream().map(PessoaService::entityToDTO).collect(Collectors.toList());
    }

    private static Pessoa entityToDTO(PessoaEntity entity) {
        var stacks = entity.getStack() != null ? Arrays.stream(entity.getStack().split(";")).toList() : null;
        return new Pessoa(entity.getId(), entity.getApelido(), entity.getNome(), entity.getNascimento(), stacks);
    }
}
