package com.example.bidirectionalMapping.Service;

import com.example.bidirectionalMapping.Dto.RequestDto.BranchRequestDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.BranchResponseDto;
import com.example.bidirectionalMapping.Exception.BranchAlreadyExistsException;
import com.example.bidirectionalMapping.Exception.BranchNotFoundException;
import com.example.bidirectionalMapping.Model.Branch;
import com.example.bidirectionalMapping.Repository.BranchRepository;
import com.example.bidirectionalMapping.Repository.HeadOfDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    HeadOfDepartmentRepository headOfDepartmentRepository;

    public BranchResponseDto addBranch(BranchRequestDto branchRequestDto) {

        Optional<Branch> optionalBranch = branchRepository.findById(branchRequestDto.getBranchId());
        if(optionalBranch.isPresent()){
            throw new BranchAlreadyExistsException("Branch already Exists");
        }
        //create new branch
        Branch newBranch = new Branch();
        newBranch.setBranchId(branchRequestDto.getBranchId());
        newBranch.setBranchName(branchRequestDto.getBranchName());

        //save branch entity
        Branch savedBranch = branchRepository.save(newBranch);

        //create ResponseEntity
        BranchResponseDto branchResponseDto = new BranchResponseDto();
        branchResponseDto.setBranchName(savedBranch.getBranchName());
        branchResponseDto.setMessage("Branch added Successfully.!!");

        return branchResponseDto;
    }

    public BranchResponseDto getBranch(int branchId) {

        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
        if(!optionalBranch.isPresent()){
            throw new BranchNotFoundException("Branch with given branchCode doesn`t exist");
        }

        //create BranchResponseDto
        BranchResponseDto branchResponseDto = new BranchResponseDto();
        branchResponseDto.setBranchName(optionalBranch.get().getBranchName());
        branchResponseDto.setMessage("Branch Found Successfully.!!");

        return branchResponseDto;
    }
}
