package com.masai.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.main.entity.CurrentUserSession;

public interface SessionRepository extends JpaRepository<CurrentUserSession, Integer>{
	public CurrentUserSession findByUuid(String uuid);
}
