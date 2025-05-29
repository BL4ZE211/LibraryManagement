package com.LibraryManager.Library.Service;

import com.LibraryManager.Library.Entity.Author;
import com.LibraryManager.Library.Entity.Book;
import com.LibraryManager.Library.Exception.ResourceNotFoundException;
import com.LibraryManager.Library.Repository.AuthorRepository;
import com.LibraryManager.Library.Repository.BookRepository;
import com.LibraryManager.Library.payload.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookDto mapToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setAuthorId(book.getAuthor().getId());
        bookDto.setId(book.getId());
        return bookDto;
    }


    public BookDto createBook(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(
                ()->new ResourceNotFoundException("Author not found with id "+bookDto.getAuthorId()));

        Book book=new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        book.setPrice(bookDto.getPrice());
         bookRepository.save(book);

         return  mapToDto(book);
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("No Book With Id : "+id));
        return mapToDto(book);
    }

    public BookDto updateBook(long id, BookDto book) {
        Book existingBook = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No book with id : "+id));
        existingBook.setPrice(book.getPrice());
        existingBook.setTitle(book.getTitle());

        bookRepository.save(existingBook);
        return mapToDto(existingBook);
    }

    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No book with id : "+ id));
         bookRepository.deleteById(id);
    }
}
