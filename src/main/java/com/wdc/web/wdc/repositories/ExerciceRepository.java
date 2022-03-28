package com.wdc.web.wdc.repositories;

import com.wdc.web.wdc.entities.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long>, JpaSpecificationExecutor<Exercice> {

}