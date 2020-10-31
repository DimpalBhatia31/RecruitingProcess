package com.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	public Application findByEmail(String email);
	
	public List<Application> findByOffer( String offer);
}
