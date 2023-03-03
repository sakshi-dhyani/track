package com.Track.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Track.Models.Response;
import com.Track.Models.User;
import com.Track.Repository.UserRepository;
import com.Track.Service.OtpService;
import com.Track.Service.UserService;



@RestController
public class OtpController {
	

	@Autowired
	private OtpService otpService;
	
	
	@Autowired private UserRepository userRepo;

	@GetMapping("/generateOtpForsignUp/{email}")
	private ResponseEntity<Response> generateOtpForsignUp(@PathVariable String email) {
		Response response = new Response();
		if (userRepo.findByEmail(email) != null) {
			response.setMessage("user alrady registered");
			return new ResponseEntity<Response>(response, HttpStatus.FOUND);
		}
		return otpService.generateOTP(email);
	}

	@GetMapping("/generateOtpForResetPassword/{email}")
	private ResponseEntity<Response> generateOtpForResetPassword(@PathVariable String email) {
		Response response = new Response();
		if (userRepo.findByEmail(email) == null) {
			response.setMessage("user not found");
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}
		return otpService.generateOTP(email);
	}

	@PostMapping("/verifyOtp")
	private ResponseEntity<Response> verifyOtp(@RequestBody User user) {
		Response response = new Response();
		if (otpService.verifyOTP(user)) {
			response.setMessage("valid otp");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		response.setMessage("invalid otp");
		return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);
	}

}
