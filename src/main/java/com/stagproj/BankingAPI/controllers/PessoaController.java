package com.stagproj.BankingAPI.controllers;

import com.stagproj.BankingAPI.dtos.reponses.PessoaResponse;
import com.stagproj.BankingAPI.dtos.requests.PessoaRequest;
import com.stagproj.BankingAPI.entities.Pessoa;
import com.stagproj.BankingAPI.services.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;
    private final ModelMapper modelMapper;

    public PessoaController(PessoaService pessoaService, ModelMapper modelMapper) {
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        List<Pessoa> valores = pessoaService.getAll();
        return new ResponseEntity<>(valores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable long id) {
        Pessoa pessoa = pessoaService.getPessoa(id);
        return new ResponseEntity<>(pessoa, (pessoa != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody PessoaRequest pessoaRequest) {

        Pessoa pessoa = modelMapper.map(pessoaRequest, Pessoa.class);
        try {
            Pessoa criacao = pessoaService.criacaoDados(pessoa);
            PessoaResponse pessoaResponse = modelMapper.map(criacao, PessoaResponse.class);
            return new ResponseEntity<>(pessoaResponse, HttpStatus.CREATED);
        }catch (Exception ee) {
            return ResponseEntity.badRequest().body(ee.getMessage());
        }
    }

}
