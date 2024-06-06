package lv.venta.controller;

import lv.venta.model.DriverStandings;
import lv.venta.model.Race;
import lv.venta.model.RaceResult;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.IDriverStandingsService;
import lv.venta.service.ITeamService;
import lv.venta.service.ITeamStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/standings")
public class StandingsController {


    @Autowired
    private IDriverCRUDService crudService;

    @Autowired
    private ITeamStandingsService teamStandingsService;

    @Autowired
    private IDriverStandingsService driverStandingsService;

    @GetMapping("/all")
    public String all(Model model) {
        return "standings-all-page";
    }

    @GetMapping("/driver/all")
    public String getDriverStandingsAll(Model model) {
        int numberOfRaces = 10;
        model.addAttribute("numberOfRaces", numberOfRaces);
        //iziet cauri sarakstam, un filtree tikai tos braucejus kuriem ir komanda un salasa listaa
        model.addAttribute("drivers", crudService.getAllDrivers().stream().filter(driver -> driver.getTeam() != null).collect(Collectors.toList()));
        model.addAttribute("mylist", driverStandingsService.getAllDriverStandings());
        return "driver-standings-page";
    }

    @GetMapping("/team/all")
    public String getTeamStandingsAll(Model model) {
        model.addAttribute("mylist", teamStandingsService.getAllTeamStandings());
        return "team-standings-page";
    }

    @GetMapping("/driver/update/{id}")
    public String getDriverStandingsUpdate(@PathVariable("id") int id, Model model){
        try {
            model.addAttribute("driverStandings", driverStandingsService.getDriverStandingsById(id));
            return "update-driver-standings-page";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/driver/update/{id}")
    public String postDriverStandingsUpdate(@PathVariable("id") int id, @ModelAttribute("driverStandings") DriverStandings updatedDriverStandings, BindingResult result) {
        if (result.hasErrors()) {
            return "update-driver-standings-page";
        } else {
            try {
                driverStandingsService.updateDriverStanding(id, updatedDriverStandings);
                return "redirect:/standings/driver/all";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
