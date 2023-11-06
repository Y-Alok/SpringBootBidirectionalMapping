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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Branch {

    @Id
    int branchId;

    String branchName;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    List<Student> students= new ArrayList<>();

    @OneToOne(mappedBy = "branch", cascade = CascadeType.ALL)
    HeadOfDepartment headOfDepartment;

}
