package main.service;

import main.entity.Role;
import main.entity.User;
import main.repository.RoleRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getAuthUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByEmail(auth.getName());
	}

	@Override
	public User getUser(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


}
