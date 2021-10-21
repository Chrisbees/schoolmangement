package com.example.schoolmangement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    private String password;
    private boolean enabled = true;

    @ManyToOne
    private Role userRole;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
    private List<Parents> parents;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
    private List<StaffClass> staffClasses;

    //
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name =
//    "role_id"))
//    private Set<Role> roles = new HashSet<>();
}
