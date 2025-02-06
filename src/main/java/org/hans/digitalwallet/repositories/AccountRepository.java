package org.hans.digitalwallet.repositories;

import org.hans.digitalwallet.models.Account;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class AccountRepository {

    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    private AccountRepository () {
        accounts.put("512", new Account("512", "doe", "123", 10.0));
        accounts.put("408", new Account("408", "juan", "123", 10.0));
    }

    private static class Holder {
        private static final AccountRepository INSTANCE = new AccountRepository();
    }

    public static AccountRepository getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<Account> getAccountByUUID (String countNumber) {
        return Optional.of(accounts.get(countNumber));
    }
}
