package nrw.frese.architecture.configuration;

import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.converter.ObjectConverter;

public class ConverterInitializationException extends RuntimeException {
    public ConverterInitializationException(Class<? extends ObjectConverter> converterClass) {
        super(String.format("An object converter of type %s could not be initialized", converterClass.getName()));
    }

    public ConverterInitializationException(Class<? extends ObjectConverter> converterClass, Exception e) {
        super(String.format("An object converter of type %s could not be initialized. Error: %s - %s", converterClass.getName(), e.getLocalizedMessage(), e), e);
    }
}
