package be.tftic.java.spring.api.mappers;

import be.tftic.java.spring.api.models.dtos.CarDTO;
import be.tftic.java.spring.domain.models.entities.Car;
import org.springframework.stereotype.Component;

/**
 * Il existe beaucoup de facon de placer les méthodes de mapping dans une application.
 */
@Component
public class CarMapper {

    public CarDTO toDTO(Car entity){
        if( entity == null )
            return null;

        return new CarDTO(
                entity.getBrand(),
                entity.getModel(),
                entity.getMileage()
        );
    }

}
