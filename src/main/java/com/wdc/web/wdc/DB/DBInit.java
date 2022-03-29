package com.wdc.web.wdc.DB;

import com.wdc.web.wdc.entities.Authorities;
import com.wdc.web.wdc.entities.Participant;
import com.wdc.web.wdc.entities.Responsable;
import com.wdc.web.wdc.entities.User;
import com.wdc.web.wdc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ResponsableRepository responsableRepository;
    private final ParticipantRepository participantRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Autowired
    public DBInit(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ResponsableRepository responsableRepository, ParticipantRepository participantRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.responsableRepository = responsableRepository;
        this.participantRepository = participantRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      //  User user = userRepository.findByUserName("yassine");
       // System.out.println(user.getRole());
/*
        Responsable admin = new Responsable("yassine","yassine",passwordEncoder.encode("password"),"email@email.com","6666", true,"domaine");

        admin.setRole(roleRepository.findById(1l).get());
        admin.setAuthoritiesList(authoritiesRepository.findAll());
        responsableRepository.save(admin);

        Participant participant = new Participant("hamza","hamza",passwordEncoder.encode("password"),"email@email.com","6666", true,"domaine","domaine");

        participant.setRole(roleRepository.findById(3l).get());
        admin.setAuthoritiesList(authoritiesRepository.findAll() );
        participantRepository.save(participant);

*/
/*
      User admin = new User("yassine","bouzar",passwordEncoder.encode("password"),"yassine@email.com","6666", true);

        admin.setRole(roleRepository.findById(1l).get());
        admin.getAuthoritiesList().add(authoritiesRepository.findById(1L).get());
        admin.getAuthoritiesList().add(authoritiesRepository.findById(2L).get());
        admin.getAuthoritiesList().add(authoritiesRepository.findById(3L).get());
        admin.getAuthoritiesList().add(authoritiesRepository.findById(4L).get());
        admin.getAuthoritiesList().add(authoritiesRepository.findById(5L).get());
        admin.getAuthoritiesList().add(authoritiesRepository.findById(6L).get());
        userRepository.save(admin);
*/
    }


}
