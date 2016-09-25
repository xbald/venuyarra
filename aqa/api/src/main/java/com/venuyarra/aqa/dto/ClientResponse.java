package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.dto.jaxb.DateTimeAdapter;
import com.venuyarra.aqa.dto.jaxb.ThrowableAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "response", propOrder = {"id", "suiteId", "commandId", "executionResult", "returnedValue", "throwable"})
public class ClientResponse {
    private Long id;
    private Long commandId;
    private Throwable throwable;
    private ExecutionResult executionResult;
    private String returnedValue;
    private Date finished;
    private Date started;
    private Long suiteId;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "commandId", required = true)
    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }

    @XmlElement(name = "suiteId")
    public Long getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Long suiteId) {
        this.suiteId = suiteId;
    }

    @XmlElement(name = "throwable", nillable = true, required = true)
    @XmlJavaTypeAdapter(ThrowableAdapter.class)
    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @XmlElement(name = "result", required = true)
    public ExecutionResult getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(ExecutionResult executionResult) {
        this.executionResult = executionResult;
    }

    @XmlElement(name = "returnedValue", required = true, nillable = true)
    public String getReturnedValue() {
        return returnedValue;
    }

    public void setReturnedValue(String returnedValue) {
        this.returnedValue = returnedValue;
    }

    @XmlElement(name = "finishedAt")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public Date getFinished() {
        return finished;
    }

    @XmlElement(name = "startedAt")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    @Override
    public String toString() {
        return "ClientResponse{" +
                "id=" + id +
                ", commandId=" + commandId +
                ", throwable=" + throwable +
                ", executionResult=" + executionResult +
                ", returnedValue='" + returnedValue + '\'' +
                ", finished=" + finished +
                ", started=" + started +
                ", suiteId=" + suiteId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientResponse)) return false;

        ClientResponse that = (ClientResponse) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getCommandId().equals(that.getCommandId())) return false;
        if (getThrowable() != null ? !getThrowable().equals(that.getThrowable()) : that.getThrowable() != null)
            return false;
        if (getExecutionResult() != that.getExecutionResult()) return false;
        if (getReturnedValue() != null ? !getReturnedValue().equals(that.getReturnedValue()) : that.getReturnedValue() != null)
            return false;
        if (!getFinished().equals(that.getFinished())) return false;
        if (!getStarted().equals(that.getStarted())) return false;
        return getSuiteId() != null ? getSuiteId().equals(that.getSuiteId()) : that.getSuiteId() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getCommandId().hashCode();
        result = 31 * result + (getThrowable() != null ? getThrowable().hashCode() : 0);
        result = 31 * result + getExecutionResult().hashCode();
        result = 31 * result + (getReturnedValue() != null ? getReturnedValue().hashCode() : 0);
        result = 31 * result + getFinished().hashCode();
        result = 31 * result + getStarted().hashCode();
        result = 31 * result + (getSuiteId() != null ? getSuiteId().hashCode() : 0);
        return result;
    }
}
