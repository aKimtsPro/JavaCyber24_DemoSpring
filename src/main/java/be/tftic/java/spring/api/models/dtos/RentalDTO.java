package be.tftic.java.spring.api.models.dtos;

import be.tftic.java.spring.domain.models.entities.Rental;
import be.tftic.java.spring.domain.models.entities.User;

import java.time.LocalDateTime;

public record RentalDTO(
   Long id,
   LocalDateTime startDate,
   LocalDateTime endDate,
   LocalDateTime returnDate,
   double price,
   double deposit,

   UserDTO renter,
   CarDTO rented
) {

    public static RentalDTO toDTO(Rental entity){
        if( entity == null )
            return null;

        return new RentalDTO(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getReturnDate(),
                entity.getPrice(),
                entity.getDeposit(),
                UserDTO.toDTO(entity.getRenter()),
                CarDTO.toDto(entity.getRented())
        );
    }

    public record UserDTO(
            long id,
            String name
    ){
        public static UserDTO toDTO(User entity){
            if(entity == null)
                return null;

            return new UserDTO(
                    entity.getId(),
                    entity.getName()
            );
        }
    }

}
