package nrw.frese.architecture.configuration;

import nrw.frese.architecture.model.KeyedObject;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Converter {
    Class<? extends KeyedObject<?>> dtoType();
    Class<? extends KeyedObject<?>> dbType();
}
