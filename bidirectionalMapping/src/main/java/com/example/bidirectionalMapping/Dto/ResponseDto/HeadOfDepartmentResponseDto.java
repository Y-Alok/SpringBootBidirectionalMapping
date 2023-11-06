package com.example.bidirectionalMapping.Dto.ResponseDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeadOfDepartmentResponseDto {

    String name;

    String message;

    BranchResponseDto branchResponseDto;

}
