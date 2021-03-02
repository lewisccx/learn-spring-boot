package com.learnspringboot.LearnSpringBoot.award.service;

import com.learnspringboot.LearnSpringBoot.award.controller.request.AwardRequest;
import com.learnspringboot.LearnSpringBoot.award.controller.request.NewAwardRequest;
import com.learnspringboot.LearnSpringBoot.award.model.Award;
import com.learnspringboot.LearnSpringBoot.award.repository.AwardRepository;
import com.learnspringboot.LearnSpringBoot.exception.custom.AppServiceException;
import com.learnspringboot.LearnSpringBoot.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;

    private final StudentRepository studentRepository;

    private final Award award;

    @Autowired
    public AwardServiceImpl(AwardRepository awardRepository, StudentRepository studentRepository, Award award) {
        this.awardRepository = awardRepository;
        this.studentRepository = studentRepository;
        this.award = award;
    }

    @Override
    public Award addAward(NewAwardRequest newAwardRequest) {
        award.setAmount(newAwardRequest.getAmount());
        award.setName(newAwardRequest.getName());
        return awardRepository.save(award);
    }

    @Override
    public Award receiveAwardByStudentId(AwardRequest awardRequest) {
        Integer studentId = awardRequest.getStudentId();
        Integer awardId = awardRequest.getAwardId();
        return studentRepository.findById(studentId)
                .map(student -> awardRepository.findById(awardId).map(award -> {
                    student.getAwardSet().add(award);
                    award.getStudentSet().add(student);
                    return awardRepository.save(award);
                }).orElseThrow(() -> new AppServiceException.StudentNotFoundException(awardId))).orElseThrow(() -> new AppServiceException.StudentNotFoundException(studentId));

    }

    @Override
    public List<Award> getAllAwardsByStudentId(Integer studentId) {
        List<Award> awardArrayList = new ArrayList<Award>();
        studentRepository.findById(studentId).map(
                student -> awardArrayList.addAll(student.getAwardSet())).orElseThrow(() -> new AppServiceException.StudentNotFoundException(studentId));
        return awardArrayList;
    }

    @Override
    public void cancelAward(AwardRequest awardRequest) {
        Integer studentId = awardRequest.getStudentId();
        Integer awardId = awardRequest.getAwardId();
        studentRepository.findById(studentId)
                .map(student -> awardRepository.findById(awardId).map(award -> {
                    student.getAwardSet().remove(award);
                    award.getStudentSet().remove(student);
                    return awardRepository.save(award);
                }).orElseThrow(() -> new AppServiceException.StudentNotFoundException(awardId))).orElseThrow(() -> new AppServiceException.StudentNotFoundException(studentId));
    }

}
