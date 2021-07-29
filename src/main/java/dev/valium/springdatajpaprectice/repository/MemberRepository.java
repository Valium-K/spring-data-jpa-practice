package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 메소드 이름으로 쿼리 생성
    List<Member> findByUserNameAndAgeGreaterThan(String username, int age);

    // 레포지토리 메소드
    @Query("select m from Member m where m.userName = :userName and m.age > :age")
    List<Member> This_method_and_upper_method_is_exactly_the_same(
            @Param("userName") String username,
            @Param("age") int age);
}
