package org.sambasoft.controllers;

import org.sambasoft.entities.Contact;
import org.sambasoft.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("/getcontacts") 
	public ResponseEntity<Page<Contact>> getContacts(@RequestParam(defaultValue="0")int page) {
		Page<Contact> contacts = contactRepository.findAll(new PageRequest(page, 5)); 
		if(contacts.hasContent()) {
			return new ResponseEntity<>(contacts, HttpStatus.OK);

		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);


	}

	@GetMapping("/search")
	public Page<Contact> searchByFirstName(@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return contactRepository.search("%" + keyword + "%", new PageRequest(page, size));
	}

	@GetMapping("/getcontact/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable(name="id")Long id) {
		System.out.println(id);
		/*Contact c= contactRepository.findOne(id);
		if(c==null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		}*/
		return new ResponseEntity<>(contactRepository.findOne(id), HttpStatus.OK); 

	}

	@GetMapping("/delcontact/{id}")
	public boolean deleteContact(@PathVariable(name="id")Long id) {
		 System.out.println(id);
		contactRepository.delete(id);
		return true;
	}

	@PostMapping("/createcontact")
	public ResponseEntity<Contact>  createContact(@RequestBody Contact contact) {
		Contact c = contactRepository.findOne(contact.getId());
		if(c!=null) {
			return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(contactRepository.save(contact),HttpStatus.CREATED);
	}

	@PutMapping("/updatecontact")
	public ResponseEntity<Contact>  updatecontact(@RequestBody Contact contact) {
		/*Contact c = contactRepository.findOne(contact.getId());
		if(c!=null) {
			return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		}*/
		return new ResponseEntity<>(contactRepository.save(contact),HttpStatus.OK);
	}
}
