package com.zomato.Zomato.entity;

import com.zomato.Zomato.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user_id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private List<Roles> role;

    private String email;
    private String password;
}
