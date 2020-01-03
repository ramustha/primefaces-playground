package com.adiwisista.primeface.playground;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class HistoryService {
    private List<History> histories;

    @PostConstruct
    public void init() {
        histories = new ArrayList<>();
    }

    public List<History> getHistories() {
        return histories;
    }

    public boolean addHistory(History history) {
        return histories.add(history);
    }
}
