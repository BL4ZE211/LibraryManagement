package com.LibraryManager.Library.Controller;

import com.LibraryManager.Library.Entity.Author;
import com.LibraryManager.Library.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return authorService.createObject(author);
    }

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public  Author getAuthorByID(@PathVariable long id){
        return authorService.getAuthorByID(id);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@RequestBody Author author,@PathVariable long id){
        return authorService.updateAuthor(author, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable long id){
         authorService.deleteAuthor(id);
    }
}
