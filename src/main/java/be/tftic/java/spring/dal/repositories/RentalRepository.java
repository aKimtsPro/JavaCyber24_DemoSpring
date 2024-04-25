package be.tftic.java.spring.dal.repositories;

import be.tftic.java.spring.domain.models.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalRepository extends BullshitRepository<Rental, Long> {

    @Query("""
        SELECT r
        FROM Rental r
        WHERE
            r.returnDate == null AND
            :datetime BETWEEN r.startDate AND r.endDate
    """)
    List<Rental> findOngoing(LocalDateTime datetime);

//    @Query("""
//    SELECT u.rentals
//    FROM User u
//    WHERE u.name like :username
//    """)
    @Query("""
    SELECT r
    FROM Rental r
    WHERE r.renter.name = :username
    """)
    List<Rental> findAllFromUser(String username);

    @Query("""
    SELECT r
    FROM Rental r
    WHERE
        :start < r.startDate AND :end > r.endDate
    """)
    List<Rental> findAllBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
    SELECT r
    FROM Rental r
    WHERE
        r.endDate >= :start AND r.startDate <= :end
    """)
    List<Rental> findAllConflicts(LocalDateTime start, LocalDateTime end);
}
