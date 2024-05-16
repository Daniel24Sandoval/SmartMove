package com.web.optiviaje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("")
	public String Landig() {
		return  "user/Landig";
	}
	
	 @GetMapping("/login")
 public String login() {
		return  "user/login";
	 }
	
	 @GetMapping("/Registro")
	 public String Registro() {
	 return  "user/Registro";
	 }
	 
	 
	@GetMapping("Menu")
	 public String Menu() {
			 return "user/Menu";
		 }
	@GetMapping("Menu2")
	 public String Menu2() {
			 return "user/Menu2";
		 }
	
}
