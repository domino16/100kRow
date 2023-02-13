package com.dominik.kRow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class unitTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    TicketRepository repository;

    @Test
    public void should_find_no_ticketss_if_repository_is_empty() {
        Iterable<ParkingTicket> ticket = repository.findAll();

        assertThat(ticket).isEmpty();
    }

    @Test
    public void should_store_a_ticket() {
        ParkingTicket ticket = repository.save(new ParkingTicket("foo summons number","foo plate id","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo"));

        assertThat(ticket).hasFieldOrPropertyWithValue("plateId", "foo plate id");
        assertThat(ticket).hasFieldOrPropertyWithValue("summonsNumber", "foo summons number");
        assertThat(ticket).hasFieldOrPropertyWithValue("state", "foo");
    }

    @Test
    public void should_find_all_tickets() {
        ParkingTicket ticket1 = new ParkingTicket("foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo");
        entityManager.persist(ticket1);

        ParkingTicket ticket2 = new ParkingTicket("foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1");
        entityManager.persist(ticket2);

        ParkingTicket ticket3 = new ParkingTicket("foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2");
        entityManager.persist(ticket3);

        Iterable<ParkingTicket> tickets = repository.findAll();

        assertThat(tickets).hasSize(3).contains(ticket1, ticket2, ticket3);
    }

    @Test
    public void should_find_ticket_by_id() {
        ParkingTicket ticket1 = new ParkingTicket("foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo");
        entityManager.persist(ticket1);

        ParkingTicket ticket2 = new ParkingTicket("foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2");
        entityManager.persist(ticket2);

        ParkingTicket foundTicket = repository.findById(ticket2.getId()).orElse(null);

        assertThat(foundTicket).isEqualTo(ticket2);
    }



    @Test
    public void should_find_tickets_by_title_containing_string() {
        ParkingTicket ticket1 = new ParkingTicket("foo","something","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo");
        entityManager.persist(ticket1);

        ParkingTicket ticket2 = new ParkingTicket("foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1");
        entityManager.persist(ticket2);

        ParkingTicket ticket3 = new ParkingTicket("foo2","something","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2");
        entityManager.persist(ticket3);

        Iterable<ParkingTicket> tickets = repository.findAllByPlateId("something");

        assertThat(tickets).hasSize(2).contains(ticket1, ticket3);
    }

    @Test
    public void should_update_ticket_by_id() {
        ParkingTicket ticket1 = new ParkingTicket("foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo");
        entityManager.persist(ticket1);

        ParkingTicket ticket2 = new ParkingTicket("foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1");
        entityManager.persist(ticket2);

        ParkingTicket updatedticket = new ParkingTicket("foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2");

        ParkingTicket ticket = repository.findById(ticket2.getId()).orElse(null);
        assert ticket != null;
        ticket.setSummonsNumber(updatedticket.getSummonsNumber());
        ticket.setPlateId(updatedticket.getPlateId());
        ticket.setState(updatedticket.getState());
        repository.save(ticket);

        ParkingTicket checkticket = repository.findById(ticket2.getId()).orElse(null);

        assert checkticket != null;
        assertThat(checkticket.getId()).isEqualTo(ticket2.getId());
        assertThat(checkticket.getSummonsNumber()).isEqualTo(updatedticket.getSummonsNumber());
        assertThat(checkticket.getPlateId()).isEqualTo(updatedticket.getPlateId());
        assertThat(checkticket.getState()).isEqualTo(updatedticket.getState());
    }

    @Test
    public void should_delete_ticket_by_id() {
        ParkingTicket ticket1 = new ParkingTicket("foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo");
        entityManager.persist(ticket1);

        ParkingTicket ticket2 = new ParkingTicket("foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1","foo1");
        entityManager.persist(ticket2);

        ParkingTicket ticket3 = new ParkingTicket("foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2");
        entityManager.persist(ticket3);

        repository.deleteById(ticket2.getId());

        Iterable<ParkingTicket> tickets = repository.findAll();

        assertThat(tickets).hasSize(2).contains(ticket1, ticket3);
    }

    @Test
    public void should_delete_all_tickets() {
        entityManager.persist(new ParkingTicket("foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo","foo"));
        entityManager.persist(new ParkingTicket("foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2","foo2"));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }
}




