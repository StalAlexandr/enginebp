package ru.alexandrstal.enginebp.exception;

public class BPMNEngineException extends RuntimeException{
    public BPMNEngineException() {
    }

    public BPMNEngineException(String s) {
        super(s);
    }

    public BPMNEngineException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BPMNEngineException(Throwable throwable) {
        super(throwable);
    }
}
