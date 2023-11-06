package com.example.bidirectionalMapping.Service;

import com.example.bidirectionalMapping.Dto.RequestDto.HeadOfDepartmentRequestDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.BranchResponseDto;
import com.example.bidirectionalMapping.Dto.ResponseDto.HeadOfDepartmentResponseDto;
import com.example.bidirectionalMapping.Exception.BranchNotFoundException;
import com.example.bidirectionalMapping.Exception.HeadOfDepartmentAlreadyExistsException;
import com.example.bidirectionalMapping.Exception.HeadOfDepartmentNotFoundException;
import com.example.bidirectionalMapping.Model.Branch;
import com.example.bidirectionalMapping.Model.HeadOfDepartment;
import com.example.bidirectionalMapping.Repository.BranchRepository;
import com.example.bidirectionalMapping.Repository.HeadOfDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeadOfDepartmentService {

    @Autowired
    HeadOfDepartmentRepository headOfDepartmentRepository;

    @Autowired
    BranchRepository branchRepository;

    public HeadOfDepartmentResponseDto addHeadOfDepartment(HeadOfDepartmentRequestDto headOfDepartmentRequestDto) {

        Optional<HeadOfDepartment> optionalHeadOfDepartment = headOfDepartmentRepository.findById(headOfDepartmentRequestDto.getHodId());
        if(optionalHeadOfDepartment.isPresent()){
            throw new HeadOfDepartmentAlreadyExistsException("Hod already exists");
        }

        Optional<Branch> optionalBranch = branchRepository.findById(headOfDepartmentRequestDto.getBranchId());
        if(!optionalBranch.isPresent()){
            throw new BranchNotFoundException("Branch with given branchId doesn`t exists");
        }
        Branch branch =optionalBranch.get();

        HeadOfDepartment headOfDepartment = new HeadOfDepartment();
        headOfDepartment.setId(headOfDepartmentRequestDto.getHodId());
        headOfDepartment.setName(headOfDepartmentRequestDto.getName());
        headOfDepartment.setBranch(branch);

        //add hod in branch also
        branch.setHeadOfDepartment(headOfDepartment);

        Branch savedBranch = branchRepository.save(branch);

        //Prepare responseDto
        BranchResponseDto branchResponseDto = new BranchResponseDto();
        branchResponseDto.setBranchName(savedBranch.getBranchName());
        branchResponseDto.setMessage("Branch found");

        HeadOfDepartmentResponseDto headOfDepartmentResponseDto = new HeadOfDepartmentResponseDto();
        headOfDepartmentResponseDto.setName(savedBranch.getHeadOfDepartment().getName());
        headOfDepartmentResponseDto.setMessage("Hod added Successfully.!!");
        headOfDepartmentResponseDto.setBranchResponseDto(branchResponseDto);

        return headOfDepartmentResponseDto;
    }

    public HeadOfDepartmentResponseDto getHod(int id) {

        Optional<HeadOfDepartment> optionalHeadOfDepartment = headOfDepartmentRepository.findById(id);
        if(!optionalHeadOfDepartment.isPresent()){
            throw new HeadOfDepartmentNotFoundException("Hod with given id doesn`t exists");
        }
        HeadOfDepartment headOfDepartment = optionalHeadOfDepartment.get();

        Optional<Branch> optionalBranch = branchRepository.findById(headOfDepartment.getBranch().getBranchId());
        if(!optionalBranch.isPresent()){
            throw new BranchNotFoundException("Branch with given branchId doesn`t exists");
        }
        Branch branch =optionalBranch.get();

        //Prepare responseDto
        BranchResponseDto branchResponseDto = new BranchResponseDto();
        branchResponseDto.setBranchName(branch.getBranchName());
        branchResponseDto.setMessage("Branch found");

        HeadOfDepartmentResponseDto headOfDepartmentResponseDto = new HeadOfDepartmentResponseDto();
        headOfDepartmentResponseDto.setName(headOfDepartment.getName());
        headOfDepartmentResponseDto.setMessage("Hod found.!!");
        headOfDepartmentResponseDto.setBranchResponseDto(branchResponseDto);

        return headOfDepartmentResponseDto;

    }
}
