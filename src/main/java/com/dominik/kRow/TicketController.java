package com.dominik.kRow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/lucene/{plateId}")
    public ResponseEntity<?> getTicketByPlateIdByLucene(@PathVariable String plateId){
        List<ParkingTicket> result = searchRepository.search(plateId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/lucene1/{summonsNumber}")
    public ResponseEntity<?> getTicketSummonsNumberByLucene(@PathVariable String summonsNumber){
        List<ParkingTicket> result = searchRepository.searchSummonsNumberAndPlateId(summonsNumber);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/jpa/{plateId}")
    public ResponseEntity<?> getTicketByPlateIdJpa(@PathVariable String plateId){
        List<ParkingTicket> result = ticketRepository.findAllByPlateId(plateId);
        return ResponseEntity.ok(result);
    }
}
