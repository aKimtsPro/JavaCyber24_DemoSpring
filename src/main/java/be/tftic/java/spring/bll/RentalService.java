package be.tftic.java.spring.bll;

import be.tftic.java.spring.domain.models.entities.Rental;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalService extends CrudService<Rental, Long> {

    List<Rental> getOngoingRentals();
    List<Rental> getFromUser(String username);
    List<Rental> getBetween(LocalDateTime start, LocalDateTime end);

}
