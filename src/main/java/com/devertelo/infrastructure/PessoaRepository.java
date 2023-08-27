package com.devertelo.infrastructure;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;


@Repository
public interface PessoaRepository extends R2dbcRepository<PessoaEntity, UUID> {

    @Query("SELECT p.id, p.apelido, p.nome, p.nascimento, p.stack FROM pessoa p " +
            "WHERE p.apelido LIKE CONCAT('%', :term, '%') " +
            "OR p.nome LIKE CONCAT('%', :term, '%') " +
            "OR p.nascimento LIKE CONCAT('%', :term, '%') " +
            "OR p.stack LIKE CONCAT('%', :term, '%')")
    Flux<PessoaEntity> findAllByTerm(String term);

}
