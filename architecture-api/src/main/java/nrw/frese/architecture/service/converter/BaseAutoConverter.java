package nrw.frese.architecture.service.converter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nrw.frese.architecture.model.KeyedObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseAutoConverter<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> implements ObjectConverter<DTO, DB, ID> {

    protected abstract DTO getNewDtoObject();

    protected abstract DB getNewDbObject();

    @Override
    public DTO convertToDto(DB dbEntity) {
        if (dbEntity == null) {
            return null;
        }

        DTO dto = getNewDtoObject();
        dto.setId(dbEntity.getId());


        return convert(dbEntity, dto);
    }

    @Override
    public DB convertToDb(DTO dtoEntity) {
        if (dtoEntity == null) {
            return null;
        }

        DB db = getNewDbObject();
        db.setId(dtoEntity.getId());

        return convert(dtoEntity, db);
    }

    private <S, T> T convert(S sourceObject, T targetObject) {
        List<Field> dbFields = getClassFields(sourceObject.getClass());
        List<Field> dtoFields = getClassFields(targetObject.getClass());

        for (Field dbField : dbFields) {
            if (!dbField.getName().equals("id")) {
                Field dtoField = findMatchingField(dtoFields, dbField);
                if (dtoField != null) {
                    Method getMethod = findMethod(dbField, sourceObject.getClass(), MethodType.GET);
                    Method setMethod = findMethod(dbField, targetObject.getClass(), MethodType.SET);
                    if (getMethod != null && setMethod != null) {
                        try {
                            setMethod.invoke(targetObject, getMethod.invoke(sourceObject));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            log.error(String.format("An exception occurred during auto-converting object of type %s to %s - %s", sourceObject.getClass().getName(), targetObject.getClass().getName(), e.getLocalizedMessage()), e);
                        }
                    }
                } else {
                    log.warn("No matching field could be found on type {} for the field {} of type {} contained in {}.",
                            targetObject.getClass().getName(), dbField.getName(), dbField.getType().getName(), sourceObject.getClass().getName());
                }
            }
        }
        return targetObject;
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

    private List<Method> getClassMethods(Class<?> baseClass) {
        Class<?> currentClass = baseClass;
        List<Method> completeClassMethods = new LinkedList<>();
        while (!currentClass.equals(Object.class)) {
            completeClassMethods.addAll(Arrays.asList(currentClass.getDeclaredMethods()));
            currentClass = currentClass.getSuperclass();
        }
        return completeClassMethods;
    }

    private Field findMatchingField(List<Field> fields, Field targetField) {
        return fields.stream().filter(field -> field.getName().equals(targetField.getName()) && targetField.getType().isAssignableFrom(field.getType())).findFirst().orElse(null);
    }

    private Method findMethod(Field field, Class<?> fieldClass, MethodType methodType) {
        Method method = null;
        switch (methodType) {
            case GET:
                method = findGetterMethod(field, fieldClass);
                break;
            case SET:
                method = findSetterMethod(field, fieldClass);
        }

        if (method == null) {
           log.warn("The method {} could not be found on type {}", getMethodName(field, methodType), fieldClass.getName());
        }

        return method;
    }

    private Method findGetterMethod(Field field, Class<?> fieldClass) {
        return getClassMethods(fieldClass).stream().filter(method -> method.getName().equals(getMethodName(field, MethodType.GET)) && method.getReturnType().equals(field.getType())).findFirst().orElse(null);
    }

    private Method findSetterMethod(Field field, Class<?> fieldClass) {
        return getClassMethods(fieldClass).stream().filter(method -> method.getName().equals(getMethodName(field, MethodType.SET)) && method.getParameterCount() == 1 && method.getParameters()[0].getType().isAssignableFrom(field.getType())).findFirst().orElse(null);
    }

    private String getMethodName(Field field, MethodType methodType) {
        String fieldName = field.getName();
        return methodType.prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public enum MethodType {
        GET("get"), SET("set");

        @Getter
        private String prefix;

        MethodType(String prefix) {
            this.prefix = prefix;
        }
    }
}
