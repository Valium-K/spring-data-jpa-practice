package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.dto.MemberDto;
import dev.valium.springdatajpaprectice.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *
 * Spring Data Jpa를 이용한 repository
 */
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    // 메소드 이름으로 쿼리 생성
    List<Member> findByUserNameAndAgeGreaterThan(String username, int age);

    // 레포지토리 메소드
    @Query("select m from Member m where m.userName = :userName and m.age > :age")
    List<Member> This_method_and_upper_method_is_exactly_the_same(
            @Param("userName") String username,
            @Param("age") int age);

    // @Query를 통한 값 추출
    @Query("select m.userName from Member m where m.userName = :userName")
    List<String> findAllByUserName(@Param("userName") String userName);

    // @Query + jpql dto 추출
    @Query("select new dev.valium.springdatajpaprectice.dto.MemberDto(m.id, m.userName, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 컬렉션 파라미터 바인딩
    @Query("select m from Member m where m.userName in :names")
    List<Member> findByNames(@Param("names") List<String> names);


    // 유연한 반환타입
    Member findOneByUserName(String userName); // 단건 - 없을경우 null
    List<Member> findManyByUserName(String userName); // 컬렉션 - null이 아님을 보장
    Optional<Member> findOptionalByUserName(String userName); // Optional

    // age로 페이징
    Page<Member> findByAge(int age, Pageable pageable);

    // 벌크연산
    @Modifying(clearAutomatically = true) // executeUpdate();를 위해 필요하다. 없다면 getSingleResult();와 같이 반환한다.
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m join fetch m.team")
    List<Member> findAllfm();


    @Query(value = "select m.member_id as id, m.userName, t.name as teamName" +
            " from member m left join team t",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
