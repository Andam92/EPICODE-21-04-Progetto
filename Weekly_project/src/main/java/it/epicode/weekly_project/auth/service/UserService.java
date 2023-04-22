package it.epicode.weekly_project.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.weekly_project.auth.entity.User;
import it.epicode.weekly_project.auth.payload.LoginDto;
import it.epicode.weekly_project.auth.repository.UserRepository;
import jakarta.persistence.EntityExistsException;

@Service
public class UserService {

	@Autowired UserRepository userRepo;
	
	
	public User findUserByUsername(LoginDto login) {
		return userRepo.findUserByUsername(login.getUsername());
	}
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	public User addUser(User u) {
		return userRepo.save(u);
	}
	
	public String deleteUserById(Long id) {
		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return "User deleted";
		} else {
			throw new EntityExistsException("The user with ID " + id + " does not exist!");
		}
	}
	
}
