package com.devertelo.domain;

import com.devertelo.application.exceptions.AlreadyExistsException;
import com.devertelo.controller.pessoa.Pessoa;
import com.devertelo.infrastructure.PessoaEntity;
import com.devertelo.infrastructure.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa create(Pessoa pessoa) {
        var entity = dtoToEntity(pessoa);
        try {
            var entitySaved = pessoaRepository.save(entity);
            return entityToDTO(entitySaved);
        } catch (DataIntegrityViolationException exception) {
            throw new AlreadyExistsException(exception);
        }
    }

    public List<Pessoa> getAll(String term) {

        var entities = pessoaRepository.findByTerm(term);

        return entities.stream().map(PessoaServiceImpl::entityToDTO).collect(Collectors.toList());
    }

    private static PessoaEntity dtoToEntity(Pessoa pessoa) {
        var stack = pessoa.stack() != null ? String.join(";", pessoa.stack()) : null;
        return new PessoaEntity(pessoa.apelido(), pessoa.nome(), pessoa.nascimento(), stack);
    }

    private static Pessoa entityToDTO(PessoaEntity entity) {
        var stacks = entity.getStack() != null ? Arrays.stream(entity.getStack().split(";")).toList() : null;
        return new Pessoa(entity.getId(), entity.getApelido(), entity.getNome(), entity.getNascimento(), stacks);
    }
}
