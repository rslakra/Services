package com.rslakra.springboot.jsp.repository;

import java.util.Collection;
import java.util.Optional;

import com.rslakra.springboot.jsp.repository.model.BookDO;

public interface BookRepository {
    Collection<BookDO> findAll();

    Optional<BookDO> findById(String isbn);

    BookDO add(BookDO book);
}
