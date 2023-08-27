package com.devertelo.domain;

import com.devertelo.controller.pessoa.Pessoa;
import com.devertelo.infrastructure.PessoaEntity;
import com.devertelo.infrastructure.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public Mono<Pessoa> create(com.devertelo.controller.pessoa.Pessoa pessoa) {
        var entity = dtoToEntity(pessoa);
        return pessoaRepository.save(entity)
                .map(this::entityToDTO);
    }

    @Override
    public Mono<com.devertelo.controller.pessoa.Pessoa> getById(UUID id) {
        Mono<PessoaEntity> byId = pessoaRepository.findById(id);
        return byId
                .map(this::entityToDTO)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<List<com.devertelo.controller.pessoa.Pessoa>> getAll(String term) {
        var entities = pessoaRepository.findAllByTerm(term);
//        var entities = pessoaRepository.findAll();
        return entities
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Mono<Long> count() {
        return pessoaRepository.count();
    }

    private PessoaEntity dtoToEntity(com.devertelo.controller.pessoa.Pessoa pessoa) {
        var stack = pessoa.stack() != null ? String.join(";", pessoa.stack()) : null;
        return new PessoaEntity(pessoa.id(), pessoa.apelido(), pessoa.nome(), pessoa.nascimento(), stack, true);
    }

    private com.devertelo.controller.pessoa.Pessoa entityToDTO(PessoaEntity entity) {
        var stacks = entity.getStack() != null ? Arrays.stream(entity.getStack().split(";")).toList() : null;
        return new com.devertelo.controller.pessoa.Pessoa(entity.getId(), entity.getApelido(), entity.getNome(), entity.getNascimento(), stacks);
    }
}
