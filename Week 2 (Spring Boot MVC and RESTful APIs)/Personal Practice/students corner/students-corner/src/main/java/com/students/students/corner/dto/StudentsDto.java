package com.students.students.corner.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentsDto {
    private Long studentId;
    private String name;
    private Long rollNo;
    private Character division;
    private LocalDateTime dateOfJoining;
    private String password;
}
