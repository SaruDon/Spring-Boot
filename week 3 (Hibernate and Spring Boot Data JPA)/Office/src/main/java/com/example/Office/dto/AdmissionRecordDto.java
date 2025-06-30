package com.example.Office.dto;



import com.example.Office.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordDto {
    private Long id;

    private Integer fees;

    private Student student;
}
