package com.prototype.ProjectArthur.services;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class EntifyDTOMapper {

    public static <E, D> D convertToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            Field[] entityFields = entity.getClass().getDeclaredFields();
            Field[] dtoFields = dtoClass.getDeclaredFields();

            List<String> entityFieldNames = Arrays.stream(entityFields)
                    .map(Field::getName)
                    .collect(Collectors.toList());

            for (Field dtoField : dtoFields) {
                String fieldName = dtoField.getName();
                if (entityFieldNames.contains(fieldName)) {
                    Field entityField = entity.getClass().getDeclaredField(fieldName);
                    entityField.setAccessible(true);
                    dtoField.setAccessible(true);
                    dtoField.set(dto, entityField.get(entity));
                }
            }

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
