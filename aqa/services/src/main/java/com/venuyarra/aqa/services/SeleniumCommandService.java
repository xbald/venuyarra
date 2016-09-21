package com.venuyarra.aqa.services;

import com.venuyarra.aqa.dto.SeleniumCommand;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface SeleniumCommandService {
    SeleniumCommand get(Long id);
    List<SeleniumCommand> getAllByTestCaseId(Long testCaseId);
}
