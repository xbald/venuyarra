package com.venuyarra.aqa.dto;


import com.venuyarra.aqa.executor.SeleniumCommandExecutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "clickcommand")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "clickcommand")
public class ClickCommand extends SeleniumCommand {
    @Override
    public ClientResponse execute(SeleniumCommandExecutor executor) {
        return executor.execute(this);
    }
}
