package com.rslakra.springboot.jsp.exception;

import com.rslakra.springboot.jsp.dto.Book;
import lombok.Getter;

@Getter
public class DuplicateBookException extends RuntimeException {
    private final Book book;

    public DuplicateBookException(Book book) {
        this.book = book;
    }
}