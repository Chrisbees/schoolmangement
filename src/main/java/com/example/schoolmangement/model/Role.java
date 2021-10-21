package com.example.schoolmangement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Parents> parentsList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StaffClass> staffClasses;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Users> users;

}
