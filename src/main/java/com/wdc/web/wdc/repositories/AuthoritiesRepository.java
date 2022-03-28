package com.wdc.web.wdc.repositories;

import com.wdc.web.wdc.entities.Authorities;
import com.wdc.web.wdc.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>{

}