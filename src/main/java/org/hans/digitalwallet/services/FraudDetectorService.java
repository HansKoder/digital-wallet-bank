package org.hans.digitalwallet.services;

public class FraudDetectorService {

    private volatile boolean fraudDetected = false;

    public void monitorTransactions()  {
        System.out.println("Start with the monitor for detecting fraud");
        while (!fraudDetected) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Operation is safe");
        }

        System.out.println("Take new actions about this fraud");
    }

    public void flagFraud() {
        System.out.println("🚨 Setting fraud alert");
        fraudDetected = true;
    }

}
