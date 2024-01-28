package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@PostMapping("/register")
	//public String addUsers(@RequestParam("username") String username,@RequestParam("username") String email,@RequestParam("username") String password,@RequestParam("username") String gender,@RequestParam("username") String role,@RequestParam("username") String address) {
	public String addUsers(@ModelAttribute Users user) {
	//System.out.println(user.getUsername()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getGender()+" "+user.getRole()+" "+user.getAddress());
	boolean userStatus=service.emailExists(user.getEmail());
	if(userStatus==false) {
		service.addUser(user);
		System.out.println("User added");
	}
	else {
		System.out.println("User already exists");
	}
	return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session) {
		//TODO: process POST request
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				return "customerhome";
			}
		}
		else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
