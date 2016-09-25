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

    @Override
    public String toString() {
        return "SelectCommand{" +
                "value='" + value + '\'' +
                ", " +
                super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectCommand)) return false;
        if (!super.equals(o)) return false;

        SelectCommand that = (SelectCommand) o;

        return getValue().equals(that.getValue());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }
}
