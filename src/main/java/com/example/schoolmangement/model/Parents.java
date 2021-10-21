package com.example.schoolmangement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "parents")
public class Parents{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "strategy", sequenceName = "strategy", allocationSize = 1)
    @Column(name = "parents_id")
    private int parentId;

    @Column(name = "user_name",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    private boolean active = true;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @ManyToOne
    private Role role;

    @Column(name = "guardians_Information")
    private String guardiansInformation;

    @Column(name = "guardian_Phone_Number")
    private String phoneNumber;

    @Column(name = "address", length = 10485760)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "photos")
    private String parentPhotos;

    @Transient
    public String getPhotosImagePath() {
        if (parentPhotos.isEmpty() && parentId == 0) {
            return null;
        } else {
            return "/productImages/" + parentPhotos.trim();
        }
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ps_fk", referencedColumnName = "parents_id")
    public List<StudentsClass> students;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "parents_users", joinColumns = @JoinColumn(name = "parents_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<Users> users;
}
