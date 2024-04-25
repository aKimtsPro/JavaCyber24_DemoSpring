package be.tftic.java.spring.domain.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long id;

    @Column(name = "rental_start", nullable = false, updatable = false)
    private LocalDateTime startDate;
    @Column(name = "rental_end", nullable = false, updatable = false)
    private LocalDateTime endDate;
    @Column(name = "rental_return", insertable = false)
    private LocalDateTime returnDate;
    @Column(name = "rental_price", nullable = false)
    private double price;
    @Column(name = "rental_deposit", nullable = false)
    private double deposit;

    @ManyToOne
    @JoinColumn(name = "rental_user_id", nullable = false, updatable = false)
    private User renter;

    @ManyToOne
    @JoinColumn(name = "rental_car_id", nullable = false)
    private Car rented;

}
