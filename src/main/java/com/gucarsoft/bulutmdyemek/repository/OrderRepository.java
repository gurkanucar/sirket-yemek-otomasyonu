package com.gucarsoft.bulutmdyemek.repository;


import com.gucarsoft.bulutmdyemek.model.Order;
import com.gucarsoft.bulutmdyemek.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByName(String name);
}
