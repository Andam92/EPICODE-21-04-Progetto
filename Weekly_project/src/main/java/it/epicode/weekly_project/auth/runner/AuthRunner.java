package it.epicode.weekly_project.auth.runner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.weekly_project.auth.entity.Device;
import it.epicode.weekly_project.auth.entity.Device_type;
import it.epicode.weekly_project.auth.entity.ERole;
import it.epicode.weekly_project.auth.entity.Role;
import it.epicode.weekly_project.auth.entity.User;
import it.epicode.weekly_project.auth.repository.DeviceRepository;
import it.epicode.weekly_project.auth.repository.RoleRepository;
import it.epicode.weekly_project.auth.repository.UserRepository;
import it.epicode.weekly_project.auth.security.CustomUserDetailsService;
import it.epicode.weekly_project.auth.service.AuthService;
import it.epicode.weekly_project.auth.service.DeviceService;


@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired DeviceRepository deviceRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	@Autowired CustomUserDetailsService userService;
	@Autowired DeviceService deviceService;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		//setRoleDefault();
		
		//ADDS DEVICE
		//deviceService.createDevice(Device_type.SMARTPHONE);
		
		//ASSIGNS DEVICE TO USER
//		User u1 = userRepository.findById(1l).get();
//		Device d1 = deviceRepository.findById(1l).get();
//		u1.getDeviceList().add(d1);
//		userRepository.save(u1);
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);
		
		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}

}
