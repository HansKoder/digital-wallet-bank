package org.hans.digitalwallet;

import org.hans.digitalwallet.services.FraudDetectorService;

public class FraudDetectionSystem {

    public static void main(String[] args) {
        FraudDetectorService fraudDetector = new FraudDetectorService();

        Thread monitoringThread = new Thread(fraudDetector::monitorTransactions);
        Thread monitoringThread2 = new Thread(fraudDetector::monitorTransactions);
        Thread monitoringThread3 = new Thread(fraudDetector::monitorTransactions);

        Thread fraudTriggerThread = new Thread(() -> {
            try {
                Thread.sleep(3000); // Simulamos que el fraude se detecta después de 3 segundos
                fraudDetector.flagFraud();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        monitoringThread.start();
        monitoringThread2.start();
        monitoringThread3.start();
        fraudTriggerThread.start();
    }
}
