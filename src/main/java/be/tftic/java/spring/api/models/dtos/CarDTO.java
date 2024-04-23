package be.tftic.java.spring.api.models.dtos;

import be.tftic.java.spring.domain.models.entities.Car;

public record CarDTO (
        String brand,
        String model,
        int mileage
) {

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
