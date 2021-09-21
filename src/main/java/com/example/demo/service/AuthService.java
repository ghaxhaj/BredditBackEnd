package com.example.demo.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.NotificationEmail;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.exception.SpringRedditException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreatedDate(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate Your Account",
				user.getEmail(),
				"Thank you for signing up with Breddit. Please Click the URL to activate your account : http://localhost:9000/api/auth/accountVerification/" + token));
	}
	
	private String generateVerificationToken(User user) {
		String s = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(s);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return s;
	}
	
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken vt) {
		String username = vt.getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found with name - " + username));
		user.setEnabled(true);
		userRepository.save(user);
	}

	public void login(LoginRequest loginRequest) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		
	}
	
	
}
