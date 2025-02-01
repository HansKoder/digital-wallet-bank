package org.hans.digitalwallet;

import org.hans.digitalwallet.repositories.AccountRepository;

public class Main {

    private static void processTransactions () {
        DigitalWallet digitalWallet = new DigitalWallet(2500);

        Thread transactionPaymentCourse = new Thread(new Transaction(digitalWallet, 100, Transaction.Operation.DEPOSIT));
        Thread transactionCoachEnglish = new Thread(new Transaction(digitalWallet, 100, Transaction.Operation.DEPOSIT));
        Thread transactionJobSalary = new Thread(new Transaction(digitalWallet, 800, Transaction.Operation.DEPOSIT));
        Thread transactionBuyGroceries = new Thread(new Transaction(digitalWallet, 500, Transaction.Operation.WITHDRAW));
        Thread transactionBuyTaxes = new Thread(new Transaction(digitalWallet, 500, Transaction.Operation.WITHDRAW));

        transactionPaymentCourse.start();
        transactionBuyTaxes.start();
        transactionBuyGroceries.start();
        transactionJobSalary.start();
        transactionCoachEnglish.start();

        try {
            transactionPaymentCourse.join();
            transactionBuyTaxes.join();
            transactionBuyGroceries.join();
            transactionJobSalary.join();
            transactionCoachEnglish.join();
        } catch (InterruptedException interruptedException) {
            System.err.println(interruptedException.getMessage());
        }

        System.out.println("Get Balance end " + digitalWallet.getBalance());
    }

    private static void validateSingletonThreadSafe () {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                AccountRepository repo = AccountRepository.getInstance();
                System.out.println("Hash " + repo.hashCode());
            }).start();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) processTransactions();

        validateSingletonThreadSafe();
    }

}
