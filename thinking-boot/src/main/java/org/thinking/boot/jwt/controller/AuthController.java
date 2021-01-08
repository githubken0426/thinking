package org.thinking.boot.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.boot.jwt.annotation.JwtTokenRequired;
import org.thinking.boot.jwt.enumration.IssuerEunm;

@RestController()
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	private JwtComponent jwtComponent;

	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@JwtTokenRequired(isRequired = false)
	public String login() {
		Map<String, String> map = new HashMap<>();
		try {
			map.put("userName", "userName");
			map.put("password", "password");
			String token = jwtComponent.generatorToken(map, IssuerEunm.SINA);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@PostMapping(value = "/doTask")
	@JwtTokenRequired(isRequired = true)
	public String doTask() {

		return "doTask";
	}

}
