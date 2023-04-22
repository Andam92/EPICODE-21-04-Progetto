package it.epicode.weekly_project.auth.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.weekly_project.auth.entity.Device;
import it.epicode.weekly_project.auth.entity.User;
import it.epicode.weekly_project.auth.exception.GlobalExceptionHandler;
import it.epicode.weekly_project.auth.payload.LoginDto;
import it.epicode.weekly_project.auth.repository.UserRepository;
import it.epicode.weekly_project.auth.service.DeviceService;
import it.epicode.weekly_project.auth.service.UserService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired UserRepository userRepo;
	
	@Autowired
	UserService userService;
	@Autowired
	DeviceService deviceService;

	// per simulare un login e ottenere il proprio User: nel path inserire username,
	// nel body inserire oggetto con username e password, nell'authorization inserire il proprio token!
	@GetMapping("/{username}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getLoggedUser(@RequestBody LoginDto login, @PathVariable String username)
			throws AccessDeniedException {
		if (login.getUsername().equals(username)) {

			return new ResponseEntity<>(userService.findUserByUsername(login), HttpStatus.OK);
		} else {

			// throw new EntityNotFoundException();
			throw new AccessDeniedException("Access denied!");
		}
	}
	
	
	// per ottenere una lista degli utenti, SOLO se si è loggati come admin o moderator
	@GetMapping
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> getUserList(){
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}
	
	
	// per ottenere i dispositivi associati a un utente tramite id utente
	@GetMapping("/devices/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDevices(@PathVariable Long id){
		List<Device> deviceList = deviceService.findUserDevices(id);
		if(deviceList.size() == 0) {
			return new ResponseEntity<>("There are no devices for this user", HttpStatus.OK);
		}
		return new ResponseEntity<>(deviceService.findUserDevices(id), HttpStatus.OK);
	}
	
	
	// crea user
	@PostMapping
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> addNewDevice(@RequestBody User user) {
		
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
	}
	
	
	// cancella user
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> deleteDevice(@PathVariable Long id){
		return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
	}
	
	
	// modifica un user
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> editDevice(@RequestBody User user, @PathVariable Long id)  {
		
		user.setId(id);
		 return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	}

}
