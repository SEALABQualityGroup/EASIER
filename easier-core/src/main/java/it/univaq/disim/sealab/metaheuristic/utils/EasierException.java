package it.univaq.disim.sealab.metaheuristic.utils;

public class EasierException extends Exception {

     public EasierException(String message) {
        super(message);
    }

    public EasierException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasierException(Throwable cause) {
        super(cause);
    }

    protected EasierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
