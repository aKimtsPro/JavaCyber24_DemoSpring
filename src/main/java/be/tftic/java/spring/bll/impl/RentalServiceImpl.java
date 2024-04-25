package be.tftic.java.spring.bll.impl;

import be.tftic.java.spring.bll.RentalService;
import be.tftic.java.spring.bll.exceptions.EntityAlreadyExistsException;
import be.tftic.java.spring.bll.exceptions.EntityNotFoundException;
import be.tftic.java.spring.dal.repositories.CarRepository;
import be.tftic.java.spring.dal.repositories.RentalRepository;
import be.tftic.java.spring.dal.repositories.UserRepository;
import be.tftic.java.spring.domain.models.entities.Rental;
import be.tftic.java.spring.domain.models.entities.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository, CarRepository carRepository){
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<Rental> getOngoingRentals() {
        LocalDateTime now = LocalDateTime.now();
        return rentalRepository.findOngoing(now);
    }

    @Override
    public List<Rental> getFromUser(String username) {
        return userRepository.findByName(username)
                .map(User::getRentals)
                .map(ArrayList::new)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "name", username));
    }

    @Override
    public List<Rental> getBetween(LocalDateTime start, LocalDateTime end) {
        return rentalRepository.findAllBetween(start, end);
    }

    @Override
    public Rental getOne(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(Rental.class, "id", id)
                );
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental create(Rental entity) {
        if( !entity.getEndDate().isAfter(entity.getStartDate()) )
            throw new RuntimeException("invalid rental dates"); // TODO specify

        if(
                !carRepository.isCarAvailable(
                        entity.getStartDate(),
                        entity.getEndDate(),
                        entity.getRented().getId()
                )
        ){
            throw new RuntimeException("car unavailable"); // TODO specify
        }

        long days = Period.between(
                entity.getStartDate().toLocalDate(),
                entity.getEndDate().toLocalDate()
        ).getDays();
        if(days < 1) days = 1;

        double price = days * entity.getRented().getDailyRate();
        double deposit = price * .5;

        entity.setId(null);
        entity.setPrice(price);
        entity.setDeposit(deposit);
        return rentalRepository.save(entity);
    }

    @Override
    public Rental update(Rental entity) {
        if( !rentalRepository.existsById(entity.getId()) )
            throw new EntityNotFoundException(Rental.class, "id", entity.getId());

        return rentalRepository.save(entity);
    }

    @Override
    public Rental delete(Long id) {
        Rental toRemove = this.getOne(id);
        rentalRepository.delete( toRemove );
        return toRemove;
    }
}
