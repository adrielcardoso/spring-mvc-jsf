package org.springframework.samples.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.Callable;

@Controller
public class HomeController
{
	@GetMapping()
	public String handle(Model model) {
		model.addAttribute("message", "Hello World!");
		return "index";
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		return "Handled exception: " + ex.getMessage();
	}

}
