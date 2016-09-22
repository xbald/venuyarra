package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.services.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;

/**
 * Created by NIKOLAI on 21.09.2016.
 */
@Rollback
public class TestSuiteServiceIT extends BaseTest {
    @Autowired
    private TestSuiteService testSuiteService;
    @Test
    public void testSuiteServiceTest() throws JAXBException {
        final TestSuite testSuite = testSuiteService.get(27L);

        final JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);

        StringWriter sw = new StringWriter();

        jaxbContext.createMarshaller().marshal(testSuite, sw);

        System.out.println(sw.toString());

    }
}
