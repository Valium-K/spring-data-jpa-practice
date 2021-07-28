package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
