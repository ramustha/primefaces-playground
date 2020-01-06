package com.adiwisista.primeface.playground.repository;

import com.adiwisista.primeface.playground.Account;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AccountRepository extends AbstractRepository<Account> {

    public void save(Account account) {
        saveOrUpdate(account);
    }

    public List<Account> findAll() {
        return findAll(Account.class);
    }
}
