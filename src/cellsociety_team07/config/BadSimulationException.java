package cellsociety_team07.config;

/**
 * The purpose of this class is to to provide an unchecked Exception that gets thrown whenever
 * bad simulation events happen. e.g. trying to produce a cell of a simulation type that doesn't fit the list
 * of possible simulation types
 * @author Andy Nguyen
 *
 */
public class BadSimulationException extends RuntimeException{
	public BadSimulationException() {
        super();
    }
    public BadSimulationException(String s) {
        super(s);
    }
    public BadSimulationException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public BadSimulationException(Throwable throwable) {
        super(throwable);
    }
}
