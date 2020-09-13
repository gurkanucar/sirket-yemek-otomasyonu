package com.gucarsoft.bulutmdyemek.controller;

import com.gucarsoft.bulutmdyemek.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Controller
public class HomeController extends BaseController {

    @Autowired
    FoodRepository foodRepository;

    @RequestMapping(value = "/authuser", method = RequestMethod.POST)
    public ModelAndView printUserData() {
        return new ModelAndView("admin.html");
    }


    @RequestMapping("/")
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        String exist = new SimpleDateFormat("yyyy.MM.dd").format(foodRepository.findByName("food").getTimestamp());
        String now = new SimpleDateFormat("yyyy.MM.dd").format(new Timestamp(System.currentTimeMillis()));

        System.out.println(exist);

        if (exist.equals(now)) {
            modelAndView.setViewName("index.html");
        }
        else {
            modelAndView.setViewName("yuklenmedi.html");
        }


        //Files.createDirectories(Paths.get("/src/uploads"));
        return modelAndView;
    }

    @RequestMapping("/food")
    public ModelAndView food() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("food.html");
        return new ModelAndView("food.html");
    }

    @RequestMapping("/afiyetolsun")
    public ModelAndView afiyetolsun() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("food.html");
        return new ModelAndView("afiyetolsun.html");
    }

    @RequestMapping("/persons")
    public ModelAndView person() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("food.html");
        return new ModelAndView("persons.html");
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        System.out.println("login geldi");
        return modelAndView;
    }


    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin.html");
        System.out.println("login geldi");
        return modelAndView;
    }


}
