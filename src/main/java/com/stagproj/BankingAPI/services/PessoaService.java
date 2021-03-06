package com.stagproj.BankingAPI.services;
import com.stagproj.BankingAPI.entities.Pessoa;
import java.util.List;


public interface PessoaService {
    List<Pessoa> getAll();

    Pessoa getPessoa(long id);

    Pessoa criacaoDados(Pessoa pessoa) throws Exception;
}
