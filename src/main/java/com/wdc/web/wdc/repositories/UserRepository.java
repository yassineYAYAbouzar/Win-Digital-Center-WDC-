package com.wdc.web.wdc.repositories;

import com.wdc.web.wdc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, Long> {
        User findByNom(String userName);
}