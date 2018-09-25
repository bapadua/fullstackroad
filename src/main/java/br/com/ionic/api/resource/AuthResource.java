package br.com.ionic.api.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ionic.api.security.JwtUtil;
import br.com.ionic.api.security.UserSS;
import br.com.ionic.api.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/refresh")
	public ResponseEntity<Void> refreshToken(HttpServletResponse res){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		res.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
