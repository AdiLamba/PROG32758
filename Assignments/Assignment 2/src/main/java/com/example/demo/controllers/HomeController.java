package com.example.demo.controllers;

import com.example.demo.beans.Book;
import com.example.demo.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;

@Controller
public class HomeController {

    private DatabaseAccess database;

    @Autowired
    public HomeController(DatabaseAccess database) {
        this.database = database;
    }

    @GetMapping ("/")
    public String goHome(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("database", database);
       // model.addAttribute("bookList", bookList);
        return "index";
    }

    @PostMapping("/addBook")
    public String addBook(Model model, @ModelAttribute("book") Book book) {
        book.setCreationDate(new Date());
        database.insertBook(book);
       // bookList.add(book);
        return "redirect:/";
    }

    @GetMapping ("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        int returnValue = database.deleteBook(id);
        return "redirect:/";
    }
}
