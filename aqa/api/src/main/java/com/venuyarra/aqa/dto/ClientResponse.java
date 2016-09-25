package com.venuyarra.aqa.dto;

import com.venuyarra.aqa.dto.jaxb.ThrowableAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "response", propOrder = {"commandId", "executionResult", "returnedValue", "throwable"})
public class ClientResponse {
    private Long commandId;
    private Throwable throwable;
    private ExecutionResult executionResult;
    private String returnedValue;

    @XmlElement(name = "commandId", required = true)
    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
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

    @Override
    public String toString() {
        return "ClientResponse{" +
                "commandId=" + commandId +
                ", throwable=" + throwable +
                ", executionResult=" + executionResult +
                ", returnedValue='" + returnedValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientResponse)) return false;

        ClientResponse that = (ClientResponse) o;

        if (!getCommandId().equals(that.getCommandId())) return false;
        if (getExecutionResult() != that.getExecutionResult()) return false;
        return getReturnedValue() != null ? getReturnedValue().equals(that.getReturnedValue()) : that.getReturnedValue() == null;

    }

    @Override
    public int hashCode() {
        int result = getCommandId().hashCode();
        result = 31 * result + getExecutionResult().hashCode();
        result = 31 * result + (getReturnedValue() != null ? getReturnedValue().hashCode() : 0);
        return result;
    }
}
