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