package com.rslakra.springboot.jsp.service.impl;

import com.rslakra.springboot.jsp.dto.Book;
import com.rslakra.springboot.jsp.exception.DuplicateBookException;
import com.rslakra.springboot.jsp.repository.BookRepository;
import com.rslakra.springboot.jsp.repository.model.BookDO;
import com.rslakra.springboot.jsp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Collection<Book> getBooks() {
        return bookRepository.findAll()
            .stream()
            .map(BookService::toBook)
            .collect(Collectors.toList());
    }

    @Override
    public Book addBook(Book book) {
        final Optional<BookDO> existingBook = bookRepository.findById(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new DuplicateBookException(book);
        }

        final BookDO savedBook = bookRepository.add(BookService.fromBook(book));
        return BookService.toBook(savedBook);
    }

}
