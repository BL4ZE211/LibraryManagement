package com.LibraryManager.Library.Service;

import com.LibraryManager.Library.Entity.Author;
import com.LibraryManager.Library.Entity.Book;
import com.LibraryManager.Library.Exception.ResourceNotFoundException;
import com.LibraryManager.Library.Repository.AuthorRepository;
import com.LibraryManager.Library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;


    public Book createBook(Book book) {
        Author author = authorRepository.findById(book.getAuthor().getId()).orElseThrow(
                ()->new ResourceNotFoundException("Author not found with id "+book.getAuthor().getId()));
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("No Book With Id : "+id));
        return book;
    }

    public Book updateBook(long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No book with id : "+id));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setTitle(book.getTitle());

        return existingBook;
    }

    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No book with id : "+ id));
         bookRepository.deleteById(id);
    }
}
