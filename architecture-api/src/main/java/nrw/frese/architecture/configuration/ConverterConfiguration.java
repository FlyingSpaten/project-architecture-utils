package nrw.frese.architecture.configuration;

import lombok.extern.slf4j.Slf4j;
import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.converter.ObjectConverter;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
public class ConverterConfiguration {

    private static ConverterConfiguration me;

    public static ConverterConfiguration getCurrentInstance() {
        if (me == null) {
            throw new NoConfigurationActiveException();
        }
        return me;
    }

    private Map<Class<? extends KeyedObject<?>>, ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?>> resolvedConverters;

    private List<ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?>> unresolvedConverters;

    @SuppressWarnings("rawtypes")
    private ConverterConfiguration(String... basePackages) {
        resolvedConverters = new HashMap<>();
        unresolvedConverters = new LinkedList<>();

        for (String basePackage : basePackages) {
            Reflections reflections = new Reflections(basePackage);
            Set<Class<? extends ObjectConverter>> converterTypes = reflections.getSubTypesOf(ObjectConverter.class);
            for (Class<? extends ObjectConverter> converterClass : converterTypes) {
                ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?> converter = null;
                try {
                    converter = initializeConverter(converterClass);
                } catch (Exception e) {
                    throw new ConverterInitializationException(converterClass, e);
                }
                if (converter != null) {
                    Class<? extends KeyedObject<?>> transitionObjectClass = resolveConverterTransitObject(converterClass);
                    if (transitionObjectClass != null) {
                        if (resolvedConverters.get(transitionObjectClass) != null) {
                            throw new DuplicatedConverterException(transitionObjectClass, converterClass, resolvedConverters.get(transitionObjectClass).getClass());
                        }
                        resolvedConverters.put(transitionObjectClass, converter);
                    } else {
                        unresolvedConverters.add(converter);
                    }
                } else {
                    throw new ConverterInitializationException(converterClass);
                }
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?> initializeConverter(Class<? extends ObjectConverter> converterClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return converterClass.getConstructor().newInstance();
    }

    private Class<? extends KeyedObject<?>> resolveConverterTransitObject(Class<? extends ObjectConverter> converterClass) {
        return null;
    }

    public static void configure(String... basePackages) {
        if (me != null) {
            throw new ConverterConfigurationDuplicationAttemptException();
        }
        me = new ConverterConfiguration(basePackages);
    }

}
