package org.hans.digitalwallet.repositories;

import org.hans.digitalwallet.entities.Account;

import java.util.*;

public final class AccountRepository {

    private final Account account = new Account("key-94552442", "jhon-doe", "8829045", 1000.0);

    private AccountRepository () {

    }

    private final Object lockCheck = new Object();
    private final Object lockUpdate = new Object();

    private static class Holder {
        private static final AccountRepository INSTANCE = new AccountRepository();
    }

    public static AccountRepository getInstance() {
        return Holder.INSTANCE;
    }


}
