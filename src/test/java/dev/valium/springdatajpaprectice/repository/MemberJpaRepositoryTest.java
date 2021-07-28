package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

// Junit5 + springBoot 조합은 이 에노테이션 하나면 ok
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void memberTest() throws Exception {
        // given
        Member member = new Member("memberA");
        Member save = memberJpaRepository.save(member);

        // when
        Member foundMember = memberJpaRepository.find(save.getId());

        // then
        Assertions.assertEquals(member.getId(), foundMember.getId());
        Assertions.assertEquals(member.getUserName(), foundMember.getUserName());
    }
}