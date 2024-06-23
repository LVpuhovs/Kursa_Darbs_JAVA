package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Driver;
import lv.venta.model.Team;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    ITeamService teamService;

    @Autowired
    private IDriverCRUDService driverService;

    @GetMapping("/all")
    public String getTeam(Model model) {
        model.addAttribute("mylist", teamService.getAllTeams());
        model.addAttribute("title", "All Teams");
        return "all-team-page";
    }

    @GetMapping("/add")
    public String getAddTeam(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("availableDrivers", driverService.getAvailableDrivers());
        return "add-team-page";
    }

    @PostMapping("/add")
    public String postAddTeam(@Valid Team team, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("availableDrivers", driverService.getAvailableDrivers());
            return "add-team-page";
        }else {
            try {
                teamService.addTeam(team);
                return "redirect:/team/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                return "error-page";
            }
        }
    }

    @GetMapping("/update/{id}") //localhost:8080/team/update/1
    public String getTeamUpdateById(@PathVariable("id") int id, Model model ) {
        try {
            model.addAttribute("id", id);
            model.addAttribute("team", teamService.getTeamById(id));
            model.addAttribute("availableDrivers", driverService.getAvailableDrivers());
            return "update-team-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }
    @PostMapping("/update/{id}")
    public String postTeamUpdate(@PathVariable("id") int id, @Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "update-team-page";
        }else {
            try {
                teamService.updateTeam(id, team);
                return "redirect:/team/all";
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                return "error-page";
            }
        }
    }

    @GetMapping("/delete/{id}") //localhost:8080/driver/delete/1
    public String getProductDeleteById(@PathVariable("id") int id, Model model) {

        try {
            teamService.deleteTeam(id);
            model.addAttribute("mylist", teamService.getAllTeams());
            return "redirect:/team/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }
}
