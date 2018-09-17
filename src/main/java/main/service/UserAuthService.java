package main.service;

import main.entity.User;

import java.util.List;

public interface UserAuthService {
	User findUserByEmail(String login);
	User getAuthUser();

	User getUser(int id);

	List<User> getAllUsers();
}
