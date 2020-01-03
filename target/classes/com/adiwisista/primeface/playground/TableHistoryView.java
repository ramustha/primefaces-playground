package com.adiwisista.primeface.playground;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TableHistoryView implements Serializable {

    @Inject
    private HistoryService historyService;

    public List<History> getHistories() {
        return historyService.getHistories();
    }
}
