package com.example.bidirectionalMapping.Controller;

import com.example.bidirectionalMapping.Dto.RequestDto.StudentRequestDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.StudentResponseDto;
import com.example.bidirectionalMapping.Model.Student;
import com.example.bidirectionalMapping.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Students Page";
    }
    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody StudentRequestDto studentRequestDto){
        try{
            StudentResponseDto studentResponseDto = studentService.addStudent(studentRequestDto);
            return new ResponseEntity(studentResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getStudent")
    public ResponseEntity getStudent(@RequestParam("rollNo") int id){
        try{
            StudentResponseDto studentResponseDto = studentService.getStudent(id);
            return new ResponseEntity(studentResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
