package com.uams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uams.entity.users;

@Repository
public interface UserRepository extends JpaRepository<users, Long> {
	Optional<users> findByUsername(String username);
}