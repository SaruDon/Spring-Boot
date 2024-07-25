package com.library.example.Library.Management.controller;

import com.library.example.Library.Management.dto.AuthorDto;
import com.library.example.Library.Management.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "author")
public class AuthorController {

    final private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping(path = "/{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long authorId){
        Optional<AuthorDto> author = authorService.getAuthorById(authorId);
        return  author.map(authorDto -> ResponseEntity.ok(authorDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthor (){
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @DeleteMapping(path = "{authorId}")
    public ResponseEntity<Boolean> deleteAuthorById(@PathVariable Long authorId){
        boolean isDeleted = authorService.deleteAuthorById(authorId);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.createAuthor(authorDto));
    }


    @PutMapping(path = "/{authorId}/book/{bookId}")
    public ResponseEntity<AuthorDto> assignAuthorToBook(@PathVariable Long authorId,
                                                        @PathVariable Long bookId){
        return ResponseEntity.ok(authorService.assignAuthorToBook(authorId,bookId));
    }

}
