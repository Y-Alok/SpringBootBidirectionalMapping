package com.example.bidirectionalMapping.Repository;

import com.example.bidirectionalMapping.Model.HeadOfDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadOfDepartmentRepository extends JpaRepository<HeadOfDepartment, Integer> {
}
