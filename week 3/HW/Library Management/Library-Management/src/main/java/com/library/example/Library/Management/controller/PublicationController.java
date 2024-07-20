package com.library.example.Library.Management.controller;

import com.library.example.Library.Management.dto.PublicationDto;
import com.library.example.Library.Management.service.PublicationService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "publication")
public class PublicationController {

    final private PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping("/{publicationId}")
    public Optional<PublicationDto> getPublicationById(@PathVariable Long publicationId){
        return publicationService.getPublicationById(publicationId);
    }

    @GetMapping
    public ResponseEntity<List<PublicationDto>> getAllPublications(){
        return ResponseEntity.ok(publicationService.getAllPublications());
    }

    @DeleteMapping(path = "{publicationId}")
    public ResponseEntity<Boolean> deletePublicationById(@PathVariable Long publicationId){
        boolean isDeleted = publicationService.deletePublicationById(publicationId);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PublicationDto> createPublication(@RequestBody PublicationDto publicationDto){
        return ResponseEntity.ok(publicationService.createPublication(publicationDto));
    }

    @PutMapping(path = "{publicationId}/book/{bookId}")
    public ResponseEntity<PublicationDto> assignPublicationToBook(@PathVariable Long publicationId,
                                                                  @PathVariable Long bookId){
        return ResponseEntity.ok(publicationService.assignPublicationToBook(publicationId,bookId));
    }
}
