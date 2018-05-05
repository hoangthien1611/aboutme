package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	@RequestMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@RequestMapping("/403")
	public String error403(){
		return "error/403";
	} 
	
	@RequestMapping("/500")
	public String error500(){
		return "error/500";
	} 
}
