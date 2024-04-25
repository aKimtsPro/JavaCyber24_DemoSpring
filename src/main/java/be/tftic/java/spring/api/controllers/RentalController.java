package be.tftic.java.spring.api.controllers;

import be.tftic.java.spring.api.models.dtos.RentalDTO;
import be.tftic.java.spring.bll.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalDTO>> getAll(){
        return ResponseEntity.ok(
                rentalService.getAll().stream()
                        .map(RentalDTO::toDTO)
                        .toList()
        );
    }

}
