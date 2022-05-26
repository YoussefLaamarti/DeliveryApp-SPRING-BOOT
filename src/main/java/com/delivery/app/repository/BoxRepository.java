package com.delivery.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.delivery.app.model.Box;
import org.springframework.stereotype.Repository;


@Repository
public interface BoxRepository extends JpaRepository<Box,Long> {
	
	@Query("SELECT p FROM Box p WHERE p.code = ?1")
	public Box findByRef(String code);

	public Box findByCode(String code);


}
