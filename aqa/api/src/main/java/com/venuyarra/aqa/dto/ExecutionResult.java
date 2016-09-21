package com.venuyarra.aqa.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlType(name = "result")
@XmlEnum
@XmlAccessorType(XmlAccessType.NONE)
public enum ExecutionResult {
    FAILED("Failed"),
    PASSED("Passed");
    private final String value;

    ExecutionResult(String value) {
        this.value = value;
    }

    @Override
    @XmlElement(name = "value")
    public String toString() {
        return value;
    }
}
