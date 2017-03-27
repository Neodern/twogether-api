package com.twogether.backend.api.repository.user;

import com.twogether.backend.api.domain.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstnameContainingIgnoreCase(String firstname);

    Optional<Person> findByLogin(String login);
}
