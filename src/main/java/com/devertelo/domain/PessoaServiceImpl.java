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
import java.util.Optional;
import java.util.UUID;
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

    @Override
    public Optional<Pessoa> getById(UUID id) {
        var entity = pessoaRepository.findById(id);
        return entity.map(this::entityToDTO);
    }

    public List<Pessoa> getAll(String term) {
        var entities = pessoaRepository.findByTerm(term);
        return entities.get()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return pessoaRepository.count();
    }

    private PessoaEntity dtoToEntity(Pessoa pessoa) {
        var stack = pessoa.stack() != null ? String.join(";", pessoa.stack()) : null;
        return new PessoaEntity(pessoa.apelido(), pessoa.nome(), pessoa.nascimento(), stack);
    }

    private Pessoa entityToDTO(PessoaEntity entity) {
        var stacks = entity.getStack() != null ? Arrays.stream(entity.getStack().split(";")).toList() : null;
        return new Pessoa(entity.getId(), entity.getApelido(), entity.getNome(), entity.getNascimento(), stacks);
    }
}
