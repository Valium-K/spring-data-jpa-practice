package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import dev.valium.springdatajpaprectice.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/**
 * 순수 JPA를 이용한 Repository
 */
@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery(
                "select m from Member m", Member.class
        ).getResultList();
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(find(id));
    }

    public void delete(Member member) {
        em.remove(member);
    }
    public long count() {
        return em.createQuery(
                "select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    // 페이징
    // 자르는 기준: age,     페이징 쿼리: offset, limit
    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery(
                "select m from Member m where m.age = :age order by m.userName desc", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    // 보통 페이징 시 현제 페이지위치를 알아오기 위해 함께 짜는 코드
    // totalCount / (보여져야 할 컨텐츠 수) = 전체 페이지 수 를 구하기위함이다.
    public long totalCount(int age) {
        return em.createQuery(
                "select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    // 순수 jpa 벌크연산
    public int bulkAgePlus(int age) {
        return em.createQuery(
                "update Member m set m.age = m.age + 1" +
                " where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
