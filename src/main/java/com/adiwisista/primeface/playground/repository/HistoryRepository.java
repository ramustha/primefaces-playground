package com.adiwisista.primeface.playground.repository;

import com.adiwisista.primeface.playground.History;
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
public class HistoryRepository {
    @Inject
    private RepoConfig repoConfig;
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = repoConfig.getSessionFactory();
    }

    public void save(History history) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(history);
        session.getTransaction().commit();
        session.close();
    }

    public List<History> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<History> criteria = builder.createQuery(History.class);
        criteria.from(History.class);
        List<History> result = session.createQuery(criteria).getResultList();
        session.close();
        return result;
    }
}
