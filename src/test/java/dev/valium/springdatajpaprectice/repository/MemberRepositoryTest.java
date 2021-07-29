package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberTest() throws Exception {
        // given
        Member member = new Member("memberA");
        Member save = memberRepository.save(member);

        // when
        Member foundMember = memberRepository.findById(save.getId()).get();

        // then
        Assertions.assertEquals(member.getId(), foundMember.getId());
        Assertions.assertEquals(member.getUserName(), foundMember.getUserName());
    }

    @Test
    @DisplayName("메소드 이름쿼리와 레포지토리 @Query 사용 비교")
    public void testQuery() throws Exception {
        // given
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("aaa", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<Member> result = memberRepository.findByUserNameAndAgeGreaterThan("aaa", 10);
        List<Member> result2 = memberRepository.This_method_and_upper_method_is_exactly_the_same("aaa", 10);

        // then
        org.assertj.core.api.Assertions.assertThat(result.get(0)).isEqualTo(m2);
        org.assertj.core.api.Assertions.assertThat(result2.get(0)).isEqualTo(m2);
    }
}
