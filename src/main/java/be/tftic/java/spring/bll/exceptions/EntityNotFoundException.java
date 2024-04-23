package be.tftic.java.spring.bll.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final Class<?> entityClass;
    private final String searchBy;
    private final Object searchValue;

    public EntityNotFoundException(Class<?> entityClass, String searchBy, Object searchValue) {
        super("Entity '%s' not found by '%s' with value '%s'".formatted(entityClass.getName(), searchBy, searchValue));
        this.entityClass = entityClass;
        this.searchBy = searchBy;
        this.searchValue = searchValue;
    }

}
