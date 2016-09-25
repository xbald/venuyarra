package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.executor.SeleniumExecutable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"id", "locatorList"})
@XmlSeeAlso({ClickCommand.class, EnterCommand.class, ValidationCommand.class, SelectCommand.class})
public abstract class SeleniumCommand implements SeleniumExecutable {
    private Long id;
    private Long parameter;
    private List<Parameter> locatorList;

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

    @XmlElementWrapper(name = "locators")
    @XmlElement(name = "locator")
    public List<Parameter> getLocatorList() {
        return locatorList;
    }

    public void setLocatorList(List<Parameter> locatorList) {
        this.locatorList = locatorList;
    }

    @Override
    public String toString() {
        final String locators =
                locatorList.stream().map(Parameter::toString).collect(Collectors.joining(","));
        return "id=" + id +
                ", locators=" + locators +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeleniumCommand)) return false;

        SeleniumCommand that = (SeleniumCommand) o;

        if (!getId().equals(that.getId())) return false;
        if (getParameter() != null ? !getParameter().equals(that.getParameter()) : that.getParameter() != null)
            return false;
        return getLocatorList().equals(that.getLocatorList());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getParameter() != null ? getParameter().hashCode() : 0);
        result = 31 * result + getLocatorList().hashCode();
        return result;
    }
}
