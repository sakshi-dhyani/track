package com.Track.Authentication;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Track.Repository.UserRepository;

@Service
public class JwtUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.Track.Models.User user=userRepo.findByEmail(email);
		if(user!=null)
			return new User(user.getEmail(),user.getPassword(),AuthorityUtils.createAuthorityList(user.getRole().getName()));
		else
			 throw new UsernameNotFoundException("user not found"+email);
		}
	}


