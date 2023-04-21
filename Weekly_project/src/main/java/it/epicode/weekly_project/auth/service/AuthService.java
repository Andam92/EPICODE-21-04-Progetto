package it.epicode.weekly_project.auth.service;

import it.epicode.weekly_project.auth.payload.LoginDto;
import it.epicode.weekly_project.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
