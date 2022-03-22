package com.qa.simsproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.simsproject.model.SneakerEntry;

public interface Repo extends JpaRepository<SneakerEntry, Long>{

}
