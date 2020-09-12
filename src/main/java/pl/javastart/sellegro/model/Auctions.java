package pl.javastart.sellegro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Auctions {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String carMake;
    private String carModel;
    private String color;
    private BigDecimal price;
    private LocalDate endDate;
}