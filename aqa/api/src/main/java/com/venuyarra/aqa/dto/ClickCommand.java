package com.venuyarra.aqa.dto;


import com.venuyarra.aqa.executor.SeleniumCommandExecutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "click_command")
@XmlAccessorType(XmlAccessType.NONE)
public class ClickCommand extends SeleniumCommand {
    @Override
    public ClientResponse execute(SeleniumCommandExecutor executor) {
        return executor.execute(this);
    }
}
