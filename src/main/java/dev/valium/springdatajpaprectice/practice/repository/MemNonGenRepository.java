package dev.valium.springdatajpaprectice.practice.repository;

import dev.valium.springdatajpaprectice.practice.entity.EntityBase;
import dev.valium.springdatajpaprectice.practice.entity.MemNonGen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemNonGenRepository extends JpaRepository<MemNonGen, String> {
}
