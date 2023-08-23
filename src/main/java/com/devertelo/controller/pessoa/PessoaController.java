package com.devertelo.controller.pessoa;

import com.devertelo.application.exceptions.AlreadyExistsException;
import com.devertelo.domain.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    private final RedisTemplate<String, Pessoa> redisTemplate;


    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> post(@RequestBody @Valid Pessoa requestBody) {
        if (redisTemplate.opsForValue().get(requestBody.apelido()) != null) {
            throw new AlreadyExistsException();
        }
        var pessoa = requestBody.withId(UUID.randomUUID());

        pessoaService.create(pessoa);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.id())
                .toUri();

        redisTemplate.opsForValue().set(pessoa.id().toString(), pessoa);
        redisTemplate.opsForValue().set(pessoa.apelido(), pessoa);

        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable UUID id) {
        var cache = redisTemplate.opsForValue().get(id.toString());
        if (cache != null) {
            return ResponseEntity.ok(cache);
        }
        var pessoa = pessoaService.getById(id);

        return pessoa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> get(@RequestParam String t) {
        if (t == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(pessoaService.getAll(t));
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<Long> get() {
        return ResponseEntity.ok(pessoaService.count());
    }


}
