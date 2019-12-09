package nrw.frese.architecture.configuration;

import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.converter.ObjectConverter;

public class DuplicatedConverterException extends RuntimeException{
    @SuppressWarnings("rawtypes")
    public DuplicatedConverterException(Class<? extends KeyedObject<?>> keyedClass,
                                        Class<? extends ObjectConverter> newConverter,
                                        Class<? extends ObjectConverter> existingConverterClass) {
        super(String.format("Multiple converters have been found for type %s. Existing converter was: %s. New converter was %s", keyedClass.getName(), existingConverterClass.getName(), newConverter.getName()));
    }
}
