package com.uams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uams.entity.software;

@Repository
public interface SoftwareRepository extends JpaRepository<software, Long>{

}
