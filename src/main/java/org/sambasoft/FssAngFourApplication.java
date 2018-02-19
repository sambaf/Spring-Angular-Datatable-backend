package org.sambasoft;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.sambasoft.entities.Contact;
import org.sambasoft.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FssAngFourApplication implements CommandLineRunner {

	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(FssAngFourApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactRepository
				.save(new Contact("francis", "samba", df.parse("31/05/1850"), "bennfrah@yahoo.com", "017627879002"));
		contactRepository
				.save(new Contact("Milla", "Björn", df.parse("22/15/1950"), "björn@gmail.com", "017627879002"));
		contactRepository
				.save(new Contact("Burma", "Dortmund", df.parse("11/10/1990"), "burma@yahoo.com", "000000000000"));
		contactRepository
				.save(new Contact("Media", "samba", df.parse("31/05/150"), "bennfrah@yahoo.com", "017627879002"));
		contactRepository.findAll().forEach(c->{
			System.out.println(c);
		});
	}
}
