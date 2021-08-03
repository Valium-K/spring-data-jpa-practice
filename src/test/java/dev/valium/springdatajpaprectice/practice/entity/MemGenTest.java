package dev.valium.springdatajpaprectice.practice.entity;

import dev.valium.springdatajpaprectice.practice.repository.MemNonGenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemGenTest {

    @Autowired
    private MemNonGenRepository repository;

    @Test
    public void tset() throws Exception {
        // given
        repository.save(new MemNonGen("test"));
        // when

        // then
    }
}