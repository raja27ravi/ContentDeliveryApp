package com.concordia.ece.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("home")
	public String homePage() {
		
		System.out.println("this is for testing");
		return "hello.jsp";
	}
	
}
