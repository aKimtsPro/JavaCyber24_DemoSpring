package be.tftic.java.spring.domain.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false)
    private Long id;

    @Column(name = "car_brand", nullable = false, updatable = false)
    private String brand;

    @Column(name = "car_model", nullable = false, updatable = false)
    private String model;

    @Column(name = "car_mileage", nullable = false)
    private int mileage;

    @Column(name = "car_color", nullable = false)
    private String color;

    @Column(name = "car_doors", nullable = false)
    private int doors;

    @Column(name = "car_power", nullable = false)
    private int power;

    @Column(name = "car_daily_rate", nullable = false)
    private double dailyRate;

}
