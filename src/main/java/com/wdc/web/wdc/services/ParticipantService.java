package com.wdc.web.wdc.services;

import com.wdc.web.wdc.Request.ParticipantRequest;
import com.wdc.web.wdc.Request.ResponsableRequest;
import com.wdc.web.wdc.entities.Participant;
import com.wdc.web.wdc.entities.Responsable;
import com.wdc.web.wdc.entities.TypeResponsable;
import com.wdc.web.wdc.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthoritiesRepository authoritiesRepository;
    private final TypeResponsableRepository typeResponsableRepository;
    public ParticipantService(ParticipantRepository participantRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, AuthoritiesRepository authoritiesRepository, TypeResponsableRepository typeResponsableRepository) {
        this.participantRepository = participantRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.authoritiesRepository = authoritiesRepository;
        this.typeResponsableRepository = typeResponsableRepository;
    }
    public List<Participant> getAllParticipant(int page, int limit) {


        if(page > 0 ) page -= 1;

        List<Participant> participantList = new ArrayList<>();

        PageRequest pagebleRequest = PageRequest.of(page,limit);

        Page<Participant> participantsEntityPage = participantRepository.findAll(pagebleRequest);
        List<Participant> participantsEntityList = participantsEntityPage.getContent();

        participantsEntityList.forEach(eachResponsable -> {
            Participant participant = modelMapper.map(eachResponsable ,Participant.class);

            participantList.add(participant);
        });



        return participantList;
    }


    public Participant createParticipant(ParticipantRequest participantRequest) {

        Participant participant = modelMapper.map(participantRequest ,Participant.class);


        participant.setPassword(passwordEncoder.encode(participant.getPassword()));
        participant.getAuthoritiesList().add(authoritiesRepository.findById(7L).get());
        participant.getAuthoritiesList().add(authoritiesRepository.findById(8L).get());
        participant.getAuthoritiesList().add(authoritiesRepository.findById(9L).get());
        participant.getAuthoritiesList().add(authoritiesRepository.findById(10L).get());
        participant.getAuthoritiesList().add(authoritiesRepository.findById(11L).get());
        participant.getAuthoritiesList().add(authoritiesRepository.findById(4L).get());


        participant.setRole(roleRepository.findById(2l).get());
        participantRepository.save(participant);

        return participant;
    }
    public void deletParticipant(Long participantId) {
        Participant participant = participantRepository.findById(participantId).get();
        if(participant == null) throw new RuntimeException("participant Not Found ");
        participantRepository.delete(participant);
    }


    public Participant updateParticipant(Long participantId, ParticipantRequest participantRequest) {

        Participant participant = participantRepository.findById(participantId).get();
        if(participant == null) throw new RuntimeException("responsable Not Found ");

        participant.setNom(participantRequest.getNom());
        participant.setPrenom(participantRequest.getPrenom());
        participant.setPassword(passwordEncoder.encode(participantRequest.getPassword()));
        participant.setDomaine(participantRequest.getDomaine());
        participant.setEmail(participantRequest.getEmail());
        return participantRepository.save(participant);
    }



}
