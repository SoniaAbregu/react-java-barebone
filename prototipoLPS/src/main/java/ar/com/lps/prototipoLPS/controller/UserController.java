package ar.com.lps.prototipoLPS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.lps.prototipoLPS.model.User;

@RestController
public class UserController {
	@RequestMapping("/user")
	public User greeting(@RequestParam(value = "name", defaultValue = "ADMIN") String name,
			@RequestParam(value = "password", defaultValue = "PASS") String password) {
		return new User(name, password);
	}
}
