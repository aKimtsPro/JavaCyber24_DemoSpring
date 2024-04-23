package be.tftic.java.spring.api.mappers;

import be.tftic.java.spring.api.models.dtos.CarDTO;
import be.tftic.java.spring.domain.models.entities.Car;

public abstract class StaticCarMapper {

    public static CarDTO toDto(Car entity){
        if( entity == null )
            return null;

        return new CarDTO(
                entity.getBrand(),
                entity.getModel(),
                entity.getMileage()
        );
    }

}
