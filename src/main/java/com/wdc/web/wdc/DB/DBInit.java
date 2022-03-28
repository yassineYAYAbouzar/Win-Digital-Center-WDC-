package com.wdc.web.wdc.DB;

import com.wdc.web.wdc.entities.Responsable;
import com.wdc.web.wdc.repositories.ResponsableRepository;
import com.wdc.web.wdc.repositories.RoleRepository;
import com.wdc.web.wdc.repositories.UserRepository;
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

    @Autowired
    public DBInit(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ResponsableRepository responsableRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.responsableRepository = responsableRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      //  User user = userRepository.findByUserName("yassine");
       // System.out.println(user.getRole());

        Responsable admin = new Responsable("yassine","yassine",passwordEncoder.encode("password"),"email@email.com","6666", true,"domaine");

        admin.setRole(this.roleRepository.findById(1l).get());
        //admin.setAuthoritiesList(this.authoritiesRepository.findAll() );
        responsableRepository.save(admin);




    }


}
