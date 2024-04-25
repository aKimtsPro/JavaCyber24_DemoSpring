package be.tftic.java.spring.dal.repositories;

import be.tftic.java.spring.domain.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT u
        FROM User u
        WHERE u.name = :name
    """)
    Optional<User> findByName(@Param("name") String username);

}
