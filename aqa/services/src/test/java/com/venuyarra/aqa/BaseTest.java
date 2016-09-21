package com.venuyarra.aqa;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by NIKOLAI on 21.09.2016.
 */
@ContextConfiguration(locations = "classpath:services-context.xml")
public class BaseTest extends AbstractTestNGSpringContextTests {

}
