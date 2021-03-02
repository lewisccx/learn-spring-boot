package com.learnspringboot.LearnSpringBoot.contact.service;

import com.learnspringboot.LearnSpringBoot.contact.controller.request.NewContactRequest;
import com.learnspringboot.LearnSpringBoot.contact.model.Contact;

import java.util.List;
public interface ContactService {

    Contact addContact(Integer id, NewContactRequest newContactRequest);
    List<Contact> findAll();
    Contact findContactById(Integer contactId);
    Contact updateContact(Contact contact, Integer studentId);
    void deleteContact(Integer contactId);
}
