package com.learnspringboot.LearnSpringBoot.contact.controller.api;

import com.learnspringboot.LearnSpringBoot.contact.controller.request.NewContactRequest;
import com.learnspringboot.LearnSpringBoot.contact.model.Contact;
import com.learnspringboot.LearnSpringBoot.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
@RequestMapping("/api/v1")
@RestController
@Validated
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = "/contacts/all")
    public ResponseEntity<List<Contact>> findAll() {
        List<Contact> contactList = contactService.findAll();
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

    @GetMapping(path = "/contacts/{id}")
    public ResponseEntity<Contact> findContactById(@PathVariable("id") @Min(1) Integer id) {
        Contact contact = contactService.findContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/contact/add")
    public ResponseEntity<Contact> addContact(@PathVariable("studentId") @Min(1) Integer studentId,
                                              @Valid @RequestBody NewContactRequest newContactRequest) {
        Contact c = contactService.addContact(studentId, newContactRequest);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<Contact> updateContact(@PathVariable("contactId") @Min(1) Integer contactId,
                                                 @Valid @RequestBody Contact contactUpdated) {
        Contact c = contactService.updateContact(contactUpdated, contactId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable("contactId") @Min(1) Integer contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }

}
