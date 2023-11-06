package com.example.bidirectionalMapping.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int rollNo;

//    @Column(unique = true)
//    String userName;
//
//    String password;
//
//    String role;

    String name;

    @ManyToOne
    @JoinColumn     // creates colum of FK of branch in student table
    Branch branch;

    @ManyToOne
    @JoinColumn
    HeadOfDepartment hod;

}
