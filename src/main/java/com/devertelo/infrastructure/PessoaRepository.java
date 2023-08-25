package com.devertelo.infrastructure;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PessoaRepository extends R2dbcRepository<PessoaEntity, UUID> {

}
