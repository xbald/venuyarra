package com.venuyarra.aqa.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@XmlRootElement(name = "parameter")
@XmlType(name = "parameter", propOrder = {"id", "locatorType", "locatorValue"})
@XmlAccessorType(XmlAccessType.NONE)
public class Parameter {
    private Long id;
    private String locatorType;
    private String locatorValue;

    public Parameter() {
    }

    public Parameter(Long id, String locatorType, String locatorValue) {
        this.id = id;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "locatorType")
    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    @XmlElement(name = "locatorValue")
    public String getLocatorValue() {
        return locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "locatorType='" + locatorType + '\'' +
                ", locatorValue='" + locatorValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;

        Parameter parameter = (Parameter) o;

        if (!getId().equals(parameter.getId())) return false;
        if (!getLocatorType().equals(parameter.getLocatorType())) return false;
        return getLocatorValue().equals(parameter.getLocatorValue());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getLocatorType().hashCode();
        result = 31 * result + getLocatorValue().hashCode();
        return result;
    }
}
