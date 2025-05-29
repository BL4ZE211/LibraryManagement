package com.LibraryManager.Library.Controller;

import com.LibraryManager.Library.Entity.Book;
import com.LibraryManager.Library.Service.BookService;
import com.LibraryManager.Library.payload.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")

public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto){
        return bookService.createBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable long id,@RequestBody BookDto book){
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
