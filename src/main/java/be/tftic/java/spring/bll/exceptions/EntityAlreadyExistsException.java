package be.tftic.java.spring.bll.exceptions;

import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {

    private final Object entity;

    public EntityAlreadyExistsException( Object entity) {
        super("Entity of type '" +entity.getClass().getName()+ "' already exists");
        this.entity = entity;
    }

    public Class<?> getEntityType() {
        return this.entity.getClass();
    }

}
