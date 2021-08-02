package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

// MemberRepositoryCustom 구현체는 ****Impl 형식의 명명규칙을 default 로 한다.
// 기본적으로 복잡한 쿼리를 직접작성(ex. QueryDSL, 동적 쿼리 등) 해야할 때 이렇게 사용한다.
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return null;
    }
}
