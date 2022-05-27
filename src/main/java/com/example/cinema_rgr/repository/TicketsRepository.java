package com.example.cinema_rgr.repository;

import com.example.cinema_rgr.entity.Ticket;
import com.example.cinema_rgr.entity.composite.TicketId;
import com.example.cinema_rgr.entity.statuses.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TicketsRepository extends JpaRepository<Ticket, TicketId> {

    void deleteTicketByTicketId(TicketId ticketId);

    List<Ticket> findTicketsByTicketIdIdScreeningAndStatus(Integer ticketId_idScreening, TicketStatus status);

    @Modifying
    @Query(nativeQuery = true, value = "update tickets " +
            "set status = 'OVERDUE' " +
            "from screening " +
            "where id_screening = screening.id and " +
            "now()>= screening.end_screening")
    void checkStatusTicket();
}
