package com.iesvi.shared.domain.err;

public class EntityExist extends DomainError {
    public EntityExist(String entityName, Object id) {
        super(entityName + "_exist", String.format("The " + entityName + ".id=<%s> exist", id));
    }
}