package be.tftic.java.spring.bll;

import be.tftic.java.spring.domain.models.Task;

import java.util.List;

public interface CrudService<T, ID> {

    T getOne(ID id);
    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(ID id);

}
