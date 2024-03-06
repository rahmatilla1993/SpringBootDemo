package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.Book;
import com.example.springbootdemo.dao.BookDao;
import com.example.springbootdemo.util.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "book/book_list";
    }

    @GetMapping("/add")
    public String addBookView() {
        return "book/book_add";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBookView(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book/book_edit");
        modelAndView.addObject("book", bookDao.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteBookView(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "book/book_delete";
    }

    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute SearchDto dto) {
        List<Book> books = bookDao.searchBook(dto);
        System.out.println(books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book/book_list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookDao.save(book);
        return "redirect:/book/all";
    }

    @PutMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookDao.edit(book);
        return "redirect:/book/all";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/book/all";
    }
}
