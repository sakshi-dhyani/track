package com.Track.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Track.Authentication.JwtTokinUtil;
import com.Track.Authentication.JwtUserDetailService;
import com.Track.Models.Response;
import com.Track.Models.Role;
import com.Track.Models.User;
import com.Track.Repository.RoleRepository;
import com.Track.Repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired private OtpService otpService;
	
	@Autowired 
	private UserRepository userRepo;
	@Autowired 
	private RoleRepository roleRepo;
	
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Autowired private JwtTokinUtil jwtTokinUtil;
	
	@Autowired private AuthenticationManager authenticationManager;
	
	@Autowired private JwtUserDetailService jwtUserDetailService;

	public ResponseEntity<Response> signUp(User user) {
		

		if (!otpService.verifyOTP(user)) {
			Response response = new Response();
			response.setMessage("invalid otp");
			return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);
		}
		user.setRole(roleRepo.findByNameAndActive(user.getRole().getName(), (byte) 1));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setMobileNumber(user.getMobileNumber());
		user.setCreatedBy(user.getEmail());
		user.setOtp(user.getOtp());
		user.setActive((byte) 1);

		user = userRepo.save(user);
		
		Response response = new Response();
		response.setMessage("user registered");
		response.setData(user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}	

	public ResponseEntity<Response> roleSignUp(Role role) {
		Role newRole = new Role();
		newRole.setName(role.getName());
		Response response = new Response();
		roleRepo.save(newRole);

		response.setCode(202);
		response.setError(null);
		response.setMessage("signUp Succssefull!!");
		response.setData(newRole);



		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<Response> adminSignIn(User user) {
		Response response=new Response();
		Role role=roleRepo.findByName(user.getRole().getName());
		if(!role.getName().equals("ADMIN")) {
			response.setMessage("Only Admin can access this page!!");
			response.setCode(403);
			return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);
		}
		if(role == null) {
			response.setMessage("please enter the valid credentials");
			response.setCode(404);
			return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
		}
		
		
		User user2=userRepo.findByEmail(user.getEmail());
		if(user2==null) {
		response.setMessage("wrong email");
		return new ResponseEntity<Response>(response,HttpStatus.FORBIDDEN);
	}
	 
		try {
			if (user2.isAccountNonLocked()) {
				System.out.println("OKay");
			}
			authenticate1(user.getEmail().trim(), user.getPassword());
		//	user2.setFirstName(user.getFirstName());
		//	user2.setLastName(user.getLastName());
		//	user2.setMobileNumber(user.getMobileNumber());
			user2.setEmail(user.getEmail());
			user.setPassword(user.getPassword());
			userRepo.save(user2);
			user2.setRole(role);
			List<Object> objects = new ArrayList<Object>();

			String accessToken = jwtTokinUtil
					.generateToken(jwtUserDetailService.loadUserByUsername(user.getEmail().trim()));
			objects.add(accessToken);
			objects.add(user2);
			response.setCode(200);
			response.setData(objects);
			response.setToken(accessToken);
			response.setMessage("signIn Successful!");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			response.setMessage("Email or password wrong");
			return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);

		}
	} 
	private void authenticate1(String email, String password) throws Exception {

		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}
	
	
	//employers signin

	public ResponseEntity<Response> employeeSignIn(User user) {
		Response response=new Response();
		Role role=roleRepo.findByName(user.getRole().getName());
		if(role == null) {
			response.setMessage("please enter the valid credentials");
			response.setCode(404);
			return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
		}
		if(!role.getName().equals("EMPLOYEE")) {
			response.setMessage("Only employee can access this page!!");
			response.setCode(403);
			return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);
		}
		
		
		
		User user2=userRepo.findByEmail(user.getEmail());
		if(user2==null) {
		response.setMessage("wrong email");
		return new ResponseEntity<Response>(response,HttpStatus.FORBIDDEN);
	}
	 
		try {
			if (user2.isAccountNonLocked()) {
				System.out.println("OKay");
			}
			authenticate(user.getEmail().trim(), user.getPassword());
		//	user2.setFirstName(user.getFirstName());
		//	user2.setLastName(user.getLastName());
		//	user2.setMobileNumber(user.getMobileNumber());
			user2.setEmail(user.getEmail());
			user.setPassword(user.getPassword());
			user2.setIsLoggedIn((byte) (1));
			userRepo.save(user2);
			user2.setRole(role);
			List<Object> objects = new ArrayList<Object>();

			String accessToken = jwtTokinUtil
					.generateToken(jwtUserDetailService.loadUserByUsername(user.getEmail().trim()));
			objects.add(accessToken);
			objects.add(user2);
			response.setCode(200);
			response.setData(objects);
			response.setToken(accessToken);
			response.setMessage("signIn Successful!");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			response.setMessage("Email or password wrong");
			return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);

		}
	} 
	private void authenticate(String email, String password) throws Exception {

		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}

	public ResponseEntity<Response> employeeSignOut(int id) {
		User emp = userRepo.findById(id);
		Response response = new Response();
		if(emp == null) {
			response.setCode(404);
			response.setMessage("user not found");
			return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
		}
		emp.setIsLoggedIn((byte) 0);
		userRepo.save(emp);
		
		response.setCode(202);
		response.setMessage("logout Successfully!!");
		
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<Response> EmployeeEditing(int id, User user) {
		User find = userRepo.findById(id);
		find.setEmail(user.getEmail());
		find.setFirstName(user.getFirstName());
		find.setLastName(user.getLastName());
		find.setEmail(user.getEmail());
		find.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		find.setRole(user.getRole());
		userRepo.save(find);
		
		Response response = new Response();
		response.setCode(200);
		response.setError(null);
		response.setMessage("information updated successfully");
		response.setData(find);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	}
