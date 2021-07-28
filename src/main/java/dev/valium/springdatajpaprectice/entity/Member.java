package dev.valium.springdatajpaprectice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "userName", "age"}) // team 넣으면 순환참조에 의해 무한루프 가능성
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String userName) {
        this.userName = userName;
    }

    public void changeTeam(Team team) {
        this.team = team; // 해당 member의 team 연관관계 설정
        team.getMembers().add(this); // team의 member도 연관관계 설정
    }

    public Member(String userName, int age, Team team) {
        this.userName = userName;
        this.age = age;
        this.team = team;
    }
}
