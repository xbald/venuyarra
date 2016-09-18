package com.venuyarra.aqa.dto;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public enum BrowserType {
    IE("ie"),
    FIREFOX("firefox"),
    CHROME("googlechrome");
    private final String value;

    BrowserType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
