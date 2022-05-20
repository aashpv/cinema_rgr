package com.example.cinema_rgr.entity;

import com.example.cinema_rgr.entity.composite.HallRowId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hall_row")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HallRow implements Serializable, Comparable<HallRow> {

    @EmbeddedId
    private HallRowId hallRowId;

    @Column(name = "capacity")
    private Short capacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HallRow hallRow = (HallRow) o;
        return hallRowId.equals(hallRow.hallRowId) && capacity.equals(hallRow.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallRowId, capacity);
    }

    @Override
    public int compareTo(HallRow o) {
        return getHallRowId().getNumber().compareTo(o.getHallRowId().getNumber());
    }
}
