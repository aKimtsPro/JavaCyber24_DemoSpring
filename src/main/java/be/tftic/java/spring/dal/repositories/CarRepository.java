package be.tftic.java.spring.dal.repositories;

import be.tftic.java.spring.domain.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
