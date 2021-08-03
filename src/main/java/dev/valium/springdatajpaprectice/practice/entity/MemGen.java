package dev.valium.springdatajpaprectice.practice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MemGen extends EntityBase {

    @Id @GeneratedValue
    private Long id;

    private String name;
}
