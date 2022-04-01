package com.wdc.web.wdc.DB;

import com.wdc.web.wdc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

/*
        authoritiesRepository.save(new Authorities("CREATE_RESPONSABLE"));
        authoritiesRepository.save(new Authorities("UPDATE_RESPONSABLE"));
        authoritiesRepository.save(new Authorities("READ_ACTIVITY"));
        authoritiesRepository.save(new Authorities("READ_PARTICIPANTS"));
        authoritiesRepository.save(new Authorities("UPDATE_PARTICIPANTS"));
        authoritiesRepository.save(new Authorities("CREATE_ACTIVITY"));
        authoritiesRepository.save(new Authorities("UPDATE_ACTIVITY"));
        authoritiesRepository.save(new Authorities("DELETE_ACTIVITY"));
        authoritiesRepository.save(new Authorities("CREATE_EXERCISE"));
        authoritiesRepository.save(new Authorities("UPDATE_EXERCISE"));
        authoritiesRepository.save(new Authorities("DELETE_EXERCISE"));
        authoritiesRepository.save(new Authorities("DELETE_EXERCISE"));

        roleRepository.save(new Role("ADMIN","ADMINISTRATEUR",true));
        roleRepository.save(new Role("RESPONSABLE","RESPONSABLE",true));
        roleRepository.save(new Role("PARTICIPANT","PARTICIPANT",true));

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
