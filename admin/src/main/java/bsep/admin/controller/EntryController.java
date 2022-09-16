package bsep.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntryController {

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	String index(Model model) {
		model.addAttribute("something", "somethingElse");
		return "index";
	}
	
	@GetMapping("/adminsPage")
	String adminsPage(Model model) {
		return "adminsPage";
	}
	
	@GetMapping("/userPage")
	String userPage(Model model) {
		return "userPage";
	}
}
