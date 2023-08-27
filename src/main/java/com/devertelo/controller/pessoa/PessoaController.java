package com.devertelo.controller.pessoa;

import com.devertelo.application.exceptions.AlreadyExistsException;
import com.devertelo.domain.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    private final RedisTemplate<String, Pessoa> redisTemplate;


    @PostMapping("/pessoas")
    public Mono<ResponseEntity<Pessoa>> post(@RequestBody @Valid Pessoa requestBody) {
        if (redisTemplate.opsForValue().get(requestBody.apelido()) != null) {
            throw new AlreadyExistsException();
        }
        var pessoa = requestBody.withId(UUID.randomUUID());


//        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(pessoa.id())
//                .toUri();

        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:9999/pessoas/{id}").build(pessoa.id().toString());

        redisTemplate.opsForValue().set(pessoa.id().toString(), pessoa);
        redisTemplate.opsForValue().set(pessoa.apelido(), pessoa);

        return pessoaService.create(pessoa).map(it -> ResponseEntity.created(uri).body(it));
    }

    @GetMapping("/pessoas/{id}")
    public Mono<ResponseEntity<Pessoa>> getBydId(@PathVariable UUID id) {
        var cache = redisTemplate.opsForValue().get(id.toString());
        if (cache != null) {
            return Mono.just(ResponseEntity.ok(cache));
        }
        var pessoa = pessoaService.getById(id);

        return pessoa.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/pessoas")
    public Mono<ResponseEntity<List<Pessoa>>> get(@RequestParam(required = false) String t) {
        if (t == null) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return pessoaService.getAll(t).map(ResponseEntity::ok);
    }

    @GetMapping("/contagem-pessoas")
    public Mono<ResponseEntity<Long>> get() {
        return pessoaService.count().map(ResponseEntity::ok);
    }


}
