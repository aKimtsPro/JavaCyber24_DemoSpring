package be.tftic.java.spring.api.controllers;

import be.tftic.java.spring.api.mappers.CarMapper;
import be.tftic.java.spring.api.mappers.StaticCarMapper;
import be.tftic.java.spring.api.models.dtos.CarDTO;
import be.tftic.java.spring.api.models.forms.CarCreateForm;
import be.tftic.java.spring.api.models.forms.CarUpdateForm;
import be.tftic.java.spring.bll.CarService;
import be.tftic.java.spring.domain.models.entities.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    // GET - http://localhost:8080/cars
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll(){
        List<Car> cars = carService.getAll();
        List<CarDTO> dtos = cars.stream()
                .map( CarDTO::toDto )
                .toList();

        return ResponseEntity.ok(dtos);
//        return ResponseEntity.ok(
//                carService.getAll().stream()
//                        .map(CarDTO::toDto)
//                        .toList()
//        );
    }

    // GET - http://localhost:8080/cars/1
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<CarDTO> getOne(@PathVariable long id){
        Car car = carService.getOne(id);
        CarDTO dto = CarDTO.toDto(car);
        return ResponseEntity.ok(dto);
//        return ResponseEntity.ok(
//                CarDTO.toDto(carService.getOne(id))
//        );
    }

    // POST - http://localhost:8080/cars
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CarCreateForm form){
        Car entity = form.toEntity();
        carService.create( entity );

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    // PUT - http://localhost:8080/cars/1
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody CarUpdateForm form){
        Car toUpdate = form.toEntity();
        toUpdate.setId(id);
        carService.update(toUpdate);

        return ResponseEntity.noContent()
                .build();
    }

    // DELETE - http://localhost:8080/cars/1
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        carService.delete(id);

        return ResponseEntity.noContent()
                .build();
    }

}
