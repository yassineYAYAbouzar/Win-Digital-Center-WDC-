package com.wdc.web.wdc.services;

import com.wdc.web.wdc.entities.Exercice;
import com.wdc.web.wdc.Request.ExerciceRequest;
import com.wdc.web.wdc.repositories.ExerciceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciceService {

    private final ExerciceRepository exerciceRepository;
    private final ModelMapper modelMapper;
    public ExerciceService(ExerciceRepository exerciceRepository, ModelMapper modelMapper) {
        this.exerciceRepository = exerciceRepository;
        this.modelMapper = modelMapper;
    }

    public List<Exercice> getAllExercice(int page, int limit) {


        if(page > 0 ) page -= 1;

        List<Exercice> exerciceList = new ArrayList<>();

        PageRequest pagebleRequest = PageRequest.of(page,limit);

        Page<Exercice> exerciceEntityPage = exerciceRepository.findAll(pagebleRequest);
        List<Exercice> exerciceEntityList = exerciceEntityPage.getContent();

        exerciceEntityList.forEach(eachExercice -> {
            Exercice exercice = modelMapper.map(eachExercice ,Exercice.class);

            exerciceList.add(exercice);
        });



        return exerciceList;
    }

    public Exercice createExercice(ExerciceRequest exerciceRequest) {

        Exercice exercice = modelMapper.map(exerciceRequest ,Exercice.class);

        exerciceRepository.save(exercice);


        return exercice;
    }

    public void deletExercice(Long exerciceId) {
        Exercice exercice = exerciceRepository.findById(exerciceId).get();
        if(exercice == null) throw new RuntimeException("exercice Not Found ");
        exerciceRepository.delete(exercice);
    }

    public Exercice updateExercice(Long exerciceId, ExerciceRequest exerciceRequest) {

        Exercice exercice = exerciceRepository.findById(exerciceId).get();
        if(exercice == null) throw new RuntimeException("exercice Not Found ");

        exercice.setAnnee(exerciceRequest.getAnnee());
        exercice.setDateDebuit(exerciceRequest.getDateDebuit());
        exercice.setDateFin(exerciceRequest.getDateFin());
        exercice.setStatus(exerciceRequest.getStatus());

        return exerciceRepository.save(exercice);
    }
}
