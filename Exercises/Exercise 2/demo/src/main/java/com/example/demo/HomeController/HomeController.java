package com.example.demo.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;

import java.util.ArrayList;

@Controller
public class HomeController {

  List<String> currentMissions = new ArrayList<>();

  @GetMapping ("/addMission")
    public String goToAddMission() {
      return "addMission";
  }

  @GetMapping("/index")
  public String goHome(){
    return "index";
  }

@PostMapping("/addNew")
  public String newMission(@RequestParam String missionName,
                           @RequestParam String agentName, Model model){
    currentMissions.add("Mission Name: "+missionName+" Mission Agent: "+agentName);

    return "success";
}

@GetMapping("/missionList")
  public String goToMissionList(Model model){

    model.addAttribute("currentMissions", currentMissions);
    return "missionList";
}

}
