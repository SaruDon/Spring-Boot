package com.example.Office.controller;


import com.example.Office.dto.ProfessorDto;
import com.example.Office.repository.ProfessorRepository;
import com.example.Office.services.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService, ModelMapper modelMapper) {
        this.professorService = professorService;
    }

    @GetMapping
    private ResponseEntity<List<ProfessorDto>> getAllProfessor(){
        return ResponseEntity.ok(professorService.getAllProfessor());
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProfessorDto> getProfessorById(@PathVariable Long id){
        Optional<ProfessorDto> professorDto = professorService.getProfessorById(id);
        return professorDto
                .map(professorDto1 -> ResponseEntity.ok(professorDto1))
                .orElse(null);
    }

    @PostMapping
    private ResponseEntity<ProfessorDto> createProfessor(@RequestBody ProfessorDto professorDto){
        return new ResponseEntity<>(professorService.createProfessor(professorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private  ResponseEntity<ProfessorDto> updateProfessor(@RequestBody ProfessorDto professorDto, @PathVariable Long id){
        return ResponseEntity.ok(professorService.updateProfessor(professorDto,id));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ProfessorDto> partialUpdate(@RequestBody Map<String,Object>updates, @PathVariable Long id){
        return ResponseEntity.ok(professorService.partialUpdate(updates,id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteProfessorById(@PathVariable Long id){
        return ResponseEntity.ok(professorService.deleteProfessorById(id));
    }

    @PutMapping("/{id}/addStudent/{studentId}")
    private ResponseEntity<ProfessorDto> addStudent(@PathVariable Long id ,@PathVariable Long studentId){
        return ResponseEntity.ok(professorService.addStudent(id,studentId));
    }

}
