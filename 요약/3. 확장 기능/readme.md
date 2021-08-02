### 사용자 정의 레포지토리 구현
* spring data jpa의 인터페이스를 구현하려면 직접 구현해야하는 기능이 많을 때 사용
* 다양한 이유로 인터페이스의 메소드를 직접 구현하고 싶을경우
#### 구현방법
1. 사용자정의 인터페이스 생성: [MemberRepositoryCustom](spring-data-jpa-practice/src/main/java/dev/valium/springdatajpaprectice/repository/MemberRepositoryCustom.java)
2. 해당 인터페이스 구현: [MemberRepositoryCustomImpl](spring-data-jpa-practice/src/main/java/dev/valium/springdatajpaprectice/repository/MemberRepositoryCustomImpl.java)
3. 인터페이스를 특정 레포지토리에 상속: [MemberRepository](spring-data-jpa-practice/src/main/java/dev/valium/springdatajpaprectice/repository/MemberRepository.java)

### Auditing
* 테이블의 등록일, 수정일 칼럼을 jpa에서 자동화하는 방법
#### 구현방법
1. `@EnableJpaAuditing` 을 설정에 추가
2. createdBy나 lastModifiedBy가 필요한 경우 아래의 빈을 추가
    ```java
        @Bean
        public AuditorAware<String> auditorProvider() {
            return () -> Optional.of("UUID나 수정한 사람의 이름 등...");
        }
    ```
3. BaseEntity 생성: [BaseEntity](spring-data-jpa-practice/src/main/java/dev/valium/springdatajpaprectice/entity/BaseEntity.java)
4. 최상위 엔티티에 상속을 받아 사용

### 도메인 클래스 컨버터
* RequestMapping시 pk를 받을 경우 해당 pk의 entity로 바로 받을 수 있따.
   ```java
   @GetMapping("/members/{id}")
   public String findMember(@PathValiable("id") Member m) { 
        return m;
   }
   ```
  > 트랜젝션 범위 안에서 entity를 가져오는 것이 아니기에 단순 조회용으로 쓰는것이 바람직 하다.

### web확장: 페이징과 정렬
* spring data jpa의 메소드 쿼리(find...By 형식 포함)에 Pageable을 넘겨 쉽게 페이징과 정렬을 PageRequest 직접구현 없이 바로 사용가능하다.
   ```java
   @GetMapping("/members")
   public Page<Member> mList(Pageable pageable) {
       return memberRepository.findAll(pageable);
   }
   ```
  > 이렇게하면 localhost/members?page=1&size=30 과 같이 쿼리가 가능하다.
  > 
> 