package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.*;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.IDriverStandingsService;
import lv.venta.service.ITeamService;
import lv.venta.service.ITeamStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/standings")
public class StandingsController {


    @Autowired
    private IDriverCRUDService crudService;
    
    @Autowired
    private ITeamService teamService;

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
        try {
            List<DriverStandings> standings = driverStandingsService.getAllDriverStandingsWithRaceResults();
            List<Driver> drivers = crudService.getAllDrivers();
            List<Race> races = driverStandingsService.getAllRaces();

            for (DriverStandings standing : standings) {
                int totalPoints = driverStandingsService.calculateDriverTotalPointsById(standing.getDriver().getIdD());
                standing.getDriver().setTotalPoints(totalPoints);
                standing.getDriver().setTotalWins(driverStandingsService.calculateDriverTotalWinsById(standing.getDriver().getIdD()));
            }
            driverStandingsService.updateDriverPositions();
            drivers.sort(Comparator.comparingInt(Driver::getDriverTotalPosition));

            model.addAttribute("standings", standings);
            model.addAttribute("races", races);
            model.addAttribute("drivers", drivers);

            return "driver-standings-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/team/all")
    public String getTeamStandingsAll(Model model) {
        try {
            List<Team> teams = teamService.getAllTeams();
            List<Race> races = driverStandingsService.getAllRaces();

            for (TeamStandings standing : teamStandingsService.getAllTeamStandings()) {
                int totalPoints = teamStandingsService.calculateTeamTotalPointsById(standing.getTeam().getIdT());
                standing.calculateTeamPoints();
                standing.getTeam().setTotalTeamPoints(totalPoints);
            }
            
            teamStandingsService.updateTeamPositions();
            teams.sort(Comparator.comparingInt(Team::getTeamTotalPosition));
            model.addAttribute("teams", teams);
            model.addAttribute("races", races);;
            return "team-standings-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/driver/update/race/{id}")
    public String getDriverStandingsUpdate(@PathVariable("id") int id, Model model){
        try {
            Race race = driverStandingsService.getRaceById(id);
            if (race == null) 
                throw new Exception("Race not found with id: " + id);

            model.addAttribute("race", race);
            model.addAttribute("raceResults", race.getRaceResults());
            model.addAttribute("id", id);
            return "update-driver-standings-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }

    @PostMapping("/driver/update/race/{id}")
    public String postDriverStandingsFromRaceUpdate(@PathVariable("id") int id, @Valid @ModelAttribute("race") Race race, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "update-driver-standings-page";
        } else {
            try {
                for (RaceResult raceResult : race.getRaceResults())
                    driverStandingsService.updateDriverStanding(raceResult.getDriverStandings().getIdDS(), raceResult.getDriverStandings());
                
                teamStandingsService.updateTeamPositions();
                return "redirect:/standings/driver/all";
            } catch (Exception e) {
                return "redirect:/error";
            }
        }
    }
}