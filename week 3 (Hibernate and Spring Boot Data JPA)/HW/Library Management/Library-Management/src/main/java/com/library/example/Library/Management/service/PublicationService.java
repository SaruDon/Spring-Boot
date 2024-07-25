package com.library.example.Library.Management.service;

import com.library.example.Library.Management.dto.PublicationDto;
import com.library.example.Library.Management.entity.Author;
import com.library.example.Library.Management.entity.Book;
import com.library.example.Library.Management.entity.Publication;
import com.library.example.Library.Management.repository.AuthorRepo;
import com.library.example.Library.Management.repository.BookRepo;
import com.library.example.Library.Management.repository.PublicationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationService {

    final private PublicationRepo publicationRepo;
    final private BookRepo bookRepo;
    final private AuthorRepo authorRepo;
    final private ModelMapper modelMapper;


    public PublicationService(PublicationRepo publicationRepo, BookRepo bookRepo, AuthorRepo authorRepo, ModelMapper modelMapper) {
        this.publicationRepo = publicationRepo;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.modelMapper = modelMapper;
    }

    public Optional<PublicationDto> getPublicationById(Long publicationId) {
        return  publicationRepo.findById(publicationId).map(publication -> modelMapper.map(publication,PublicationDto.class));
    }


    public PublicationDto createPublication(PublicationDto publicationDto) {
        Publication publicationToSave = modelMapper.map(publicationDto, Publication.class);
        return modelMapper.map(publicationRepo.save(publicationToSave), PublicationDto.class);
    }

    public PublicationDto assignPublicationToBook(Long publicationId, Long bookId) {
        Optional<Publication> publicationE = publicationRepo.findById(publicationId);
        Optional<Book>  bookE = bookRepo.findById(bookId);

        Publication assignedBookPublication = publicationE.flatMap(publication ->
                bookE.map(book -> {
                    book.setPublication(publication);
                    bookRepo.save(book);

                    publication.getBooks().add(book);

                    // Update authors in the publication
                    Author author = book.getAuthor();
                    publication.getAuthors().add(author);
                    author.getPublicationsList().add(publication);
                    authorRepo.save(author);

                    return publication;
                })).orElse(null);

        return modelMapper.map(assignedBookPublication, PublicationDto.class);
    }

    public List<PublicationDto> getAllPublications() {
        List<Publication> allPublications = publicationRepo.findAll();
        return allPublications.stream()
                .map(publication -> modelMapper.map(publication,PublicationDto.class))
                .collect(Collectors.toList());
    }

    public boolean deletePublicationById(Long publicationId) {
        if (!publicationExist(publicationId)) return false;
        publicationRepo.deleteById(publicationId);
        return true;
    }
    public Boolean publicationExist(Long publicationId){
        return  publicationRepo.existsById(publicationId);
    }
}
