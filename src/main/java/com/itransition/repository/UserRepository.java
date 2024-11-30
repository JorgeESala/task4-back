package com.itransition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itransition.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findByBlocked(Boolean isBlocked);
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	void deleteByEmail(String email);
}
