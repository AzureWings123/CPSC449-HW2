package com.example.bookstore_mongodb.service;

import com.example.bookstore_mongodb.entity.Book;
import com.example.bookstore_mongodb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // create a book
    public Book createBook(Book book) {
        book.setCreatedAt(new Date());
        return bookRepository.save(book);
    }

    // get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // delete a book by id
    public ResponseEntity deleteBook(String id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.status(404).body("Book not found.");
        }
        bookRepository.deleteById(id);
        return ResponseEntity.status(200).body("Deleted.");
    }
}
