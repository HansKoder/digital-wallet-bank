package org.hans.digitalwallet;

import org.hans.digitalwallet.models.Account;
import org.hans.digitalwallet.repositories.AccountRepository;
import org.hans.digitalwallet.services.TransferFundService;

import java.util.Optional;

public class TransferFundSystem {

    public static void main(String[] args) {
        Optional<Account> accountDoe = AccountRepository.getInstance().getAccountByUUID("512");
        Optional<Account> accountJuan = AccountRepository.getInstance().getAccountByUUID("408");

        if (accountDoe.isEmpty() || accountJuan.isEmpty()) return;

        TransferFundService service = new TransferFundService();

        Thread transferToJuan = new Thread(() -> service.transferFunds(accountDoe.get(), accountJuan.get(), 5));
        Thread transferToDoe = new Thread(() -> service.transferFunds(accountJuan.get(), accountDoe.get(), 5));

        transferToDoe.start();
        transferToJuan.start();
    }

}
