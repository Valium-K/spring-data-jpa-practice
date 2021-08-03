package dev.valium.springdatajpaprectice.practice.entity;

import org.springframework.data.domain.Persistable;

// 엔티티 베이스 but id는 generatedValue가 아님
public abstract class EntityBaseNonGenPK <T> extends EntityBase implements Persistable<T> {

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }
}
