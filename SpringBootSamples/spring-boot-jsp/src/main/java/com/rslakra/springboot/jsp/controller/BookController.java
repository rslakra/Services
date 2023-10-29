package com.rslakra.springboot.jsp.controller;

import com.rslakra.springboot.jsp.dto.Book;
import com.rslakra.springboot.jsp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    /**
     * @param bookService
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/viewBooks")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "view-books";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/addBook")
    public String addBookView(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    /**
     * @param book
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/addBook")
    public RedirectView addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/book/addBook", true);
        Book savedBook = bookService.addBook(book);
        redirectAttributes.addFlashAttribute("savedBook", savedBook);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        return redirectView;
    }
}