package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.model.KeyedObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAutoConverter<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> implements ObjectConverter<DTO, DB, ID> {

    protected abstract DTO getNewDtoObject();

    protected abstract DB getNewDbObject();

    @SuppressWarnings("unchecked")
    @Override
    public DTO convertToDto(DB dbEntity) {
        if (dbEntity == null) {
            return null;
        }

        DTO dto = getNewDtoObject();
        dto.setId(dbEntity.getId());

        Class<? extends KeyedObject<ID>> dtoClass = (Class<? extends KeyedObject<ID>>) dto.getClass();

        return dto;
    }

    @Override
    public DB convertToDb(DTO dtoEntity) {
        return null;
    }

    private List<Field> getClassFields(Class<?> baseClass) {
        Class<?> currentClass = baseClass;
        List<Field> completeClassFields = new LinkedList<>();
        while (!currentClass.equals(Object.class)) {
            List<Field> classFields = Arrays.stream(currentClass.getDeclaredFields()).filter(field -> !field.isSynthetic()).collect(Collectors.toList());
            completeClassFields.addAll(classFields);
            currentClass = currentClass.getSuperclass();
        }
        return completeClassFields;
    }
}
