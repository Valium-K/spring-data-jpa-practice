package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("컬렉션 파라미터 바인딩 테스트")
    public void findByNames() throws Exception {
        // given
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("aaa", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        // when
        List<Member> result = memberRepository.findByNames(Arrays.asList("aaa", "bbb"));

        // then
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }

    // spring data jpa를 이용한 페이징
    @Test
    @DisplayName("spring data jpa를 이용한 페이징과 그 기능들")
    public void paging() throws Exception {
        // given
        memberRepository.save(new Member("mem1", 10));
        memberRepository.save(new Member("mem2", 10));
        memberRepository.save(new Member("mem3", 10));
        memberRepository.save(new Member("mem4", 10));
        memberRepository.save(new Member("mem5", 10));

        int age = 10;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "userName"));

        // when
        // Pageable 인터페이스의 구현체가 PageRequest이다.
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        // 반환타입이 Page이면 totalCount쿼리를 자동으로 낸다.
        // long totalCount = memberRepository.totalCount(age);

        // then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        // Page<>를 사용하면 아래의 기본적 기능을 사용 가능하다.
        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }
}
