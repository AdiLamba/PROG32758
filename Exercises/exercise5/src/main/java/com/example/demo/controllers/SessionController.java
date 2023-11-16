package com.example.demo.controllers;

import com.example.demo.beans.Message;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionController {

    @GetMapping("/")
    public String goHome(Model model, HttpSession session) {
        List<Message> messages = (List<Message>) session.getAttribute("messageList");
        model.addAttribute("messageList", messages);
        model.addAttribute("message", new Message());
        return "index";
    }

    @GetMapping("/startSession")
    public String startSession(Model model, HttpSession session,
                               @ModelAttribute("message") Message message) {

        List<Message> messages = (List<Message>) session.getAttribute("messageList");
        if (messages == null){
            messages = new ArrayList<>();
            session.setAttribute("messageList", messages);
        }
        messages.add(message);
        model.addAttribute("messageList", messages);
        return "redirect:/";
    }

    @GetMapping("/killSession")
    public String killSession(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
