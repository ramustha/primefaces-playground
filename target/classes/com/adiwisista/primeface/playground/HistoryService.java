package com.adiwisista.primeface.playground;

import com.adiwisista.primeface.playground.repository.HistoryRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class HistoryService {
    private List<History> histories;

    @Inject
    private HistoryRepository historyRepository;

    @PostConstruct
    public void init() {
        histories = new ArrayList<>();

        histories.addAll(historyRepository.findAll());
    }

    public List<History> getHistories() {
        return histories;
    }

    public boolean addHistory(History history) {
        historyRepository.save(history);
        return histories.add(history);
    }
}
