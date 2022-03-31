package com.wdc.web.wdc.repositories;

import com.wdc.web.wdc.entities.TypeResponsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeResponsableRepository extends JpaRepository<TypeResponsable, Long> {

}