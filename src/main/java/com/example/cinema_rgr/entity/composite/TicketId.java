package com.example.cinema_rgr.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketId implements Serializable {

    @Column(name = "id_screening")
    private Integer idScreening;

    @Column(name = "row")
    private Short row;

    @Column(name = "seat")
    private Short seat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId ticketId = (TicketId) o;
        return idScreening.equals(ticketId.idScreening) && row.equals(ticketId.row) && seat.equals(ticketId.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idScreening, row, seat);
    }
}

