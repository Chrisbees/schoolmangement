package com.example.schoolmangement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
@Data
public class StaffClass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @SequenceGenerator(name = "strategy", sequenceName = "strategy", allocationSize = 1)
    @Column(name = "id")
    private int staffId;

    @Column(name = "roles")
    private String roles;

    @Column(name = "userName")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "education", length = 10485760)
    private String education;

    @Column(name = "aboutStaff", length = 10485760)
    private String aboutStaff;

    @Column(name = "skills", length = 10485760)
    private String skills;

    @Column(name = "email", unique = true,nullable = false)
    private String email;

    @Column(name = "dob")
    private String dob;

    @Column(name = "Salary")
    private String salary;

    @Column(name = "State_Of_Origin")
    private String stateOfOrigin;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address", length = 10485760)
    private String address;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "photos", nullable = true)
    private String photos;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || staffId == 0) return null;

        return "/productImages/" + photos;
    }

    public boolean isActive;

    @ManyToOne
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "staff_user", joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<Users> users;

}
