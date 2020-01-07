package nrw.frese.architecture.configuration;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;
import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.converter.ObjectConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class ConverterConfiguration {

    private static ConverterConfiguration me;

    public static ConverterConfiguration getCurrentInstance() {
        if (me == null) {
            throw new NoConfigurationActiveException();
        }
        return me;
    }

    private Map<Class<? extends KeyedObject<?>>, ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?>> resolvedTransitionObjectConverters;

    private Map<Class<? extends KeyedObject<?>>, ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?>> resolvedDatabaseObjectConverters;

    private List<ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?>> unresolvedObjectConverters;

    @SuppressWarnings("rawtypes")
    private ConverterConfiguration(String... basePackages) {
        resolvedTransitionObjectConverters = new HashMap<>();
        resolvedDatabaseObjectConverters = new HashMap<>();
        unresolvedObjectConverters = new LinkedList<>();

        try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages(basePackages)
                .scan()) {
            ClassInfoList objectConverterInfoList = scanResult.getSubclasses("nrw.frese.architecture.service.converter.ObjectConverter");
            for (Class<? extends ObjectConverter> converterClass : objectConverterInfoList.loadClasses(ObjectConverter.class)) {

                ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?> converter = null;
                try {
                    converter = initializeConverter(converterClass);
                } catch (Exception e) {
                    throw new ConverterInitializationException(converterClass, e);
                }
                Class<? extends KeyedObject<?>> transitionObjectClass = resolveConverterTransitObject(converterClass);
                if (transitionObjectClass != null) {
                    if (resolvedTransitionObjectConverters.get(transitionObjectClass) != null) {
                        throw new DuplicatedConverterException(transitionObjectClass, converterClass, resolvedTransitionObjectConverters.get(transitionObjectClass).getClass());
                    }
                    resolvedTransitionObjectConverters.put(transitionObjectClass, converter);
                    Class<? extends KeyedObject<?>> databaseObjectClass = resolveConverterDatabaseObject(converterClass);
                    if (resolvedDatabaseObjectConverters.get(databaseObjectClass) != null) {
                        throw new DuplicatedConverterException(databaseObjectClass, converterClass, resolvedDatabaseObjectConverters.get(transitionObjectClass).getClass());
                    }
                    resolvedDatabaseObjectConverters.put(databaseObjectClass, converter);
                } else {
                    unresolvedObjectConverters.add(converter);
                }
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private ObjectConverter<? extends KeyedObject<?>, ? extends KeyedObject<?>, ?> initializeConverter(Class<? extends ObjectConverter> converterClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return converterClass.getConstructor().newInstance();
    }

    @SuppressWarnings("rawtypes")
    private Class<? extends KeyedObject<?>> resolveConverterTransitObject(Class<? extends ObjectConverter> converterClass) {
        Converter converter = converterClass.getAnnotation(Converter.class);
        if (converter != null) {
            return converter.dtoType();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    private Class<? extends KeyedObject<?>> resolveConverterDatabaseObject(Class<? extends ObjectConverter> converterClass) {
        return converterClass.getAnnotation(Converter.class).dbType();
    }

    public static void configure(String... basePackages) {
        if (me != null) {
            throw new ConverterConfigurationDuplicationAttemptException();
        }
        me = new ConverterConfiguration(basePackages);
    }

    @SuppressWarnings("unchecked")
    public <DTO extends KeyedObject<ID>, ID> ObjectConverter<DTO, ? extends KeyedObject<ID>, ID> getConverterByTransitionObject(DTO transitionObject) {
        return (ObjectConverter<DTO, ? extends KeyedObject<ID>, ID>) getCurrentInstance().resolvedTransitionObjectConverters.get(transitionObject);
    }

    @SuppressWarnings("unchecked")
    public <DB extends KeyedObject<ID>, ID> ObjectConverter<? extends KeyedObject<ID>, DB, ID> getConverterByDatabaseObject(DB transitionObject) {
        return (ObjectConverter<? extends KeyedObject<ID>, DB, ID>) getCurrentInstance().resolvedDatabaseObjectConverters.get(transitionObject);
    }

}
