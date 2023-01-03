package com.example.Email_Back;
import com.example.Email_Back.Controller.EmailService;
import com.example.Email_Back.Model.Caches.EmailCache;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Utils.RandomGenerator;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class EmailBackApplication {

	@Autowired
	private EmailCache emailCache;


	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(EmailBackApplication.class, args);
		EmailService emailService = app.getBean(EmailService.class);
		Email email = app.getBean(Email.class);
		email.setEmailProperties(RandomGenerator.generateId(),"hi please work", 20230301, "ehabyasser", new ArrayList<String>(1), "hiii", new ArrayList<String>(0));
		emailService.createNewEmail(email);
	}




	@PreDestroy
	public void destroy() {
		System.out.println("Closing session\nSaving Emails...");
		this.emailCache.saveDBContents();
	}

}