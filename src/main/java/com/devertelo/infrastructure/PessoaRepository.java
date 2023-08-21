package com.devertelo.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID>, JpaSpecificationExecutor<PessoaEntity> {

    default Page<PessoaEntity> findByTerm(String term) {

        Specification<PessoaEntity> specification = Specifications.apelidoLike(term)
                .or(Specifications.nameLike(term))
                .or(Specifications.stackLike(term));

        return findAll(specification, Pageable.ofSize(2));
    }

    class Specifications {

        private Specifications() {
        }

        public static Specification<PessoaEntity> apelidoLike(String apelido) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("apelido"), "%" + apelido + "%");
        }

        public static Specification<PessoaEntity> nameLike(String name) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("nome"), "%" + name + "%");
        }

        public static Specification<PessoaEntity> stackLike(String stack) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("stack"), "%" + stack + "%");
        }
    }
}
