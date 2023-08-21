package com.devertelo.controller.pessoa;

import com.devertelo.domain.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> post(@RequestBody @Valid Pessoa pessoa) {
        var responseBody = pessoaService.create(pessoa);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseBody.id())
                .toUri();
        return ResponseEntity.created(uri)
                .body(responseBody);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable UUID id) {
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
