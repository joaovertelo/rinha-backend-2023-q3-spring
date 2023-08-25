//package com.devertelo.infrastructure;
//
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//
//import java.util.UUID;
//
//
////, JpaSpecificationExecutor<PessoaEntity>
////@Repository
//public interface Pessoa2Repository extends ReactiveCrudRepository<PessoaEntity, UUID> {
//
////    default Page<PessoaEntity> findByTerm(String term) {
////
////        Specification<PessoaEntity> specification = Specifications.apelidoLike(term)
////                .or(Specifications.nameLike(term))
////                .or(Specifications.stackLike(term));
////
////        return findAll(specification, Pageable.ofSize(50));
////    }
////
////    class Specifications {
////
////        private Specifications() {
////        }
////
////        public static Specification<PessoaEntity> apelidoLike(String apelido) {
////            return (root, query, criteriaBuilder)
////                    -> criteriaBuilder.like(root.get("apelido"), "%" + apelido + "%");
////        }
////
////        public static Specification<PessoaEntity> nameLike(String name) {
////            return (root, query, criteriaBuilder)
////                    -> criteriaBuilder.like(root.get("nome"), "%" + name + "%");
////        }
////
////        public static Specification<PessoaEntity> stackLike(String stack) {
////            return (root, query, criteriaBuilder)
////                    -> criteriaBuilder.like(root.get("stack"), "%" + stack + "%");
////        }
////    }
//}
