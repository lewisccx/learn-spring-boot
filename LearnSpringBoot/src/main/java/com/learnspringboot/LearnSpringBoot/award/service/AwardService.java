package com.learnspringboot.LearnSpringBoot.award.service;

import com.learnspringboot.LearnSpringBoot.award.controller.request.AwardRequest;
import com.learnspringboot.LearnSpringBoot.award.controller.request.NewAwardRequest;
import com.learnspringboot.LearnSpringBoot.award.model.Award;

import java.util.List;
public interface AwardService {

    Award addAward(NewAwardRequest newAwardRequest);
    Award receiveAwardByStudentId(AwardRequest awardRequest);
    List<Award> getAllAwardsByStudentId(Integer studentId);
    void cancelAward(AwardRequest awardRequest);
}
