package com.example.demo.controllers;

import com.example.demo.beans.Mission;
import com.example.demo.databases.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {


    private DatabaseAccess database;
    public HomeController(DatabaseAccess database) {
        this.database = database;
    }


    @GetMapping("/")
    public String goHome(Model model) {
        model.addAttribute("mission", new Mission());
        model.addAttribute("database", database);
        return "index";
    }

    @PostMapping ("/createNew")
    public String createNew(Model model, @ModelAttribute("mission")Mission mission) {
        List<String> agentNames = database.getDistinctAgentNames();
        model.addAttribute("agentNames", agentNames);
        database.insertMission(mission);
        return "redirect:/";
    }

    @GetMapping  ("/toCreate")
        public String toCreate(Model model) {
        Mission mission = new Mission();
        List<String> agentNames = database.getDistinctAgentNames();
        model.addAttribute("mission", mission);
        model.addAttribute("agentNames", agentNames);
        return "create_mission";
        }

    @GetMapping("/viewMissions")
    public String viewMissions(Model model, @RequestParam("agent") String selectedAgent) {
        Mission mission = new Mission();

        // Get the list of distinct agent names from the database
        List<String> agentNames = database.getDistinctAgentNames();

        model.addAttribute("mission", mission);
        model.addAttribute("agentNames", agentNames); // Add the agent names to the model

        return "view_missions";
    }

    @GetMapping("/editMission/{id}")
    public String editMission(@PathVariable("id") long id, Model model) {
        Mission mission = database.getMissionById(id);
        model.addAttribute("mission", mission);
        return "edit_mission";
    }

    @GetMapping("/deleteMission/{id}")
    public String deleteMission(@PathVariable long id) {
        int returnValue = database.deleteMission(id);
        return "redirect:/view_missions";
    }

    @PostMapping("/updateMission/{id}")
    public String updateMission(@PathVariable("id") long id, @RequestParam Map<String, String> missionDetails, Model model) {
        // Retrieve the existing mission from the database based on the 'id'
        Mission existingMission = database.getMissionById(id);

        if (existingMission != null) {
            existingMission.setAgent(missionDetails.get("agent"));
            existingMission.setTitle(missionDetails.get("title"));
            existingMission.setGadget1(missionDetails.get("gadget1"));
            existingMission.setGadget2(missionDetails.get("gadget2"));


            database.updateMission(existingMission);

            List<String> agentNames = database.getDistinctAgentNames();
            model.addAttribute("agentNames", agentNames);
            model.addAttribute("mission", existingMission);

            return "redirect:/viewMissions";
        } else {
            return "redirect:/";
        }
    }

}
