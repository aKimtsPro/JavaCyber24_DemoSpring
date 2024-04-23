package be.tftic.java.spring.bll.impl;

import be.tftic.java.spring.bll.CarService;
import be.tftic.java.spring.bll.exceptions.EntityAlreadyExistsException;
import be.tftic.java.spring.bll.exceptions.EntityNotFoundException;
import be.tftic.java.spring.dal.repositories.CarRepository;
import be.tftic.java.spring.domain.models.entities.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car getOne(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        return optCar.orElseThrow(
                () -> new EntityNotFoundException(Car.class, "id", id)
        );
    }

    @Override
    public void create(Car entity) {
        if( carRepository.existsById(entity.getId()) )
            throw new EntityAlreadyExistsException(entity);

        carRepository.save( entity );
    }

    @Override
    public void update(Car entity) {
        if( !carRepository.existsById(entity.getId()) )
            throw new EntityNotFoundException(Car.class, "id", entity.getId());

        carRepository.save( entity );
        // On ne veut pas que 'brand' et 'model' puisse être modifié
        // Autre solution => voire updatable dans @Column des variables
//        Car toUpdate = this.getOne(entity.getId());
//
//        toUpdate.setDoors(entity.getDoors());
//        toUpdate.setColor(entity.getColor());
//        toUpdate.setMileage(entity.getMileage());
//        toUpdate.setPower(entity.getPower());
//        toUpdate.setDailyRate(entity.getDailyRate());
//
//        carRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
//        if( !carRepository.existsById(id) )
//            throw new EntityNotFoundException(Car.class, "id", id);
//        carRepository.deleteById(id);

        Car toRemove = this.getOne(id);
        carRepository.delete(toRemove);
    }
}
