package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/driver")
public class DriverCRUDController {
    @Autowired
    IDriverCRUDService crudService;

    @GetMapping("/create")		//localhost:8080/driver/create
    public String getDriverCreate(Model model) {
        model.addAttribute("driver", new Driver());
        return "create-driver-page";
    }

    @PostMapping("/create")
    public String postDriverCreate(@Valid Driver driver, BindingResult result) {
        if (result.hasErrors()) {
            return "create-driver-page";
        } else {
            try {
                crudService.createDriver(driver);
                return "redirect:/driver/all";
            } catch (Exception e) {
                return "redirect:/error";
            }
        }
    }

    @GetMapping("/all")			//localhost:8080/driver/all
    public String getDriverAll(Model model) {
        try {
            model.addAttribute("mylist", crudService.getAllDrivers());
            model.addAttribute("title", "All Drivers");
            return "show-driver-all-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}") 		//localhost:8080/driver/update?id=1
    public String getDriverUpateById(@PathVariable("id") int id, Model model ) {
        try {
        	Driver driver = crudService.getDriverById(id);
            model.addAttribute("id", id);
            model.addAttribute("name", driver.getName());
            model.addAttribute("driver", driver);
            return "update-driver-page";
        } catch (Exception e) {
        	model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
    }
    @PostMapping("/update/{id}")
    public String postdriverUpdate(@PathVariable("id") int id, @Valid Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("id", id);
            return "update-driver-page";
        }else {
            try {
                crudService.updateDriverById(id, driver);
                return "redirect:/driver/all";
            } catch (Exception e) {
            	return "redirect:/error";
    		}
        }
    }

    @GetMapping("/delete/{id}") 		//localhost:8080/driver/delete/1
    public String getProductDeleteById(@PathVariable("id") int id, Model model) {

        try {
            crudService.deleteDriverById(id);
            model.addAttribute("mylist", crudService.getAllDrivers());
            return "redirect:/driver/all";
        } catch (Exception e) {
			return "redirect:/error";
		}
    }

}
