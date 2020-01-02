package com.adiwisista.primeface.playground;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class TestView implements Serializable {

    private String welcome;

    @PostConstruct
    public void init() {
        welcome = "Welcome to PrimeFaces!!!";
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
}
