package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
}
