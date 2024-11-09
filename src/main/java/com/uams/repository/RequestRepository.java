package com.uams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uams.entity.request;

public interface RequestRepository extends JpaRepository<request, Long> {
	List<request> findByStatus(String status);
}
