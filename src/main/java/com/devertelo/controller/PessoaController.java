package com.devertelo.controller;

import com.devertelo.controller.domain.PessoaService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.micronaut.validation.validator.Validator;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@Controller
@Validated
public class PessoaController {

    private final Validator validator;
    private final PessoaService pessoaService;

    public PessoaController(Validator validator, PessoaService pessoaService) {
        this.validator = validator;
        this.pessoaService = pessoaService;
    }

    @Post("/pessoas")
    public HttpResponse<Pessoa> post(@Body @Valid Pessoa pessoa) {
        var violations = validator.validate(pessoa);
        if (!violations.isEmpty()) {
            return HttpResponse.unprocessableEntity();
        }
        return HttpResponse.created(pessoaService.create(pessoa));
    }

    @Get("/pessoas/{id}")
    public HttpResponse<String> get(@PathVariable UUID id) {
        return HttpResponse.ok("teste");
    }

    @Get("/pessoas")
    public HttpResponse<List<Pessoa>> get(@QueryValue String t) {
        if (t == null) {
            return HttpResponse.badRequest();
        }

        return HttpResponse.ok(pessoaService.getAll(t));
    }

    @Get("/contagem-pessoas")
    public HttpResponse<String> get() {
        return HttpResponse.ok("ok");
    }


}
