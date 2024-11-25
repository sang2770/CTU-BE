package com.ctu.map.repository;

import com.ctu.map.models.Farm;

import org.springframework.data.jpa.repository.JpaRepository;
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
