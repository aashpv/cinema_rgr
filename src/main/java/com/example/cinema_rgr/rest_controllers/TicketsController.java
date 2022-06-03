package com.example.cinema_rgr.rest_controllers;

import com.example.cinema_rgr.entity.Ticket;
import com.example.cinema_rgr.entity.composite.TicketId;
import com.example.cinema_rgr.entity.statuses.TicketStatus;
import com.example.cinema_rgr.service.TicketsService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
@Log
public class TicketsController {

    @Autowired
    private final TicketsService ticketsService;

    @PostMapping("/save")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        log.info("Handling save ticket: " + ticket);
        return ticketsService.saveTicket(ticket);
    }

    @PostMapping("/saveAll")
    public List<Ticket> saveTickets(@RequestBody List<Ticket> ticketList) {
        log.info("Handling save ticket: " + ticketList);
        return ticketsService.saveTickets(ticketList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable TicketId id) {
        log.info("Handling delete ticket request: " + id);
        ticketsService.deleteTicketById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById")
    public Optional<Ticket> findTicketByTicketId(@RequestParam TicketId id) {
        log.info("Handling find by id ticket request: " + id);
        return ticketsService.findTicketByTicketId(id);
    }

    @GetMapping("/findAll")
    public List<Ticket> findAllTicket() {
        log.info("Handling find all tickets request");
        return ticketsService.findTicketsAll();
    }

    @GetMapping("/findTicketsByTicketIdIdScreeningAndStatus")
    public List<Ticket> findTicketsByScreeningIdAndValid(@RequestParam Integer screeningId) {
        return ticketsService.findTicketsByTicketIdIdScreeningAndStatus(screeningId, TicketStatus.NOT_OVERDUE);
    }



}
