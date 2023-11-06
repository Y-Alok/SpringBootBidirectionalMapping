package com.example.bidirectionalMapping.Repository;

import com.example.bidirectionalMapping.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

//    Student findByUsername(String userName);
}
