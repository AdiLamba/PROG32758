package com.example.demo.controllers;

import com.example.demo.beans.Book;
import com.example.demo.database.DatabaseAccess;
import net.sf.jsqlparser.schema.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BookController {

    private DatabaseAccess database;

    public BookController(DatabaseAccess database) {
        this.database = database;
    }

    @GetMapping("/")
    public String goHome (Model model){

        model.addAttribute("book", new Book());
        model.addAttribute("books", database.getBooks());

        return "index";
    }

    @GetMapping ("/addBook")
    public String redirectToAddBook() {
        System.out.println("Redirecting to add-book test");
        return "admin/add-book";
    }

    @PostMapping("/addNewBook")
    public String addNewBook(@ModelAttribute Book book) {

        database.addBook(book);

        return "redirect:/";
    }
}
