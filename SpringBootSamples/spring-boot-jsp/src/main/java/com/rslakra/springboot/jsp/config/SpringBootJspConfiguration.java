package com.rslakra.springboot.jsp.config;

import com.rslakra.springboot.jsp.repository.BookRepository;
import com.rslakra.springboot.jsp.repository.impl.InMemoryBookRepository;
import com.rslakra.springboot.jsp.repository.model.BookDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringBootJspConfiguration {

    @Bean
    public BookRepository provideBookRepository() {
        return new InMemoryBookRepository(initialBookData());
    }

    private static Map<String, BookDO> initialBookData() {
        Map<String, BookDO> initData = new HashMap<>();
        initData.put("ISBN-1", new BookDO("ISBN-1", "Book 1", "Book 1 Author"));
        initData.put("ISBN-2", new BookDO("ISBN-2", "Book 2", "Book 2 Author"));
        initData.put("ISBN-3", new BookDO("ISBN-3", "Book 3", "Book 3 Author"));
        return initData;
    }
}