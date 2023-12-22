package com.example.demo.controllers;

import com.example.demo.beans.Book;
import com.example.demo.beans.Review;
import com.example.demo.database.DatabaseAccess;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private DatabaseAccess database;

    public HomeController(DatabaseAccess database) {
        this.database = database;
    }

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/reviews/{id}")
    public String viewReview(@PathVariable Long id, Model model) {

        Book book = database.getBookById(id);

        if(book == null) {

            System.out.println("No result for this id: "+id);
            return "redirect:/";
        }
        List<Review> reviews = database.getReviewsByBookId(id);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);

        return "view-book";
    }


    @GetMapping("/login")
    public String goToLogin() {

        return "login";
    }


    @GetMapping("/addReview/{id}")
    public String redirectToAddReview(@PathVariable Long id, Model model) {

        model.addAttribute("bookId", id);
        return "redirect:/user/add-review/" + id;
    }

    @GetMapping("/user/add-review/{id}")
    public String showAddReviewForm(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "user/add-review";
    }

    @PostMapping("/addNewReview/{id}")
    public String addNewReview(@PathVariable Long id, @ModelAttribute Review review, Model model) {
        review.setBookId(id);
        database.addReview(review);
        return "redirect:/view-book/" + id;
    }

    @GetMapping("/view-book/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Book book = database.getBookById(id);

        if (book == null) {
            System.out.println("No result for this id: " + id);
            return "redirect:/";
        }

        List<Review> reviews = database.getReviewsByBookId(id);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);

        return "view-book";
    }


    @GetMapping("/permission-denied")
    public String goToDenied() {

        return "error/permission-denied";
    }


    @GetMapping("/register")
    public String goToRegister() {

        return "register";
    }

    @PostMapping("/registerNew")
    public String registerNewUser(@RequestParam String username,
                                  @RequestParam String password,
                                  RedirectAttributes redirectAttributes) {

        if (userDetailsManager.userExists(username)) {

            //redirectAttributes.addFlashAttribute("error", "Username is already taken, please enter something else");

            return "redirect:/register";
        }

        String encodedPassword = passwordEncoder.encode(password);

        UserDetails user = User.withUsername(username)
                .password(encodedPassword)
                .roles("USER")
                .build();

        userDetailsManager.createUser(user);

        //redirectAttributes.addFlashAttribute("success", "Registration Completed Successfully");

        return "redirect:/login";
    }

}
