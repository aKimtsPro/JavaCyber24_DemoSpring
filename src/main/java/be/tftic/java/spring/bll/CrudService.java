package be.tftic.java.spring.bll;

import be.tftic.java.spring.domain.models.Task;

import java.util.List;

public interface CrudService<T, ID> {

    T getOne(ID id);
    List<T> getAll();

    T create(T entity);

    T update(T entity);

    T delete(ID id);

}
