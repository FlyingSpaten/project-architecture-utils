package nrw.frese.architecture.configuration;

public class ConverterConfigurationDuplicationAttemptException extends RuntimeException {
    public ConverterConfigurationDuplicationAttemptException() {
        super("There was an attempt to configure a ConverterConfiguration even though one is already present.");
    }
}
