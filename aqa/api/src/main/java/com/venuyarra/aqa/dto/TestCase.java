package com.venuyarra.aqa.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "testcase")
@XmlType(name = "testcase", propOrder = {"id", "title", "commandList"})
@XmlAccessorType(XmlAccessType.NONE)
public class TestCase {
    private Long id;
    private String title;
    private List<SeleniumCommand> commandList = new ArrayList<>();

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCommandList(List<SeleniumCommand> commandList) {
        this.commandList = commandList;
    }

    @XmlElement(name = "title", nillable = true)
    public String getTitle() {
        return title;
    }

    @XmlElementWrapper(name = "commands")
    @XmlElement(name = "command")
    public List<SeleniumCommand> getCommandList() {
        return commandList;
    }

    public void addCommand(SeleniumCommand command) {
        commandList.add(command);
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", commandList=" + commandList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCase)) return false;

        TestCase testCase = (TestCase) o;

        if (!getId().equals(testCase.getId())) return false;
        if (getTitle() != null ? !getTitle().equals(testCase.getTitle()) : testCase.getTitle() != null) return false;
        return getCommandList().equals(testCase.getCommandList());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getCommandList().hashCode();
        return result;
    }
}
