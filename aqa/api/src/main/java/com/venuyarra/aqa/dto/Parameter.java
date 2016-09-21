package com.venuyarra.aqa.dto;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public class Parameter {
    private Long id;
    private String locatorType;
    private String locatorValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }
}
