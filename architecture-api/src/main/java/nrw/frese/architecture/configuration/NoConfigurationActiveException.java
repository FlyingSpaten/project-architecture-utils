package nrw.frese.architecture.configuration;

public class NoConfigurationActiveException extends RuntimeException {
    public NoConfigurationActiveException() {
        super("No active converter-configuration was found. Use ConverterConfiguration.configure(Stirng... basePackages) to configure.");
    }
}
