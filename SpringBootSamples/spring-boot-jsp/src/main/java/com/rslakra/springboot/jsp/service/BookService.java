package com.rslakra.springboot.jsp.service;

import com.rslakra.springboot.jsp.dto.Book;
import com.rslakra.springboot.jsp.repository.model.BookDO;

import java.util.Collection;

public interface BookService {

    /**
     * @return
     */
    Collection<Book> getBooks();

    /**
     * @param book
     * @return
     */
    Book addBook(Book book);

    /**
     * @param bookDO
     * @return
     */
    static Book toBook(BookDO bookDO) {
        return new Book(bookDO.getIsbn(), bookDO.getName(), bookDO.getAuthor());
    }

    /**
     * @param book
     * @return
     */
    static BookDO fromBook(Book book) {
        return new BookDO(book.getIsbn(), book.getName(), book.getAuthor());
    }
}