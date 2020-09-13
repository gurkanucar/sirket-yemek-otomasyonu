package com.gucarsoft.bulutmdyemek.controller;

import com.gucarsoft.bulutmdyemek.model.Order;
import com.gucarsoft.bulutmdyemek.model.ResponsePojo;
import com.gucarsoft.bulutmdyemek.repository.OrderRepository;
import com.gucarsoft.bulutmdyemek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PersonRepository personRepository;

    public ResponsePojo list() {
        List<Order> orderList = new ArrayList<>();
        return new ResponsePojo(orderList);
    }

    @PostMapping
    public ModelAndView makeOrder(@RequestParam String id, @RequestParam String food) {

        Order existing = orderRepository.findByName(personRepository.findById(Long.valueOf(id)).get().getName());
        if (existing != null) {
            existing.setFood(food);
            orderRepository.save(existing);

        } else {
            Order order = new Order();
            order.setDeleted(false);
            order.setName(personRepository.findById(Long.valueOf(id)).get().getName());
            order.setFood(food);
            orderRepository.save(order);
        }
        return new ModelAndView("redirect:http://localhost:8090/afiyetolsun");
    }

    @GetMapping
    public ModelAndView afiyetolsun() {
        return new ModelAndView("redirect:http://localhost:8090/afiyetolsun");
    }

    public ResponsePojo updateOrder(Order order) {
        Order existing = new Order();
        existing.setFood(order.getFood());
        return new ResponsePojo(order);
    }

    public ResponsePojo deleteOrder(Order order) {
        return new ResponsePojo(order);
    }


}
