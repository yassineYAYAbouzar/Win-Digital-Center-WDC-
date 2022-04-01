package com.wdc.web.wdc.controller;

import com.wdc.web.wdc.Request.ParticipantRequest;
import com.wdc.web.wdc.Request.ResponsableRequest;
import com.wdc.web.wdc.response.ParticipantResponse;
import com.wdc.web.wdc.response.ResponsableResponse;
import com.wdc.web.wdc.services.ParticipantService;
import com.wdc.web.wdc.services.ResponsableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {


    private final ParticipantService participantService;
    private final ResponsableService responsableService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(ParticipantService participantService, ResponsableService responsableService, ModelMapper modelMapper) {
        this.participantService = participantService;
        this.responsableService = responsableService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("register")
    public ResponseEntity<ParticipantResponse> createParticipant(@RequestBody ParticipantRequest participantRequest) throws Exception {
        ParticipantResponse participantResponse = modelMapper.map(participantService.createParticipant(participantRequest) ,ParticipantResponse.class);
        return new ResponseEntity<ParticipantResponse>( participantResponse , HttpStatus.CREATED) ;
    }

    @PatchMapping(path = "/participant/{participantId}")
    public ResponseEntity<ParticipantResponse> updateProfileParticipant(@PathVariable Long participantId , @RequestBody ParticipantRequest participantRequest){


        ParticipantResponse participantResponse  = modelMapper.map(participantService.updateParticipant(participantId,participantRequest) ,ParticipantResponse.class);

        return new ResponseEntity<ParticipantResponse>( participantResponse , HttpStatus.ACCEPTED) ;
    }
    @PatchMapping(path = "/responsable/{responsableId}")
    public ResponseEntity<ResponsableResponse> updateProfileResponsable(@PathVariable Long responsableId , @RequestBody ResponsableRequest responsableRequest){


        ResponsableResponse responsableResponse  = modelMapper.map(responsableService.updateUser(responsableId,responsableRequest) , ResponsableResponse.class);

        return new ResponseEntity<ResponsableResponse>( responsableResponse , HttpStatus.ACCEPTED) ;
    }


}
