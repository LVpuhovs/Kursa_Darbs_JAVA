package lv.venta.controller;

import lv.venta.model.Driver;
import lv.venta.model.DriverStandings;
import lv.venta.model.Race;
import lv.venta.model.RaceResult;
import lv.venta.model.Team;
import lv.venta.model.TeamStandings;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.IDriverStandingsService;
import lv.venta.service.ITeamService;
import lv.venta.service.ITeamStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Comparator;
import java.util.List;

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
        	List<Driver> drivers = crudService.getAllDrivers(); 
        	List<Race> races = driverStandingsService.getAllRaces();
        	
        	for(DriverStandings standing : driverStandingsService.getAllDriverStandings()) {
        		int totalPoints = driverStandingsService.calculateDriverTotalPointsById(standing.getDriver().getIdD());
                standing.getDriver().setTotalPoints(totalPoints);
                standing.getDriver().setTotalWins(driverStandingsService.calculateDriverTotalWinsById(standing.getDriver().getIdD()));
        	}
        	driverStandingsService.updateDriverPositions();
        	drivers.sort(Comparator.comparingInt(Driver::getDriverTotalPosition));

            model.addAttribute("drivers", drivers);
            model.addAttribute("races", races);

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
        	
        	for(TeamStandings standing : teamStandingsService.getAllTeamStandings()) {
        		int totalPoints = teamStandingsService.calculateTeamTotalPointsById(standing.getTeam().getIdT());
        		standing.calculateTeamPoints();
        		standing.getTeam().setTotalTeamPoints(totalPoints);
        	}
        	teamStandingsService.updateTeamPositions();
        	teams.sort(Comparator.comparingInt(Team::getTeamTotalPosition));
        	model.addAttribute("teams", teams);
        	model.addAttribute("races", races);
            return "team-standings-page";
        	
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }
    
	@GetMapping("/driver/update/race/{id}")
    public String getDriverStandingsFromRaceUpdate(@PathVariable("id") int id, Model model){
        try {
        	Race race = driverStandingsService.getRaceById(id);
        	  if (race == null) {
                  throw new Exception("Race not found with id: " + id);
              }
        	
            model.addAttribute("race", race);
            model.addAttribute("raceResults", race.getRaceResults());
            model.addAttribute("id", id);  //raceId
            return "update-driver-standings-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }

    @PostMapping("/driver/update/race/{id}")
    public String postDriverStandingsFromRaceUpdate(@PathVariable("id") int id, @Valid @ModelAttribute("race") Race race, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("id", id);
            return "update-driver-standings-page";
        } else {
            try {
            	 for (int i = 0; i < race.getRaceResults().size(); i++) {
                     driverStandingsService.updateDriverStanding(i, race.getRaceResults().get(id).getDriverStandings());
                 }
                
                return "redirect:/standings/driver/all";
            } catch (Exception e) {
            	model.addAttribute("msg", e.getMessage());
                return "error-page";
            }
        }
    }
    
    @GetMapping("/error")  //localhost:8080/error
	public String getError() {
		return "error-page";
	}
}
