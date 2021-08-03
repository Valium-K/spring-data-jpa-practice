package dev.valium.springdatajpaprectice.practice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MemNonGen extends EntityBaseNonGenPK<String> {

    @Id
    private String name;

    protected MemNonGen() {}
    public MemNonGen(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return this.name;
    }
}
