package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.ClientResponse;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.ExecutionResult;
import com.venuyarra.aqa.dto.Parameter;
import com.venuyarra.aqa.dto.SelectCommand;
import com.venuyarra.aqa.dto.TestCase;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.dto.ValidationCommand;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;

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

        Assert.assertEquals(command, unmarshalled, "Deserialized object differs from original");
    }

    @Test
    public void testClickCommandSerialize() throws JAXBException {
        ClickCommand command = createClickCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final ClickCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), ClickCommand.class);
        Assert.assertEquals(command, unmarshalled, "Deserialized object differs from original");
    }

    @Test
    public void testSelectCommandSerialize() throws JAXBException {
        SelectCommand command = createSelectCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final SelectCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), SelectCommand.class);
        Assert.assertEquals(command, unmarshalled, "Deserialized object differs from original");
    }

    @Test
    public void testEnterCommandSerialize() throws JAXBException {
        EnterCommand command = createEnterCommand();

        StringWriter sw = new StringWriter();

        JAXB.marshal(command, sw);
        final String marshalled = sw.toString();

        final EnterCommand unmarshalled = JAXB.unmarshal(new StringReader(marshalled), EnterCommand.class);
        Assert.assertEquals(command, unmarshalled, "Deserialized object differs from original");
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

        final TestCase unmarshalled = JAXB.unmarshal(new StringReader(marshalled), TestCase.class);
        Assert.assertEquals(testCase, unmarshalled, "Deserialized object differs from original");
    }

    @Test
    public void testTestSuiteSerialization() {
        TestSuite testSuite = new TestSuite();
        testSuite.setId(123L);
        testSuite.setTitle("Test Suite Title");
        for (int i = 0; i < 3; i++) {
            TestCase testCase = new TestCase();
            testCase.setTitle("Test Case Title" + i);
            testCase.setId(1L);

            testCase.setCommandList(
                    Arrays.asList(
                            createValidationCommand(),
                            createClickCommand(),
                            createSelectCommand(),
                            createEnterCommand()
                    )
            );
            testSuite.addTestCase(testCase);
        }
        StringWriter sw = new StringWriter();
        JAXB.marshal(testSuite, sw);
        String marshalled = sw.toString();

        final TestSuite unmarshalled = JAXB.unmarshal(new StringReader(marshalled), TestSuite.class);

        Assert.assertEquals(testSuite, unmarshalled, "Deserialized object differs from original");
    }

    @Test
    public void testClientResponseSerialization() {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setStartedAt(new Date());
        clientResponse.setCommandId(1L);
        clientResponse.setExecutionResult(ExecutionResult.FAILED);
        clientResponse.setReturnedValue("returned value");
        clientResponse.setFinishedAt(new Date());
        clientResponse.setSuiteId(444L);
        try {
            throw new RuntimeException("message");
        } catch (RuntimeException e) {
            clientResponse.setThrowable(e);
        }
        StringWriter sw = new StringWriter();

        JAXB.marshal(clientResponse, sw);
        final String marshaled = sw.toString();
        final ClientResponse unmarshalled = JAXB.unmarshal(new StringReader(marshaled), ClientResponse.class);

        Assert.assertEquals(clientResponse, unmarshalled, "Deserialized object differs from original");

    }

    private ValidationCommand createValidationCommand() {
        ValidationCommand command = new ValidationCommand();
        command.setId(3L);
        command.setExpectedResult("Expected Result");
        command.setLocatorList(
                Arrays.asList(
                        new Parameter(1L, "locT", "locV"),
                        new Parameter(2L, "locT", "locV"),
                        new Parameter(3L, "locT", "locV"),
                        new Parameter(4L, "locT", "locV")
                )
        );
        return command;
    }

    private EnterCommand createEnterCommand() {
        EnterCommand command = new EnterCommand();
        command.setId(1L);
        command.setValue("Enter Value");
        command.setLocatorList(
                Arrays.asList(
                        new Parameter(1L, "locT", "locV"),
                        new Parameter(2L, "locT", "locV"),
                        new Parameter(3L, "locT", "locV"),
                        new Parameter(4L, "locT", "locV")
                )
        );
        return command;
    }

    private SelectCommand createSelectCommand() {
        SelectCommand command = new SelectCommand();
        command.setId(4L);
        command.setValue("Select Value");
        command.setLocatorList(
                Arrays.asList(
                        new Parameter(1L, "locT", "locV"),
                        new Parameter(2L, "locT", "locV"),
                        new Parameter(3L, "locT", "locV"),
                        new Parameter(4L, "locT", "locV")
                )
        );
        return command;
    }

    private ClickCommand createClickCommand() {
        ClickCommand command = new ClickCommand();
        command.setId(2L);
        command.setLocatorList(
                Arrays.asList(
                        new Parameter(1L, "locT", "locV"),
                        new Parameter(2L, "locT", "locV"),
                        new Parameter(3L, "locT", "locV"),
                        new Parameter(4L, "locT", "locV")
                )
        );
        return command;
    }
}
