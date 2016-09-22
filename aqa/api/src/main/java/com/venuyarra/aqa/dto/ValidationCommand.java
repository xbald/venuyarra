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
@XmlType(name = "validationcommand", propOrder = {"expectedResult"})
@XmlAccessorType(XmlAccessType.NONE)
public class ValidationCommand extends SeleniumCommand {
    private String expectedResult;

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
}
