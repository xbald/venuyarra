package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.SelectCommand;
import com.venuyarra.aqa.dto.TestCase;
import com.venuyarra.aqa.dto.ValidationCommand;
import org.testng.annotations.Test;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by NIKOLAI on 22.09.2016.
 */
public class SerializationTests {

    @Test
    public void testValidationCommandSerialize() throws JAXBException {
        ValidationCommand command = createValidationCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final ValidationCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), ValidationCommand.class);
        System.out.println(unmarshalled);
    }

    @Test
    public void testClickCommandSerialize() throws JAXBException {
        ClickCommand command = createClickCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final ClickCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), ClickCommand.class);
        System.out.println(unmarshalled);
    }

    @Test
    public void testSelectCommandSerialize() throws JAXBException {
        SelectCommand command = createSelectCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final SelectCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), SelectCommand.class);
        System.out.println(unmarshalled);
    }

    @Test
    public void testEnterCommandSerialize() throws JAXBException {
        EnterCommand command = createEnterCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final EnterCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), EnterCommand.class);
        System.out.println(unmarshalled);
    }

    @Test
    public void testTestCaseSerialization() {
        TestCase testCase = new TestCase();
        testCase.setTitle("Test Case Title");
        testCase.setId(1L);

        testCase.setCommandList(
                Arrays.asList(
                        createValidationCommand(),
                        createClickCommand(),
                        createSelectCommand(),
                        createEnterCommand()
                )
        );
        StringWriter sw = new StringWriter();

        JAXB.marshal(testCase, sw);
        final String marshalled = sw.toString();
        System.out.println(marshalled);

        final TestCase unmarshalled = JAXB.unmarshal(new StringReader(marshalled), TestCase.class);
        System.out.println(unmarshalled);
    }

    @Test
    public void testTestSuiteSerialization() {

    }

    @Test
    public void testClientResponseSerialization() {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setCommandId(1L);
        clientResponse.setExecutionResult(ExecutionResult.FAILED);
        clientResponse.setReturnedValue("returned value");
        try {
            throw new RuntimeException("message");
        } catch (RuntimeException e) {
            clientResponse.setThrowable(e);
        }
        StringWriter sw = new StringWriter();

        JAXB.marshal(clientResponse, sw);
        final String marshaled = sw.toString();
        System.out.println(marshaled);

        final ClientResponse unmarshalled = JAXB.unmarshal(new StringReader(marshaled), ClientResponse.class);
        System.out.println(unmarshalled);
    }

    private ValidationCommand createValidationCommand() {
        ValidationCommand command = new ValidationCommand();
        command.setId(3L);
        command.setExpectedResult("Expected Result");
        command.setLocatorType("locator type");
        command.setLocatorValue("locator value");
        return command;
    }

    private EnterCommand createEnterCommand() {
        EnterCommand command = new EnterCommand();
        command.setId(1L);
        command.setValue("Enter Value");
        command.setLocatorType("locator type");
        command.setLocatorValue("locator value");
        return command;
    }

    private SelectCommand createSelectCommand() {
        SelectCommand command = new SelectCommand();
        command.setId(4L);
        command.setValue("Select Value");
        command.setLocatorType("locator type");
        command.setLocatorValue("locator value");
        return command;
    }

    private ClickCommand createClickCommand() {
        ClickCommand command = new ClickCommand();
        command.setId(2L);
        command.setLocatorType("locator type");
        command.setLocatorValue("locator value");
        return command;
    }
}
