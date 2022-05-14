package com.wdc.web.wdc.controller;

import com.wdc.web.wdc.Request.ActivityRequest;
import com.wdc.web.wdc.Request.ExerciceRequest;
import com.wdc.web.wdc.entities.Activity;
import com.wdc.web.wdc.entities.Exercice;
import com.wdc.web.wdc.exceptions.UserNotFound;
import com.wdc.web.wdc.response.ActivityResponse;
import com.wdc.web.wdc.response.ExerciceResponse;
import com.wdc.web.wdc.response.ResponsableResponse;
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



    @GetMapping("activity/activityList")
    public List<ActivityResponse> getAllgetAllActivity(@RequestParam(value="page",defaultValue = "1") int page , @RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<ActivityResponse> activityResponse = new ArrayList<>();

        List<Activity> activityList = activityService.getAllActivity( page , limit);

        activityList.forEach(activity -> {
            ActivityResponse response = modelMapper.map(activity ,ActivityResponse.class);

            activityResponse.add(response);
        });

        return activityResponse;
    }

    @GetMapping("/exercice/exerciceList")
    public List<ExerciceResponse> getAllExercice(@RequestParam(value="page",defaultValue = "1") int page , @RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<ExerciceResponse> exerciceResponse = new ArrayList<>();

        List<Exercice> exerciceList = exerciceService.getAllExercice( page , limit);

        exerciceList.forEach(exercice -> {
            ExerciceResponse response = modelMapper.map(exercice ,ExerciceResponse.class);

            exerciceResponse.add(response);
        });

        return exerciceResponse;
    }


    @PostMapping("/activity")
    public ResponseEntity<ActivityResponse> createActivity (@RequestBody ActivityRequest activityRequest) throws Exception {
        ActivityResponse activityResponse = modelMapper.map(activityService.createActivity(activityRequest) ,ActivityResponse.class);
        return new ResponseEntity<ActivityResponse>( activityResponse , HttpStatus.CREATED) ;
    }

    @PostMapping("/exercice")
    public ResponseEntity<ExerciceResponse> createExercice(@RequestBody ExerciceRequest exerciceRequest) throws Exception {
        ExerciceResponse exerciceResponse = modelMapper.map(exerciceService.createExercice(exerciceRequest) ,ExerciceResponse.class);
        return new ResponseEntity<ExerciceResponse>( exerciceResponse , HttpStatus.CREATED) ;
    }

    @DeleteMapping(path = "/activity/{activityId}")
    public ResponseEntity<Object> deletActivity(@PathVariable Long activityId ){
        activityService.deletActivity(activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/exercice/{exerciceId}")
    public ResponseEntity<Object> deletExercice(@PathVariable Long exerciceId ){
        exerciceService.deletExercice(exerciceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping(path = "/activity/{activityId}")
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable Long activityId , @RequestBody ActivityRequest activityRequest){


        ActivityResponse activityResponse  = modelMapper.map(activityService.updateActivity(activityId,activityRequest) ,ActivityResponse.class);

        return new ResponseEntity<ActivityResponse>( activityResponse , HttpStatus.ACCEPTED) ;
    }

    @PatchMapping(path = "/exercice/{exerciceId}")
    public ResponseEntity<ExerciceResponse> updateExercice(@PathVariable Long exerciceId , @RequestBody ExerciceRequest exerciceRequest){


        ExerciceResponse exerciceResponse  = modelMapper.map(exerciceService.updateExercice(exerciceId,exerciceRequest) ,ExerciceResponse.class);

        return new ResponseEntity<ExerciceResponse>( exerciceResponse , HttpStatus.ACCEPTED) ;
    }
    @GetMapping(path = "/activity/{activityId}")
    public ResponseEntity<?> getActivity(@PathVariable Long activityId ){

        ActivityResponse activityResponse  = modelMapper.map(activityService.fetchActivity(activityId).get() ,ActivityResponse.class);

        if(activityService.fetchActivity(activityId).isPresent()){
                return new ResponseEntity<ActivityResponse>( activityResponse, HttpStatus.OK) ;
        }
        return ResponseEntity.badRequest().body(new UserNotFound("activty  Was Not Found ." ,HttpStatus.NOT_FOUND));
    }
    @GetMapping(path = "/exercice/{exerciceId}")
    public ResponseEntity<?> getAExercice(@PathVariable Long exerciceId ){

        ExerciceResponse exerciceResponse  = modelMapper.map(exerciceService.fetchExercie(exerciceId).get() ,ExerciceResponse.class);

        if(exerciceService.fetchExercie(exerciceId).isPresent()){
            return new ResponseEntity<ExerciceResponse>( exerciceResponse, HttpStatus.OK) ;
        }
        return ResponseEntity.badRequest().body(new UserNotFound("exercice  Was Not Found ." ,HttpStatus.NOT_FOUND));
    }


}

