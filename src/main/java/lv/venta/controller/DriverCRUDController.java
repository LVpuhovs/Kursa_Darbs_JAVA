package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
