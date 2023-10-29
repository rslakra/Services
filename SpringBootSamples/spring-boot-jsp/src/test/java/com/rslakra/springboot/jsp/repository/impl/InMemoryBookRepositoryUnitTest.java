package com.rslakra.springboot.jsp.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.rslakra.springboot.jsp.repository.model.BookDO;
import org.junit.jupiter.api.Test;

import com.rslakra.springboot.jsp.repository.BookRepository;

public class InMemoryBookRepositoryUnitTest {

    @Test
    public void givenEmtpyData_whenFindAll_thenReturnEmptyCollection() {
        BookRepository bookRepository = new InMemoryBookRepository(Collections.emptyMap());
        Collection<BookDO> storedBooks = bookRepository.findAll();

        assertEquals(0, storedBooks.size());
    }

    @Test
    public void givenInitialData_whenFindAll_thenReturnInitialData() {
        BookRepository bookRepository = new InMemoryBookRepository(initialBookData());
        Collection<BookDO> storedBooks = bookRepository.findAll();

        assertEquals(3, storedBooks.size());
    }

    @Test
    public void givenInitialData_whenFindUnavailableIsbn_thenReturnEmpty() {
        BookRepository bookRepository = new InMemoryBookRepository(initialBookData());
        Optional<BookDO> storedBookOpt = bookRepository.findById("isbn4");

        assertFalse(storedBookOpt.isPresent());
    }

    @Test
    public void givenInitialData_whenFindAvailableIsbn_thenReturnItem() {
        BookRepository bookRepository = new InMemoryBookRepository(initialBookData());
        Optional<BookDO> storedBookOpt = bookRepository.findById("isbn1");

        assertTrue(storedBookOpt.isPresent());
    }

    @Test
    public void givenAddedIsbn_whenFindAvailableIsbn_thenReturnItem() {
        BookRepository bookRepository = new InMemoryBookRepository(Collections.emptyMap());
        bookRepository.add(new BookDO("isbn4", "name4", "author4"));
        Optional<BookDO> storedBookOpt = bookRepository.findById("isbn4");

        assertTrue(storedBookOpt.isPresent());
    }

    private static Map<String, BookDO> initialBookData() {
        Map<String, BookDO> initData = new HashMap<>();
        initData.put("isbn1", new BookDO("isbn1", "name1", "author1"));
        initData.put("isbn2", new BookDO("isbn2", "name2", "author2"));
        initData.put("isbn3", new BookDO("isbn3", "name3", "author3"));
        return initData;
    }
}