package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	  @GetMapping("/home")		//localhost:8080/home
	    public String getHome(Model model) {
	        model.addAttribute("title", "Formula IS");
	        return "home-page";
	    }
	  @GetMapping("/error")  //localhost:8080/error
	    public String getError() {
	        return "error-page";
	    }
	
}
