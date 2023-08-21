package com.devertelo.controller;

import com.devertelo.domain.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> post(@RequestBody @Valid Pessoa pessoa) {
//        var violations = validator.validate(pessoa);
//        if (!violations.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
//        }
        return ResponseEntity.ok(pessoaService.create(pessoa));
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<String> get(@PathVariable UUID id) {
        return ResponseEntity.ok("teste");
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> get(@RequestParam String t) {
        if (t == null) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.ok(pessoaService.getAll(t));
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("ok");
    }


}
