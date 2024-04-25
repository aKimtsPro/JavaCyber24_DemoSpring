package be.tftic.java.spring.dal.repositories;

import be.tftic.java.spring.domain.models.entities.Car;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("""
    SELECT count(r.id) == 0
    FROM Car c
        JOIN Rental r ON r.rented = c
    WHERE
        (
            r.endDate < :start OR
            r.startDate > :end
        ) AND
        c.id = :carId
    """)
    boolean isCarAvailable(LocalDateTime start, LocalDateTime end, Long carId);

}
