package com.studyproj.banking.data.repository;

import com.studyproj.banking.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByCpf(String cpf);

    Person findByAccountId(Long idConta);
}