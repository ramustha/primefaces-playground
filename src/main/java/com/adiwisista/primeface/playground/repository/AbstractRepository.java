package com.adiwisista.primeface.playground.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractRepository<T> implements Serializable {
    @Inject
    private RepoConfig repoConfig;
    protected SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = repoConfig.getSessionFactory();
    }

    protected T saveOrUpdate(T entity) {
        createTransaction(trx -> {
            trx.saveOrUpdate(entity);
            trx.getTransaction().commit();
        });
        return entity;
    }

    protected List<T> findAll(Class<T> tClass) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(tClass);
            criteria.from(tClass);
            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public void createTransaction(Consumer<Session> sessionConsumer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            sessionConsumer.accept(session);
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
