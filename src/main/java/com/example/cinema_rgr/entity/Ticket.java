package com.example.cinema_rgr.entity;

import com.example.cinema_rgr.entity.composite.TicketId;
import com.example.cinema_rgr.entity.statuses.TicketStatus;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket implements Serializable {

    @EmbeddedId
    private TicketId ticketId;

    @Column(name = "price")
    private Short price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private TicketStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId.equals(ticket.ticketId) && price.equals(ticket.price) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, price, status);
    }
}
