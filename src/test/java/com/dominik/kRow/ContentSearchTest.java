package com.dominik.kRow;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
    public class ContentSearchTest {

        private final Logger log = LoggerFactory.getLogger(ContentSearchTest.class);

        @PersistenceContext
        private EntityManager entityManager;

        @Test
        public void canSearchContent() {

            SearchResult<ParkingTicket> results = Search.session(entityManager)
                    .search(ParkingTicket.class)
                    .where(f -> f.matchAll())
                    .fetch(10);

            assertThat(results.total().hitCount()).isEqualTo(42339438);

        }
    }
