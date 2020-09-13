package com.gucarsoft.bulutmdyemek.controller;

import com.gucarsoft.bulutmdyemek.service.FoodService;
import com.gucarsoft.bulutmdyemek.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    public FoodService foodService;

    @Autowired
    public PersonService personService;

}
