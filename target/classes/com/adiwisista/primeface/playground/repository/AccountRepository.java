package com.adiwisista.primeface.playground.repository;

import com.adiwisista.primeface.playground.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Named
@RequestScoped
public class AccountRepository {
    @Inject
    private RepoConfig repoConfig;
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = repoConfig.getSessionFactory();
    }

    public void save(Account account) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(account);
        session.getTransaction().commit();
        session.close();
    }

    public List<Account> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
        criteria.from(Account.class);
        List<Account> result = session.createQuery(criteria).getResultList();
        session.close();
        return result;
    }
}
