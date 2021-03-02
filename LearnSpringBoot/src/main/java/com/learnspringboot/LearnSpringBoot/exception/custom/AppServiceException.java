package com.learnspringboot.LearnSpringBoot.exception.custom;

public class AppServiceException {

    //Combine into one

    public static class StudentNotFoundException extends RuntimeException {

        public StudentNotFoundException(Integer id) {
            super("Student ID not found: " + id);
        }
    }

    public static class ContactNotFoundException extends RuntimeException {

        public ContactNotFoundException(Integer id) {
            super("Contact ID not found: " + id);
        }
    }

    public static class AssignmentNotFoundException extends RuntimeException {

        public AssignmentNotFoundException(Integer id) {
            super("Assignment ID not found: " + id);
        }
    }

    public static class AwardNotFoundException extends RuntimeException {

        public AwardNotFoundException(Integer id) {
            super("Award ID not found: " + id);
        }
    }
}
