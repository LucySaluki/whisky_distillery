package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="age", required = false) Integer age,
            @RequestParam(name="distillery", required = false) String distillery,
            @RequestParam(name="region", required = false) String region){

        if (year!= null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(year), HttpStatus.OK);
        } else if (distillery!=null && age!= null){
            Distillery distilleryObj = distilleryRepository.findDistilleryByName(distillery);
            return new ResponseEntity<>(whiskyRepository.findWhiskyByAgeAndDistilleryId(age,distilleryObj.getId()), HttpStatus.OK);
        } else if (region!=null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryRegion(region), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }

}
