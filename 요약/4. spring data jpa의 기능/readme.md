SPRING DATA JPA의 자동적용 기능들
=====================

### @Repository 적용
* JPA예외를 spring예외로 변환한다.

### @Transactional 적용
* 서비스 계층에서 적용하지 않으면 spring data jpa가 repository에서 자동적용한다.

### spring data jpa의 save()
* 새로운 엔티티면 persist(), 아니면 merge(). 이는 `@GeneratedValue`가 아닌 직접 pk를 관리하는 엔티티일 경우 select + inesrt 쿼리가 나간다.
  * 새로운 엔티티의 판단은 pk가 null 일 경우이기 때문이다.
  * 이러한 엔티티의 경우 `Persistable<PK_타입>`의 `isNew()`, `getId()` 메소드를 오버라이드해 사용하면 된다. 
    * 방법 중 하나로 `isNew()`의 경우 `@CreatedDate Private LocalDateTime`가 null 판단할 수 있다.
    
  > merge()는 비영속 상태인 엔티티를 db에 적용할 때 만 사용해야한다.    
  > 그렇지 않은 엔티티는 변경감지를 이용하는 것이 낫다. practice 패키지 참조.

### spring data jpa 기반 네이티브 쿼리
* 정적쿼리에서는 Projection 혼용시 기존보단 편하게 사용가능
* but 네이티브쿼리는 사용하지 않는것이 낫다. JdbcTemplate, myBatis 등 사용권장.

```java
// Projections
// 아래의 속성을 가진 프록시 객체를 spring data jpa가 만들어 준다.
// Dto 처럼 사용가능 but 간단하게만 사용해야 성능에 좋음
public interface MemberProjection {
  Long getId();
  String getUserName();
  String getTeamName();
}

public interface MemberRepository extends JpaRepository<Member, Long> {
  
  // Projection + 네이티브쿼리를 Page로 받음.
  @Query(value = "select m.member_id as id, m.userName, t.name as teamName" +
          " from member m left join team t",
          countQuery = "select count(*) from member",
          nativeQuery = true)
  Page<MemberProjection> findByNativeProjection (Pageable pageable);
}
```