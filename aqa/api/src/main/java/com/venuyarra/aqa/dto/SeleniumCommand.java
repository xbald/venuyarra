package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.executor.SeleniumExecutable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"id", "locatorType", "locatorValue"})
@XmlSeeAlso({ClickCommand.class, EnterCommand.class, ValidationCommand.class, SelectCommand.class})
public abstract class SeleniumCommand implements SeleniumExecutable {
    private Long id;
    private Long parameter;
    private String locatorType;
    private String locatorValue;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public Long getParameter() {
        return parameter;
    }

    public void setParameter(Long parameter) {
        this.parameter = parameter;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    @XmlElement(name = "locatorType")
    public String getLocatorType() {
        return locatorType;
    }

    @XmlElement(name = "locatorValue")
    public String getLocatorValue() {
        return locatorValue;
    }

}
