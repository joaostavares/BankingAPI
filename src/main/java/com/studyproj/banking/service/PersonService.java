package com.studyproj.banking.service;
import com.studyproj.banking.data.entity.Account;
import com.studyproj.banking.data.entity.Person;
import com.studyproj.banking.config.ExceptionMessage;
import com.studyproj.banking.data.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class PersonService{
    private final PersonRepository personRepository;
    private final AccountService accountService;

    public PersonService(PersonRepository personRepository, AccountService accountService) {
        this.personRepository = personRepository;
        this.accountService = accountService;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getPersonById(long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public Person createPerson(Person person) {
        Account account = accountService.getAccount(person.getAccount().getId());
        if (nonNull(personRepository.findByAccountId(account.getId()))) {
            throw new ExceptionMessage("This account already contains personal data");
        }
        if (isNull(account)) {
            throw new ExceptionMessage("Account does not exist.");
        }
        if (nonNull(personRepository.findByCpf(person.getCpf()))) {
            throw new ExceptionMessage("The CPF entered already belongs to another person.");
        }
        person.setAccount(account);
        personRepository.save(person);
        return person;
    }

}
