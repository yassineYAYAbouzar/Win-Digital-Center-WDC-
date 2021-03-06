package com.wdc.web.wdc.controller;

import com.wdc.web.wdc.Request.ResponsableRequest;
import com.wdc.web.wdc.entities.Responsable;
import com.wdc.web.wdc.exceptions.UserException;
import com.wdc.web.wdc.exceptions.UserNotFound;
import com.wdc.web.wdc.response.ResponsableResponse;
import com.wdc.web.wdc.services.ResponsableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {


    private final ResponsableService responsableService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(ResponsableService responsableService, ModelMapper modelMapper) {
        this.responsableService = responsableService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("responsableList")
    public List<ResponsableResponse> getAllUser(@RequestParam(value="page",defaultValue = "1") int page ,@RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<ResponsableResponse> responsableResponses = new ArrayList<>();

        List<Responsable> responsableList =responsableService.getAllResponsable( page , limit);

        responsableList.forEach(responsable -> {
            ResponsableResponse response= modelMapper.map(responsable ,ResponsableResponse.class);
            response.setTypeResponsable(responsable.getIdTypeResponsable().getName());
            responsableResponses.add(response);
        });

        return responsableResponses;
    }






    @PostMapping("")
    public ResponseEntity<ResponsableResponse> createUser(@RequestBody @Valid ResponsableRequest responsableRequest) throws Exception {
        ResponsableResponse responsableResponse = modelMapper.map(responsableService.createResponsable(responsableRequest) ,ResponsableResponse.class);
       responsableResponse.setTypeResponsable(responsableService.findTYpeById(responsableRequest.getTypeResponsable()).getName());
        return new ResponseEntity<ResponsableResponse>( responsableResponse , HttpStatus.CREATED) ;
    }



    @PatchMapping(path = "/{responsableId}")
    public ResponseEntity<ResponsableResponse> updateResponsable(@PathVariable Long responsableId , @RequestBody ResponsableRequest responsableRequest) throws Exception{
        ResponsableResponse responsableResponse  = modelMapper.map(responsableService.updateUser(responsableId,responsableRequest) ,ResponsableResponse.class);
        return new ResponseEntity<ResponsableResponse>( responsableResponse , HttpStatus.ACCEPTED) ;
    }









    @DeleteMapping(path = "/{responsableId}")
    public ResponseEntity<Object> deletUser(@PathVariable Long responsableId ) throws UserPrincipalNotFoundException{
        try {
            responsableService.deletResponsable(responsableId);
        } catch (UserNotFound e) {
            new UserNotFound("responsable Not Found ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





    @GetMapping(path = "/{responsableId}")
    public ResponseEntity<?> getResponsable(@PathVariable Long responsableId ){

        ResponsableResponse responsableResponse  = modelMapper.map(responsableService.fetchResponsable(responsableId).get() ,ResponsableResponse.class);

        if(responsableService.fetchResponsable(responsableId).isPresent()){
            return new ResponseEntity<ResponsableResponse>( responsableResponse , HttpStatus.OK) ;
        }
        return ResponseEntity.badRequest().body(new UserNotFound("Responsable  Was Not Found ." ,HttpStatus.NOT_FOUND));
    }

}
