package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.executor.SeleniumCommandExecutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@XmlRootElement(name = "selectcommand")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "selectcommand", propOrder = {"value"})
public class SelectCommand extends SeleniumCommand {
    private String value;

    @Override
    public ClientResponse execute(SeleniumCommandExecutor executor) {
        return executor.execute(this);
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlElement(name = "value")
    public String getValue() {
        return value;
    }
}
