package com.dominik.kRow;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SearchRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public List<ParkingTicket> search(String text) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<ParkingTicket> result =
                searchSession
                        .search(ParkingTicket.class)
                        .where(f -> f.match().fields("plateId").matching(text))
                        .fetch(500);
        return result.hits();
    }
@Transactional
    public List<ParkingTicket> searchSummonsNumberAndPlateId(String text) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<ParkingTicket> result =
                searchSession
                        .search(ParkingTicket.class)
                        .where(f -> f.match().fields("summonsNumber").matching(text).fuzzy(2))
                        .fetch(500);
        return result.hits();
    }


    }