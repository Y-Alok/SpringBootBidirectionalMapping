package com.example.bidirectionalMapping.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeadOfDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @OneToOne
    @JoinColumn
    Branch branch;

    @OneToMany(mappedBy = "hod", cascade = CascadeType.ALL)
    List<Student>students = new ArrayList<>();

}
