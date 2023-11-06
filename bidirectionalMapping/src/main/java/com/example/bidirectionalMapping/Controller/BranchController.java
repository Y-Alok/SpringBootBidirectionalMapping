package com.example.bidirectionalMapping.Controller;

import com.example.bidirectionalMapping.Dto.RequestDto.BranchRequestDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.BranchResponseDto;
import com.example.bidirectionalMapping.Model.Branch;
import com.example.bidirectionalMapping.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping("/addBranch")
    public ResponseEntity addBranch(@RequestBody BranchRequestDto branchRequestDto){
        try{
            BranchResponseDto branchResponseDto = branchService.addBranch(branchRequestDto);
            return new ResponseEntity(branchResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBranch")
    public ResponseEntity getBranch(@RequestParam("branchId") int branchId){
        try{
            BranchResponseDto branchResponseDto = branchService.getBranch(branchId);
            return new ResponseEntity(branchResponseDto, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
