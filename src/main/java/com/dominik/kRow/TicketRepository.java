package com.dominik.kRow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<ParkingTicket, Long> {

    List<ParkingTicket> findAllByPlateId(String plateId);

    ParkingTicket save(ParkingTicket parkingTicket);
}
