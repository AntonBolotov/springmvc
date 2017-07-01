package com.springapp.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SessionController {

//	@Autowired(required = true)
//	private AuthenticationManager authenticationManager;



	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String checkSession() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (auth.getPrincipal() instanceof String)? "false" : "true";

	}

//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	@ResponseBody
//	public String loginPage (@RequestParam("login") String userName,
//	@RequestParam("password") String password, HttpServletRequest request) {
//
//		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
//				userName, password);
//
//		try {
//
//			Authentication authentication = authenticationManager
//					.authenticate(authenticationToken);
//
//
//			SecurityContext securityContext = SecurityContextHolder
//					.getContext();
//
//			securityContext.setAuthentication(authentication);
//
//			HttpSession session = request.getSession(true);
//			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//			return "sucess";
//		} catch (AuthenticationException ex) {
//			return "fail " + ex.getMessage();
//		}
////
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		if (auth != null){
////			new SecurityContextLogoutHandler().logout(request, response, auth);
////		}
////		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//	}
}