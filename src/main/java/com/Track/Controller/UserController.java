package com.Track.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Track.Models.Response;
import com.Track.Models.Role;
import com.Track.Models.User;
import com.Track.Service.UserService;


@RestController
public class UserController {
	
	@Autowired private UserService userService;
	
	
	@PostMapping("/signUp")
	public ResponseEntity<Response> signUp(@RequestBody User user){
		return this.userService.signUp(user);
	}
	
	@PostMapping("/role/signUp")
	public ResponseEntity<Response> roleSignUp(@RequestBody Role role){
		return this.userService.roleSignUp(role);
	}
	
	@PostMapping("/admin/signIn")
	public ResponseEntity<Response> adminSignIn(@RequestBody User user){
		return this.userService.adminSignIn(user);
	}
	
	@PostMapping("/employee/signIn")
	public ResponseEntity<Response> employeeSignIn(@RequestBody User user){
		return this.userService.employeeSignIn(user);
	}
	
	@GetMapping("/signOut/{id}")
	public ResponseEntity<Response> employeeSignOut(@PathVariable int id){
		return userService.employeeSignOut(id);
	}
	
	@PutMapping("/Employee/edit")
	public ResponseEntity<Response> EmployeeEditing(@PathVariable int id, @RequestBody User user){
		
		return userService.EmployeeEditing(id,user);
	}
	
	
	

}
