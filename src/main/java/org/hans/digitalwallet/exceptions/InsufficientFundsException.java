package org.hans.digitalwallet.exceptions;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(String message) {
        super(message);
    }
}
