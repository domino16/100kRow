package com.dominik.kRow;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
@Transactional
public class Indexer {

    @PersistenceContext
    private EntityManager entityManager;

    public void indexData() throws InterruptedException {
        SearchSession searchSession = Search.session( entityManager );
        MassIndexer indexer = searchSession.massIndexer( ParkingTicket.class );
        indexer.startAndWait();
    }

}
