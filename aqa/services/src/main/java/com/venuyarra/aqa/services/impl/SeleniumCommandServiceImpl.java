package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.ParameterDao;
import com.venuyarra.aqa.SeleniumCommandDao;
import com.venuyarra.aqa.dto.Parameter;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.services.SeleniumCommandService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@Transactional
public class SeleniumCommandServiceImpl implements SeleniumCommandService {
    private ParameterDao parameterDao;
    private SeleniumCommandDao seleniumCommandDao;

    @Override
    public SeleniumCommand get(Long id) {
        final SeleniumCommand seleniumCommand = seleniumCommandDao.get(id);
        final Parameter parameter = parameterDao.get(seleniumCommand.getParameter());
        seleniumCommand.setLocatorValue(parameter.getLocatorValue());
        seleniumCommand.setLocatorType(parameter.getLocatorValue());
        return seleniumCommand;
    }

    @Override
    public  List<SeleniumCommand> getAllByTestCaseId(Long testCaseId) {
        final List<SeleniumCommand> commandList = seleniumCommandDao.getByTestCaseId(testCaseId);
        for (SeleniumCommand seleniumCommand : commandList) {
            final Parameter parameter = parameterDao.get(seleniumCommand.getParameter());
            seleniumCommand.setLocatorValue(parameter.getLocatorValue());
            seleniumCommand.setLocatorType(parameter.getLocatorType());
        }
        return commandList;
    }

    public void setSeleniumCommandDao(SeleniumCommandDao seleniumCommandDao) {
        this.seleniumCommandDao = seleniumCommandDao;
    }

    public void setParameterDao(ParameterDao parameterDao) {
        this.parameterDao = parameterDao;
    }
}
