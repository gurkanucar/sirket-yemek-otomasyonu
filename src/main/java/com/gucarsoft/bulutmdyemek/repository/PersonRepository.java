package com.gucarsoft.bulutmdyemek.repository;


import com.gucarsoft.bulutmdyemek.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository  extends JpaRepository<Person, Long> {
    Person findByName(String name);
    List<Person> findAllByDeletedFalse();
}
