package com.wdc.web.wdc.services;

import com.wdc.web.wdc.Request.ActivityRequest;
import com.wdc.web.wdc.entities.Activity;
import com.wdc.web.wdc.entities.TypeActivity;
import com.wdc.web.wdc.repositories.ActivityRepository;
import com.wdc.web.wdc.repositories.TypeActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final TypeActivityRepository typeActivityRepository;
    private final ModelMapper modelMapper;
    public ActivityService(ActivityRepository activityRepository, TypeActivityRepository typeActivityRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.typeActivityRepository = typeActivityRepository;
        this.modelMapper = modelMapper;
    }

    public List<Activity> getAllActivity(int page, int limit) {


        if(page > 0 ) page -= 1;

        List<Activity> activityList = new ArrayList<>();

        PageRequest pagebleRequest = PageRequest.of(page,limit);

        Page<Activity> activityEntityPage = activityRepository.findAll(pagebleRequest);
        List<Activity> activityEntityList = activityEntityPage.getContent();

        activityEntityList.forEach(eachActivity -> {
            Activity activity = modelMapper.map(eachActivity ,Activity.class);

            activityList.add(activity);
        });



        return activityList;
    }

    public Activity createActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity() ;
        activity.setEtat(activityRequest.getEtat());
        activity.setIdResponsable(activityRequest.getIdResponsable());
        activity.setIdExercise(activityRequest.getIdExercise());
        activity.setDateDebuit(activityRequest.getDateDebuit());
        activity.setDateFin(activityRequest.getDateFin());
        activity.setDescription(activityRequest.getDescription());
        activity.setIdTypeActivity(findTypeById(activityRequest.getTypeActivity()));
        activityRepository.save(activity);
        return activity;
    }

    public void deletActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).get();
        if(activity == null) throw new RuntimeException("activity Not Found ");
        activityRepository.delete(activity);
    }

    public Activity updateActivity(Long activityId, ActivityRequest activityRequest) {

        Activity activity = activityRepository.findById(activityId).get();
        if(activity == null) throw new RuntimeException("activity Not Found ");

        activity.setDescription(activityRequest.getDescription());
        activity.setDateDebuit(activityRequest.getDateDebuit());
        activity.setDateFin(activityRequest.getDateFin());
        activity.setEtat(activityRequest.getEtat());
        activity.setIdResponsable(activityRequest.getIdResponsable());
        activity.setIdExercise(activityRequest.getIdExercise());
        //activity.setIdTypeActivity(activityRequest.getIdTypeActivity());

        return activityRepository.save(activity);
    }
    public Optional<Activity> fetchActivity(Long responsableId) {
        return activityRepository
                .findById(responsableId);
    }
    public TypeActivity findTypeById(Long id) {
        return typeActivityRepository.findById(id).get();
    }
}
