package com.gucarsoft.bulutmdyemek.controller;

import com.gucarsoft.bulutmdyemek.model.Person;
import com.gucarsoft.bulutmdyemek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController extends BaseController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public List<Person> personList() {
        return personRepository.findAllByDeletedFalse();
    }

    @GetMapping("/delete/")
    public ModelAndView delete(@RequestParam Long id) {
        Person existing = personRepository.findById(id).get();
        existing.setDeleted(true);
        personRepository.save(existing);
       return new ModelAndView("redirect:http://localhost:8090/persons");
    }

    @PostMapping
    public ModelAndView create(@RequestParam String name) {

        Long id = personRepository.findByName(name).getId();

        if(id != null){
            Person existing = personRepository.findById(id).get();
            existing.setDeleted(false);
            personRepository.save(existing);
        }
        else{
            Person person=new Person();
            person.setName(name);
            person.setDeleted(false);
            personRepository.save(person);
        }
        return new ModelAndView("redirect:http://localhost:8090/persons");
    }
}
