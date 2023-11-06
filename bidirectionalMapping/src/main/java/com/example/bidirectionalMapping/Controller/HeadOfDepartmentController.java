package com.example.bidirectionalMapping.Controller;

import com.example.bidirectionalMapping.Dto.RequestDto.HeadOfDepartmentRequestDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.HeadOfDepartmentResponseDto;
import com.example.bidirectionalMapping.Model.HeadOfDepartment;
import com.example.bidirectionalMapping.Service.HeadOfDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hod")
public class HeadOfDepartmentController {

    @Autowired
    HeadOfDepartmentService headOfDepartmentService;

    @PostMapping("/addHod")
    public ResponseEntity addHeadOfDepartment(@RequestBody HeadOfDepartmentRequestDto headOfDepartmentRequestDto){
        try {
            HeadOfDepartmentResponseDto headOfDepartmentResponseDto = headOfDepartmentService.addHeadOfDepartment(headOfDepartmentRequestDto);
            return new ResponseEntity(headOfDepartmentResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getHod")
    public ResponseEntity getHeadOfDepartment(@RequestParam("id") int id){
        try{
            HeadOfDepartmentResponseDto headOfDepartmentResponseDto = headOfDepartmentService.getHod(id);
            return new ResponseEntity(headOfDepartmentResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
