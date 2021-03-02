package com.learnspringboot.LearnSpringBoot.contact.service;

import com.learnspringboot.LearnSpringBoot.contact.controller.request.NewContactRequest;
import com.learnspringboot.LearnSpringBoot.contact.model.Contact;
import com.learnspringboot.LearnSpringBoot.contact.repository.ContactRepository;
import com.learnspringboot.LearnSpringBoot.exception.custom.AppServiceException;
import com.learnspringboot.LearnSpringBoot.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final StudentRepository studentRepository;

    private final Contact contact;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, StudentRepository studentRepository, Contact contact) {
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
        this.contact = contact;
    }

    @Override
    public Contact addContact(Integer id, NewContactRequest newContactRequest) {
        return studentRepository.findById(id)
                .map(student -> {
                    contact.setEmail(newContactRequest.getEmail());
                    contact.setMobile(newContactRequest.getMobile());
                    contact.setStudent(student);
                    return contactRepository.save(contact);
                }).orElseThrow(() -> new AppServiceException.StudentNotFoundException(id));
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findContactById(Integer contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()) {
            return contact.get();
        }
        throw new AppServiceException.StudentNotFoundException(contactId);
    }

    @Override
    public Contact updateContact(Contact update, Integer contactId) {
        return contactRepository.findById(contactId).map(
                contact -> {
                    contact.setMobile(update.getMobile());
                    contact.setEmail(update.getEmail());
                    return contactRepository.save(contact);

                }).orElseThrow(() -> new AppServiceException.ContactNotFoundException(contactId));

    }

    @Override
    public void deleteContact(Integer contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()) {
            contactRepository.deleteById(contactId);
            return;
        }
        throw new AppServiceException.ContactNotFoundException(contactId);
    }

}
