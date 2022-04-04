package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Comics;

@Repository
public interface ComicRepository extends JpaRepository<Comics, Integer>{

}