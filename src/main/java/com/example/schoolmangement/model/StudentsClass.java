package com.example.schoolmangement.model;


import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class StudentsClass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @SequenceGenerator(name = "strategy", sequenceName = "strategy", allocationSize = 1)
    @Column(name = "id")
    private int studentId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "Class")
    private String studentClass;

    private boolean isActive;

    @Column(name = "aboutStudent", length = 10485760)
    private String aboutStudent;

    @Column(name = "medicalRecords", length = 10485760)
    private String medicalRecords;

    @Column(name = "dob")
    private String dob;

    @Column(name = "State_Of_Origin")
    private String stateOfOrigin;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address", length = 10485760)
    private String address;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "photos", nullable = true)
    private String studentPhotos;

    @Transient
    public String getPhotosImagePath() {
        if (studentPhotos.isEmpty() && studentId == 0) {
            return "/img/avatarImg.jpg";
        } else {
            return "/productImages/" + studentPhotos;
        }
    }

    @ElementCollection
    @CollectionTable(name = "FeesPaid", joinColumns = @JoinColumn(name = "students_id"))
    @Column(name = "fees_paid")
    private List<Integer> feesPaid = new LinkedList<>();


    public int addNumbers() {
        int sum = 0;
        for (int i = 0; i < feesPaid.size(); i++) {
            sum += feesPaid.get(i);
        }
        return sum;
    }

    @Column(name = "totalFees")
    private Integer totalFees = 0;

    @Column(name = "Balance")
    private Integer feesBalance;
}

