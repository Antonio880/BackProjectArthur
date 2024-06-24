package com.prototype.ProjectArthur.services;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class EntifyDTOMapper {

    public static <E, D> D convertToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            for (Field entityField : entity.getClass().getDeclaredFields()) {
                entityField.setAccessible(true);
                Object value = entityField.get(entity);
                for (Field dtoField : dtoClass.getDeclaredFields()) {
                    if (dtoField.getName().equals(entityField.getName())) {
                        dtoField.setAccessible(true);
                        dtoField.set(dto, value);
                    }
                }
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
