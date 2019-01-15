package pl.kwi.springboot.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
			
	@RequestMapping(value = "/welcome")
	public void initWelcome(HttpServletResponse response) throws IOException {
		response.sendRedirect("welcome/Unknown");;
	}
	
	@RequestMapping(value = "/welcome/{name}")
	public String sayWelcome(@PathVariable("name") String name) {
		name = (name == null) ? "Unknown" : name;
		return "Welcome " + name;
	}

}