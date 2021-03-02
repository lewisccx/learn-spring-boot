package com.learnspringboot.LearnSpringBoot.award.controller.api;

import com.learnspringboot.LearnSpringBoot.award.controller.request.AwardRequest;
import com.learnspringboot.LearnSpringBoot.award.controller.request.NewAwardRequest;
import com.learnspringboot.LearnSpringBoot.award.model.Award;
import com.learnspringboot.LearnSpringBoot.award.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
@Validated
public class AwardController {

    private final AwardService awardService;

    @Autowired
    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @PostMapping("/award/add")
    public ResponseEntity<Award> addAward(@Valid @RequestBody NewAwardRequest newAwardRequest) {
        Award award = awardService.addAward(newAwardRequest);
        return new ResponseEntity<>(award, HttpStatus.CREATED);
    }

    @PostMapping("/award/register")
    public ResponseEntity<Award> receiveAwardByStudentId(
            @Valid @RequestBody AwardRequest awardRequest) {
        Award award = awardService.receiveAwardByStudentId(awardRequest);
        return new ResponseEntity<>(award, HttpStatus.CREATED);
    }

    @GetMapping(path = "/student/{studentId}/awards")
    public ResponseEntity<List<Award>> findAwardsByStudentId(@PathVariable("studentId") @Min(1) Integer studentId) {
        List<Award> awardList = awardService.getAllAwardsByStudentId(studentId);
        return new ResponseEntity<>(awardList, HttpStatus.OK);

    }

    @PostMapping(path = "/award/cancel")
    public ResponseEntity<Void> cancelAwardByStudentId(
            @Valid @RequestBody AwardRequest awardRequest
    ) {
        awardService.cancelAward(awardRequest);
        return ResponseEntity.noContent().build();
    }
}
