package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;

import java.util.List;

// 사용자 정의 레포지토리
public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
