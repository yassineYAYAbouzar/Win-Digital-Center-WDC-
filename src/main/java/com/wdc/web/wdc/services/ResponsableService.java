package com.wdc.web.wdc.services;

import com.wdc.web.wdc.Request.ResponsableRequest;
import com.wdc.web.wdc.entities.Responsable;
import com.wdc.web.wdc.repositories.AuthoritiesRepository;
import com.wdc.web.wdc.repositories.ResponsableRepository;
import com.wdc.web.wdc.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponsableService {

    private final ResponsableRepository responsableRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthoritiesRepository authoritiesRepository;
    public ResponsableService(ResponsableRepository responsableRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, AuthoritiesRepository authoritiesRepository) {
        this.responsableRepository = responsableRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.authoritiesRepository = authoritiesRepository;
    }
    public List<Responsable> getAllResponsable(int page, int limit) {


        if(page > 0 ) page -= 1;

        List<Responsable> responsableList = new ArrayList<>();

        PageRequest pagebleRequest = PageRequest.of(page,limit);

        Page<Responsable> responsableEntityPage = responsableRepository.findAll(pagebleRequest);
        List<Responsable> responsableEntityList = responsableEntityPage.getContent();

        responsableEntityList.forEach(eachResponsable -> {
            Responsable responsable = modelMapper.map(eachResponsable ,Responsable.class);

            responsableList.add(responsable);
        });



        return responsableList;
    }


    public Responsable createResponsable(ResponsableRequest responsableRequest) {

        Responsable responsable = modelMapper.map(responsableRequest ,Responsable.class);


        responsable.setPassword(passwordEncoder.encode(responsable.getPassword()));
        responsable.getAuthoritiesList().add(authoritiesRepository.findById(7L).get());
        responsable.getAuthoritiesList().add(authoritiesRepository.findById(8L).get());
        responsable.getAuthoritiesList().add(authoritiesRepository.findById(9L).get());
        responsable.getAuthoritiesList().add(authoritiesRepository.findById(10L).get());
        responsable.getAuthoritiesList().add(authoritiesRepository.findById(11L).get());
        responsable.getAuthoritiesList().add(authoritiesRepository.findById(12L).get());

        responsable.setRole(roleRepository.findById(2l).get());
        responsableRepository.save(responsable);


        return responsable;
    }
    public void deletResponsable(Long responsableId) {
        Responsable responsable = responsableRepository.findById(responsableId).get();
        if(responsable == null) throw new RuntimeException("responsable Not Found ");
        responsableRepository.delete(responsable);
    }


    public Responsable updateUser(Long responsableId, ResponsableRequest responsableRequest) {

        Responsable responsable = responsableRepository.findById(responsableId).get();
        if(responsable == null) throw new RuntimeException("responsable Not Found ");

        responsable.setNom(responsableRequest.getNom());
        responsable.setPrenom(responsableRequest.getPrenom());
        responsable.setPassword(passwordEncoder.encode(responsableRequest.getPassword()));
        responsable.setDomaine(responsableRequest.getDomaine());
        responsable.setEmail(responsableRequest.getEmail());
        return responsableRepository.save(responsable);
    }



}
