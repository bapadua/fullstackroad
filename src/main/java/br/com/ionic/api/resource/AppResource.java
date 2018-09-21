package br.com.ionic.api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppResource {

	public String getHome() {
		return "https://solucaologica.github.io/";
	}
}
