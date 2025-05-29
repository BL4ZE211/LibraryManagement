package com.LibraryManager.Library.Service;

import com.LibraryManager.Library.Entity.Author;
import com.LibraryManager.Library.Exception.ResourceNotFoundException;
import com.LibraryManager.Library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createObject(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }


    public Author getAuthorByID(long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Author not found with id "+id));
        return author;
    }


    public Author updateAuthor(Author author1,long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Author not found with id "+author1.getId()));
        author.setName(author1.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Author not found with id "+id));

        authorRepository.deleteById(id);

    }
}
