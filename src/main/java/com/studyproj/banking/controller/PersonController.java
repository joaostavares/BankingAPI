package com.studyproj.banking.controller;

import com.studyproj.banking.domain.reponses.PersonResponse;
import com.studyproj.banking.domain.requests.PersonRequest;
import com.studyproj.banking.data.entity.Person;
import com.studyproj.banking.config.ExceptionMessage;
import com.studyproj.banking.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person", description = "Person Controller")
@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> personList = personService.getAll();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPessoa(@PathVariable long id) {
        Person person = personService.getPersonById(id);
        return new ResponseEntity<>(person, (person != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<PersonResponse> post(@Valid @RequestBody PersonRequest personRequest) throws ExceptionMessage {

        Person person = modelMapper.map(personRequest, Person.class);
        Person creation = personService.createPerson(person);
        PersonResponse personResponse = modelMapper.map(creation, PersonResponse.class);
        return new ResponseEntity<>(personResponse, (creation != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

}
