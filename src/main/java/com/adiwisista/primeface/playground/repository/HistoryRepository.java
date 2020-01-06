package com.adiwisista.primeface.playground.repository;

import com.adiwisista.primeface.playground.History;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class HistoryRepository extends AbstractRepository<History> {

    public void save(History history) {
        saveOrUpdate(history);
    }

    public List<History> findAll() {
        return findAll(History.class);
    }
}
