package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
