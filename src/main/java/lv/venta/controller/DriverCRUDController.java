package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/driver")
public class DriverCRUDController {
    @Autowired
    IDriverCRUDService crudService;

    @GetMapping("/create")
    public String getDriverCreate(Model model) {
        model.addAttribute("driver", new Driver());
        return "create-driver-page";
    }

    @PostMapping("/create")
    public String postDriverCreate(@Valid Driver driver, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "create-driver-page";
        } else {
            try {
                crudService.createDriver(driver);
                return "redirect:/driver/all";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/all")
    public String getDriverAll(Model model) {
        try {
            model.addAttribute("mylist", crudService.getAllDrivers());
            return "show-driver-all-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}") //localhost:8080/driver/update?id=1
    public String getDriverUpateById(@PathVariable("id") int id, Model model ) {
        Driver driver = null;
        try {
            driver = crudService.getDriverById(id);
            model.addAttribute("id", id);
            model.addAttribute("name", driver.getName());
            model.addAttribute("driver", driver);
            return "update-driver-page";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @PostMapping("/update/{id}")
    public String postdriverUpdate(@PathVariable("id") int id, @Valid Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-driver-page";
        }else {
            try {
                crudService.updateDriverById(id, driver);
                return "redirect:/driver/all";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/delete/{id}") //localhost:8080/product/delete/1
    public String getProductDeleteById(@PathVariable("id") int id, Model model) {

        try {
            crudService.deleteDriverById(id);
            model.addAttribute("mylist", crudService.getAllDrivers());
            return "show-driver-all-page";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
