package com.example.demo.controllers;

import com.example.demo.beans.SchoolMgmt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    private ArrayList<SchoolMgmt> schoolList = new ArrayList<>();

    @GetMapping("/")
    public String goHome() {
        return "index";
    }

    @GetMapping("/list")
    public String goToCurrentList(Model model) {
        model.addAttribute("schoolList", schoolList);
        return "currentList";
    }

    @GetMapping("/addNewSchool")
    public String addNewSchool(Model model) {
        model.addAttribute("school", new SchoolMgmt());
        return "addSchool";
    }

    @PostMapping("/addSchool")
    public String addSchool(Model model,
                            @ModelAttribute SchoolMgmt school) {
        schoolList.add(school);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete() {
        schoolList.clear();
        return "index";
    }

}
