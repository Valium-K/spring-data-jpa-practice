package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertEquals(member.getId(), foundMember.getId());
        assertEquals(member.getUserName(), foundMember.getUserName());
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        //단건 조회 검증
        Member findMember1 =
                memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 =
                memberJpaRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
        //리스트 조회 검증
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
        long deletedCount = memberJpaRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}