쿼리 메소드
=========
### 메소드 이름 쿼리
* 메소드 이름으로 쿼리생성 키워드: [docs.spring.io](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)    
* 메소드 이름으로 쿼리생성 예제: [docs.spring.io](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation)

### JPA named query
* 잘 사용하지 않음 - 레포지토리 메소드에 쿼리를 직접 작성 가능하기 때문

### @Query 레포지토리 메소드
* @Query는 앱 로딩시 파싱을 하기에 컴파일 타임 에러처럼 작동한다.
* 메소드 이름 쿼리가 길어질 경우(where문이 길 때) 사용한다.
* 동적 쿼리는 QueryDSL을 이용하는것이 편하다.

### List<> 반환타입은 null이 아님을 보장한다.
* 쿼리 결과가 없을 경우 빈 컬렉션을 반환하므로 null 검증코드는 필요가 없다.

### spring data jpa 에서는 .getSingleResult()의 값이 없을경우 null을 반환한다.
* NoResultException을 try-catch로 내부적으로 감싸 null을 반환시킨다.
* 단, 반환타입을 Optional로 감쌌다면 당연히 `Optional.empty` 이다.
* 2개 이상일 경우 `IncorrectResultSizeDataAccessException` 이 발생한다.
    * 단순 JPA일 경우에는 `NonUniqueResultException` 이 발생한다.
  
### page
* 상속관계는 Slice < Page < PageResult(구현체) 이다.
  * PageRequest는 Pageable의 구현체이다.
  
* Slice는 가져올 size를 정의하지만 실제로는 size+1만큼 가져온다.
  * 모바일에서 뒤에 컨텐츠가 더 있을을 알리는 용도로 사용가능(ex. 더보기 버튼에 컨텐츠를 흐릿하게 겹침)

* Slice는 totalCount쿼리를 날리지 않는다.
* Page는 totalCount쿼리를 날린다.
* 반환타입을 List로 받으면 단순히 가져올 개수를 limit하는 기능이다.
* API에서 사용 시 Page.map()을 이용해 entity -> Dto로 변환 가능하다.
* Page index는 0부터 시작이다.
* Page에서 자동으로 생성하는 totalCount쿼리의 최적화가 필요하다면(기본적으로 전체조회는 무겁기에),
  * `@Query(value = "쿼리",  countQuery = "카운트 쿼리")`를 직접 작성한다.
  
#### 구현방법
1. 메소드 쿼리 등록: `Page<Member> findByAge(int age, Pageable pageable);`
2. PageRequest를 구현 후 사용: [spring data jpa를 이용한 페이징과 그 기능들](src/main/java/dev/valium/springdatajpaprectice/repository/MemberRepository.java)

### 벌크성 수정쿼리
* where 문에 만족하는 조건에 대해 벌크연산 및 update한 갯수를 리턴한다.
* 벌크연산은 영속성 컨텍스트를 무시하고 DB에 update를 하기에 주의해야한다.
  * 순수 JPA: 벌크연산 직후 em.flush(); em.clear(); 를 하면 된다.
  * spring data jpa: `@Modifying(clearAutomatically = true)` 을 준다.
  
### @EntityGraph
* 간단한 jpql의 fetch join시 사용가능하다.
  * `@EntityGraph(attributepaths = {"조인_대상을_가리키는_필드명"})`