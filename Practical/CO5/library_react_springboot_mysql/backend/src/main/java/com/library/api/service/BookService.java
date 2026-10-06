package com.library.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.api.model.Book;
import com.library.api.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository books;

    public BookService(BookRepository books) {
        this.books = books;
    }

    public Book addBook(Book b, Long memberId) {
        return books.save(b);
    }

    public List<Book> getAllBooks() {
        return books.findAll();
    }

    public Book getBook(Long id) {
        return books.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}

