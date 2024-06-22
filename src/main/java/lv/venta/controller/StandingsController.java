package lv.venta.controller;

import lv.venta.model.Driver;
import lv.venta.model.DriverStandings;
import lv.venta.model.Race;
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
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        	List<DriverStandings> standings = driverStandingsService.getAllDriverStandings();
        	List<Driver> drivers = crudService.getAllDrivers(); 
        	List<Race> races = driverStandingsService.getAllRaces();
        	
        	for(DriverStandings standing : standings) {
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
    	int numberOfRaces = 2;
    	model.addAttribute("numberOfRaces", numberOfRaces);
        try {
        	teamStandingsService.calculateAndUpdateAllTeamPoints();
        	model.addAttribute("teams", teamStandingsService.getAllTeamStandings());
            return "team-standings-page";
        	
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/driver/update/{id}")
    public String getDriverStandingsUpdate(@PathVariable("id") int id, Model model){
        try {
            model.addAttribute("driverStandings", driverStandingsService.getDriverStandingsById(id));
            return "update-driver-standings-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
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
                return "error-page";
            }
        }
    }
}
