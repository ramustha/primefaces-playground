package com.adiwisista.primeface.playground;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class FacesUtil {
    private FacesUtil() {
    }

    public static void addMessage(String message, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(severity, message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
