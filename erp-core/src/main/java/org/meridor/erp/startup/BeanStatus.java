package org.meridor.erp.startup;

public class BeanStatus {

    private final String message;

    private final double progress;

    public BeanStatus(String message, double progress) {
        this.message = message;
        this.progress = progress;
    }

    public String getMessage() {
        return message;
    }

    public double getProgress() {
        return progress;
    }
}
