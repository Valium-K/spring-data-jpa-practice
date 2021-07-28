공통 인터페이스 기능
================
## 기본
* spring boot를 사용 시 JavaConfig설정은 생략가능
* `JpaRepository<T, ID>`인터페이스를 상속받은 클래스를 spring data jpa가 찾아 자동으로 구현체를 생성함
    > 이 때 구현체는 class com.sun.proxy.$Proxy###
* `@Repository`는 생략가능
* org.springframework.data:spring-data-commons 는 jpa, redis 등 공통으로 사용되는 기능 패키지이다.
    > 이곳에 기본적인 CrudRepository<>가 있다.
 
