package com.gucarsoft.bulutmdyemek.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orderTable")
@Where(clause = "deleted=false")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String food;
    private Timestamp timestamp =new Timestamp(System.currentTimeMillis());
    private boolean deleted;
}
