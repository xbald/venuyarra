package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.SeleniumCommandDao;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.services.SeleniumCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@Transactional
public class SeleniumCommandServiceImpl implements SeleniumCommandService {
    @Autowired
    private SeleniumCommandDao seleniumCommandDao;


    @Override
    public SeleniumCommand get() {
        return null;
    }
}
