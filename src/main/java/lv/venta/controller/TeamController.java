package lv.venta.controller;

import lv.venta.model.Team;
import lv.venta.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    ITeamService teamService;

    @GetMapping("/all")
    public String team(Model model) {
        model.addAttribute("mylist", teamService.getAllTeams());
        model.addAttribute("title", "All Teams");
        return "all-team-page";
    }
}
