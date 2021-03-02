package com.learnspringboot.LearnSpringBoot.award.repository;

import com.learnspringboot.LearnSpringBoot.award.model.Award;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {

}
