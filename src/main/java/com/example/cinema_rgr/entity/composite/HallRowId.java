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
public class HallRowId implements Serializable {

    @Column(name = "id_hall")
    private Short idHall;

    @Column(name = "number")
    private Short number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HallRowId hallRowId = (HallRowId) o;
        return idHall.equals(hallRowId.idHall) && number.equals(hallRowId.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHall, number);
    }
}
