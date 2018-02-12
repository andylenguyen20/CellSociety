package cellsociety_team07;

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
