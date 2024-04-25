package be.tftic.java.spring.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BullshitRepository<T, ID extends Number> extends JpaRepository<T, ID> {

    int countByIdLessThanEqual(ID id);

}
