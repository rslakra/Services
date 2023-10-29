package com.rslakra.springboot.jsp.repository.impl;

import java.util.*;

import com.rslakra.springboot.jsp.repository.BookRepository;
import com.rslakra.springboot.jsp.repository.model.BookDO;

public class InMemoryBookRepository implements BookRepository {

    private final Map<String, BookDO> storedBooks;

    public InMemoryBookRepository(Map<String, BookDO> storedBooks) {
        this.storedBooks = new HashMap<>();
        this.storedBooks.putAll(storedBooks);
    }

    @Override
    public Collection<BookDO> findAll() {
        if (storedBooks.isEmpty()) {
            return Collections.emptyList();
        }

        return storedBooks.values();
    }

    @Override
    public Optional<BookDO> findById(String isbn) {
        return Optional.ofNullable(storedBooks.get(isbn));
    }

    @Override
    public BookDO add(BookDO book) {
        storedBooks.put(book.getIsbn(), book);
        return book;
    }
}
