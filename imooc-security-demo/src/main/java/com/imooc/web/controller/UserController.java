package com.imooc.web.controller;

import com.imooc.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
	
	@GetMapping("/user")
	public List<User> query(@RequestParam String username){
		System.out.println(username);
		ArrayList<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

}
