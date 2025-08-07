package org.example.convertly.model;

import java.util.ArrayList;
import java.util.List;

public class ConversionSession {
    private String sessionId;
    private List<ConversionResponse> conversions = new ArrayList<>();

    public List<ConversionResponse> getConversions() {
        return conversions;
    }

    public void setConversions(List<ConversionResponse> conversions) {
        this.conversions = conversions;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
