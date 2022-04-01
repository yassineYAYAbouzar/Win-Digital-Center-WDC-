package com.wdc.web.wdc.controller;

import com.wdc.web.wdc.Request.ParticipantRequest;
import com.wdc.web.wdc.entities.Participant;
import com.wdc.web.wdc.response.ParticipantResponse;
import com.wdc.web.wdc.services.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {


    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(ParticipantService participantService, ModelMapper modelMapper) {
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("participantList")
    public List<ParticipantResponse> getAllParticipant(@RequestParam(value="page",defaultValue = "1") int page , @RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<ParticipantResponse> responsableResponses = new ArrayList<>();

        List<Participant> participantList =participantService.getAllParticipant( page , limit);

        participantList.forEach(participant -> {
            ParticipantResponse response= modelMapper.map(participant ,ParticipantResponse.class);

            responsableResponses.add(response);
        });

        return responsableResponses;
    }

    @PostMapping("register")
    public ResponseEntity<ParticipantResponse> createParticipant(@RequestBody ParticipantRequest participantRequest) throws Exception {
        ParticipantResponse participantResponse = modelMapper.map(participantService.createParticipant(participantRequest) ,ParticipantResponse.class);
        return new ResponseEntity<ParticipantResponse>( participantResponse , HttpStatus.CREATED) ;
    }

    @DeleteMapping(path = "/{participantId}")
    public ResponseEntity<Object> deletUser(@PathVariable Long participantId ){
        participantService.deletParticipant(participantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping(path = "/{participantId}")
    public ResponseEntity<ParticipantResponse> updateParticipant(@PathVariable Long participantId , @RequestBody ParticipantRequest participantRequest){


        ParticipantResponse participantResponse  = modelMapper.map(participantService.updateParticipant(participantId,participantRequest) ,ParticipantResponse.class);

        return new ResponseEntity<ParticipantResponse>( participantResponse , HttpStatus.ACCEPTED) ;
    }

}
