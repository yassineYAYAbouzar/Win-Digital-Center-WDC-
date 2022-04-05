package com.wdc.web.wdc.controller;

import com.wdc.web.wdc.Request.ActivityRequest;
import com.wdc.web.wdc.Request.ExerciceRequest;
import com.wdc.web.wdc.entities.Activity;
import com.wdc.web.wdc.entities.Exercice;
import com.wdc.web.wdc.response.ActivityResponse;
import com.wdc.web.wdc.response.ExerciceResponse;
import com.wdc.web.wdc.services.ActivityService;
import com.wdc.web.wdc.services.ExerciceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/responsable")
public class ResponsableController {


    private final ActivityService activityService;
    private final ExerciceService exerciceService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResponsableController(ActivityService activityService, ExerciceService exerciceService, ModelMapper modelMapper) {
        this.activityService = activityService;
        this.exerciceService = exerciceService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("activityList")
    public List<ActivityResponse> getAllgetAllActivity(@RequestParam(value="page",defaultValue = "1") int page , @RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<ActivityResponse> activityResponse = new ArrayList<>();

        List<Activity> activityList = activityService.getAllActivity( page , limit);

        activityList.forEach(activity -> {
            ActivityResponse response = modelMapper.map(activity ,ActivityResponse.class);

            activityResponse.add(response);
        });

        return activityResponse;
    }

    @GetMapping("exerciceList")
    public List<ExerciceResponse> getAllExercice(@RequestParam(value="page",defaultValue = "1") int page , @RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<ExerciceResponse> exerciceResponse = new ArrayList<>();

        List<Exercice> exerciceList = exerciceService.getAllExercice( page , limit);

        exerciceList.forEach(exercice -> {
            ExerciceResponse response = modelMapper.map(exercice ,ExerciceResponse.class);

            exerciceResponse.add(response);
        });

        return exerciceResponse;
    }


    @PostMapping("insertActivity")
    public ResponseEntity<ActivityResponse> createActivity (@RequestBody ActivityRequest activityRequest) throws Exception {
        ActivityResponse activityResponse = modelMapper.map(activityService.createActivity(activityRequest) ,ActivityResponse.class);
        return new ResponseEntity<ActivityResponse>( activityResponse , HttpStatus.CREATED) ;
    }

    @PostMapping("insertExercice")
    public ResponseEntity<ExerciceResponse> createExercice(@RequestBody ExerciceRequest exerciceRequest) throws Exception {
        ExerciceResponse exerciceResponse = modelMapper.map(exerciceService.createExercice(exerciceRequest) ,ExerciceResponse.class);
        return new ResponseEntity<ExerciceResponse>( exerciceResponse , HttpStatus.CREATED) ;
    }

    @DeleteMapping(path = "/{activityId}")
    public ResponseEntity<Object> deletActivity(@PathVariable Long activityId ){
        activityService.deletActivity(activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{exerciceId}")
    public ResponseEntity<Object> deletExercice(@PathVariable Long exerciceId ){
        exerciceService.deletExercice(exerciceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping(path = "/{activityId}")
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable Long activityId , @RequestBody ActivityRequest activityRequest){


        ActivityResponse activityResponse  = modelMapper.map(activityService.updateActivity(activityId,activityRequest) ,ActivityResponse.class);

        return new ResponseEntity<ActivityResponse>( activityResponse , HttpStatus.ACCEPTED) ;
    }

    @PatchMapping(path = "/{exerciceId}")
    public ResponseEntity<ExerciceResponse> updateExercice(@PathVariable Long exerciceId , @RequestBody ExerciceRequest exerciceRequest){


        ExerciceResponse exerciceResponse  = modelMapper.map(exerciceService.updateExercice(exerciceId,exerciceRequest) ,ExerciceResponse.class);

        return new ResponseEntity<ExerciceResponse>( exerciceResponse , HttpStatus.ACCEPTED) ;
    }



}

