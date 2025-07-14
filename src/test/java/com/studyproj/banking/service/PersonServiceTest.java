package com.studyproj.banking.service;

import com.studyproj.banking.data.entity.Account;
import com.studyproj.banking.data.entity.Person;
import com.studyproj.banking.config.ExceptionMessage;
import com.studyproj.banking.data.repository.AccountRepository;
import com.studyproj.banking.data.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private PersonService personService;

    private PersonRepository personRepository;

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository, accountService);
    }

    @Test
    void getsuccessGetAllPerson() {
        Person person1 = mock(Person.class);
        Person person2 = mock(Person.class);

        when(personService.getAll()).thenReturn(List.of(person1, person2));


        Assertions.assertDoesNotThrow(() -> personService.getAll());
        Assertions.assertEquals(2, personService.getAll().size());
    }

    @Test
    void getSuccessPersonById() {
        Person person = mock(Person.class);

        when(personRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(person));

        Assertions.assertDoesNotThrow(() -> personService.getPersonById(Mockito.anyLong()));
    }

    @Test
    void getSuccessWhenCreateANewPerson() {
        Person person = new Person();
        Account account = new Account();
        account.setId(1L);
        person.setAccount(account);

        when(personRepository.findByAccountId(Mockito.anyLong())).thenReturn(null);
        when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));

        Assertions.assertDoesNotThrow(() -> personService.createPerson(person));
    }

    @Test
    void getAccountAlreadyHasPersonalDataWhenCreateANewPerson() {
        Person person = new Person();
        Account account = new Account();
        account.setId(1L);
        person.setAccount(account);

        when(personRepository.findByAccountId(Mockito.anyLong())).thenReturn(mock(Person.class));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> personService.createPerson(person), "This account already contains personal data");

    }

    @Test
    void getAccountDontExistWhenCreateANewPerson() {
        Person person = new Person();
        Account account = new Account();
        account.setId(1L);
        person.setAccount(account);

        when(personRepository.findByAccountId(Mockito.anyLong())).thenReturn(mock(Person.class));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> personService.createPerson(person), "Account does not exist.");

    }

    @Test
    void getCpfAlreadyExitsWhenCreateANewPerson() {
        Person person = new Person();
        Account account = new Account();
        account.setId(1L);
        person.setAccount(account);

        when(personRepository.findByAccountId(Mockito.anyLong())).thenReturn(null);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(account));
        when(personRepository.findByCpf(person.getCpf())).thenReturn(mock(Person.class));

        Assertions.assertThrowsExactly(ExceptionMessage.class, () -> personService.createPerson(person), "The CPF entered already belongs to another person.");
    }

}
