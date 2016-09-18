package com.venuyarra.aqa.dto;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public class TestTask {
    private Long id;
    private String clientId;
    private BrowserType browserType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
    }
}
