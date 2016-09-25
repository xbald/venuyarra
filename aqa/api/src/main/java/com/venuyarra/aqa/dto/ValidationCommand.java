package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.executor.SeleniumCommandExecutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "validationcommand")
@XmlType(name = "validationcommand", propOrder = {"expectedResult", "attribute"})
@XmlAccessorType(XmlAccessType.NONE)
public class ValidationCommand extends SeleniumCommand {
    private String expectedResult;

    private String attribute;

    @XmlElement(name = "attribute", required = true, nillable = true)
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @XmlElement(name = "expected")
    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public ClientResponse execute(SeleniumCommandExecutor executor) {
        return executor.execute(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationCommand)) return false;
        if (!super.equals(o)) return false;

        ValidationCommand that = (ValidationCommand) o;

        return getExpectedResult().equals(that.getExpectedResult());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getExpectedResult().hashCode();
        return result;
    }
}
