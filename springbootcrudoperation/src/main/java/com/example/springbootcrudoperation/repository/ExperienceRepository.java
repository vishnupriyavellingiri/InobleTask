package com.example.springbootcrudoperation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootcrudoperation.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience,Long>{
	
}
