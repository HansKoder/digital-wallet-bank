package org.hans.digitalwallet.utils;

public class Sleep {

    public static void delay (Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
