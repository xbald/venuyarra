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
@XmlRootElement(name = "entercommand")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "entercommand", propOrder = {"value"})
public class EnterCommand extends SeleniumCommand {
    private String value;

    @Override
    public ClientResponse execute(SeleniumCommandExecutor executor) {
        return executor.execute(this);
    }

    @XmlElement(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EnterCommand{" +
                "value='" + value + '\'' +
                ", " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterCommand)) return false;
        if (!super.equals(o)) return false;

        EnterCommand that = (EnterCommand) o;

        return getValue().equals(that.getValue());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }
}
