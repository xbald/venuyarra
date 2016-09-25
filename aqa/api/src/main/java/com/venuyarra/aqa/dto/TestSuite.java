package com.venuyarra.aqa.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@XmlRootElement(name = "testsuite")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "testsuite", propOrder = {"id", "title", "testCaseList"})
public class TestSuite {
    private Long id;
    private String title;
    private List<TestCase> testCaseList = new ArrayList<>();

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title", nillable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElementWrapper(name = "testcases")
    @XmlElement(name = "testcase")
    public List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public void addTestCase(TestCase testCase) {
        testCaseList.add(testCase);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuite)) return false;

        TestSuite testSuite = (TestSuite) o;

        if (!getId().equals(testSuite.getId())) return false;
        if (getTitle() != null ? !getTitle().equals(testSuite.getTitle()) : testSuite.getTitle() != null) return false;
        return getTestCaseList().equals(testSuite.getTestCaseList());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getTestCaseList().hashCode();
        return result;
    }
}
