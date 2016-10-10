package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.executor.SeleniumCommandExecutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 10.10.2016.
 */
@XmlRootElement(name = "doubleclickcommand")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "doubleclickcommand")
public class DoubleClickCommand extends SeleniumCommand {
    @Override
    public ClientResponse execute(SeleniumCommandExecutor executor) {
        return executor.execute(this);
    }

    @Override
    public String toString() {
        return "DoubleClickCommand{ " + super.toString();
    }
}

