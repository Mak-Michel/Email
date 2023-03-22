package com.example.Email_Back;
import com.example.Email_Back.Model.Caches.EmailCache;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailBackApplication {

	@Autowired
	private EmailCache emailCache;


	public static void main(String[] args) {SpringApplication.run(EmailBackApplication.class, args);}




	@PreDestroy
	public void destroy() {
		System.out.println("Closing session\nSaving Emails...");
		this.emailCache.saveDBContents();
	}

}
