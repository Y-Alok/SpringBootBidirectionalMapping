package com.example.bidirectionalMapping.Service;

import com.example.bidirectionalMapping.Dto.RequestDto.StudentRequestDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.StudentResponseDto;
import com.example.bidirectionalMapping.Exception.BranchNotFoundException;
import com.example.bidirectionalMapping.Exception.StudentAlreadyExistsException;
import com.example.bidirectionalMapping.Exception.StudentNotFoundException;
import com.example.bidirectionalMapping.Model.Branch;
import com.example.bidirectionalMapping.Model.HeadOfDepartment;
import com.example.bidirectionalMapping.Model.Student;
import com.example.bidirectionalMapping.Repository.BranchRepository;
import com.example.bidirectionalMapping.Repository.HeadOfDepartmentRepository;
import com.example.bidirectionalMapping.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    HeadOfDepartmentRepository headOfDepartmentRepository;


    public StudentResponseDto addStudent(StudentRequestDto studentRequestDto) {

        Optional<Student>optionalStudent = studentRepository.findById(studentRequestDto.getRollNo());
        if(optionalStudent.isPresent()){
            throw new StudentAlreadyExistsException("Student already exists");
        }
//        Student student = optionalStudent.get();

        Optional<Branch> optionalBranch = branchRepository.findById(studentRequestDto.getBranchId());
        if(!optionalBranch.isPresent()){
            throw new BranchNotFoundException("Branch with given id doesn`t exist so cannot add student");
        }
        Branch branch = optionalBranch.get();

        //create student from studentRequestDto
        Student student = new Student();
        student.setRollNo(studentRequestDto.getRollNo());
        student.setName(studentRequestDto.getName());
        student.setBranch(branch);
        student.setHod(branch.getHeadOfDepartment());

        branch.getStudents().add(student);
        Branch savedBranch = branchRepository.save(branch);

        //response entity
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setBranchName(savedBranch.getBranchName());
        studentResponseDto.setHodName(savedBranch.getHeadOfDepartment().getName());
        studentResponseDto.setStudentName(studentRequestDto.getName());
        studentResponseDto.setMessage("Student added successfully");

        return studentResponseDto;

    }

    public StudentResponseDto getStudent(int id) {

        Optional<Student>optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new StudentNotFoundException("Student doesn`t exist");
        }
        Student student = optionalStudent.get();
        Branch branch = student.getBranch();

//        Optional<Branch> optionalBranch = branchRepository.findById(branch.getBranchId());
//        if(!optionalBranch.isPresent()){
//            throw new BranchNotFoundException("Branch with given id doesn`t exist so cannot add student");
//        }

        //response entity
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setBranchName(branch.getBranchName());
        studentResponseDto.setHodName(branch.getHeadOfDepartment().getName());
        studentResponseDto.setStudentName(student.getName());
        studentResponseDto.setMessage("Student Found");

        return studentResponseDto;

    }
}
