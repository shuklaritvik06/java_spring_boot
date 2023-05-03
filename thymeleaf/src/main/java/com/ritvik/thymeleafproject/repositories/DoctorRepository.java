package com.ritvik.thymeleafproject.repositories;

import com.ritvik.thymeleafproject.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {}
